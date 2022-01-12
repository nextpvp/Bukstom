package world.cepi.bukstom.plugin

import com.google.common.base.Preconditions
import com.google.common.collect.ImmutableSet
import com.google.common.graph.GraphBuilder
import com.google.common.graph.Graphs
import org.apache.commons.lang.Validate
import org.bukkit.Server
import org.bukkit.command.PluginCommandYamlParser
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.permissions.Permissible
import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionDefault
import org.bukkit.plugin.*
import org.bukkit.util.FileUtil
import world.cepi.bukstom.MinestomServer
import world.cepi.bukstom.command.MinestomCommandMap
import java.io.File
import java.lang.reflect.Constructor
import java.util.*
import java.util.logging.Level
import java.util.regex.Pattern

// Literally just SimplePluginManager but with some small changes
class MinestomPluginManager(private val server: MinestomServer, private val commandMap: MinestomCommandMap) :
	PluginManager {
	private val fileAssociations: MutableMap<Pattern, PluginLoader> = HashMap()
	private val plugins: MutableList<Plugin> = ArrayList()
	private val lookupNames: MutableMap<String, Plugin> = HashMap()
	private var dependencyGraph = GraphBuilder.directed().build<String>()
	private var updateDirectory: File? = null
	private val permissions: MutableMap<String, Permission> = HashMap()
	private val defaultPerms: MutableMap<Boolean, MutableSet<Permission>> = LinkedHashMap()
	private val permSubs: MutableMap<String, MutableMap<Permissible, Boolean>> = HashMap()
	private val defSubs: MutableMap<Boolean, MutableMap<Permissible, Boolean>> = HashMap()
	private val useTimings = false
	private var pluginsDirectory: File? = null
	fun pluginsDirectory(): File? {
		return pluginsDirectory
	} // Paper

	init {
		defaultPerms[true] = LinkedHashSet()
		defaultPerms[false] = LinkedHashSet()
	}

	/**
	 * Registers the specified plugin loader
	 *
	 * @param loader Class name of the PluginLoader to register
	 * @throws IllegalArgumentException Thrown when the given Class is not a
	 * valid PluginLoader
	 */
	@Throws(IllegalArgumentException::class)
	override fun registerInterface(loader: Class<out PluginLoader>) {
		val instance: PluginLoader
		if (PluginLoader::class.java.isAssignableFrom(loader)) {
			val constructor: Constructor<out PluginLoader>
			try {
				constructor = loader.getConstructor(Server::class.java)
				instance = constructor.newInstance(server)
			} catch (ex: NoSuchMethodException) {
				val className = loader.name
				throw IllegalArgumentException(
					String.format(
						"Class %s does not have a public %s(Server) constructor",
						className,
						className
					), ex
				)
			} catch (ex: Exception) {
				throw IllegalArgumentException(
					String.format(
						"Unexpected exception %s while attempting to construct a new instance of %s",
						ex.javaClass.name,
						loader.name
					), ex
				)
			}
		} else {
			throw IllegalArgumentException(
				String.format(
					"Class %s does not implement interface PluginLoader",
					loader.name
				)
			)
		}
		val patterns = instance.pluginFileFilters
		synchronized(this) {
			for (pattern: Pattern in patterns) {
				fileAssociations.put(pattern, instance)
			}
		}
	}

	/**
	 * Loads the plugins contained within the specified directory
	 *
	 * @param directory Directory to check for plugins
	 * @return A list of all plugins loaded
	 */
	override fun loadPlugins(directory: File): Array<Plugin> {
		// Paper start - extra jars
		return this.loadPlugins(directory, emptyList())
	}

	fun loadPlugins(directory: File, extraPluginJars: List<File>): Array<Plugin> {
		pluginsDirectory = directory
		// Paper end
		Validate.notNull(directory, "Directory cannot be null")
		Validate.isTrue(directory.isDirectory, "Directory must be a directory")
		val result: MutableList<Plugin> = ArrayList()
		val filters: Set<Pattern> = fileAssociations.keys
		if (server.updateFolder != "") {
			updateDirectory = File(directory, server.updateFolder)
		}
		val plugins: MutableMap<String, File> = HashMap()
		val loadedPlugins: MutableSet<String> = HashSet()
		val pluginsProvided: MutableMap<String, String> = HashMap()
		val dependencies: MutableMap<String, MutableCollection<String>> = HashMap()
		val softDependencies: MutableMap<String, MutableCollection<String>> = HashMap()

		// This is where it figures out all possible plugins
		// Paper start - extra jars
		val pluginJars: MutableList<File> = ArrayList(Arrays.asList(*directory.listFiles()))
		pluginJars.addAll(extraPluginJars)
		for (file: File in pluginJars) {
			// Paper end
			var loader: PluginLoader? = null
			for (filter: Pattern in filters) {
				val match = filter.matcher(file.name)
				if (match.find()) {
					loader = fileAssociations[filter]
				}
			}
			if (loader == null) continue
			var description: PluginDescriptionFile? = null
			try {
				description = loader.getPluginDescription(file)
				val name = description.getName()
				if (name.equals("bukkit", ignoreCase = true) || name.equals(
						"minecraft",
						ignoreCase = true
					) || name.equals("mojang", ignoreCase = true)
				) {
					server.logger.log(
						Level.SEVERE,
						"Could not load '" + file.path + "' in folder '" + file.parentFile.path + "': Restricted Name"
					) // Paper
					continue
				} else if (description.rawName.indexOf(' ') != -1) {
					server.logger.log(
						Level.SEVERE,
						"Could not load '" + file.path + "' in folder '" + file.parentFile.path + "': uses the space-character (0x20) in its name"
					) // Paper
					continue
				}
			} catch (ex: InvalidDescriptionException) {
				server.logger.log(
					Level.SEVERE,
					"Could not load '" + file.path + "' in folder '" + file.parentFile.path + "'",
					ex
				) // Paper
				continue
			}
			val replacedFile = plugins.put(description.getName(), file)
			if (replacedFile != null) {
				server.logger.severe(
					String.format(
						"Ambiguous plugin name `%s' for files `%s' and `%s' in `%s'",
						description.getName(),
						file.path,
						replacedFile.path,
						file.parentFile.path // Paper
					)
				)
			}
			val removedProvided = pluginsProvided.remove(description.getName())
			if (removedProvided != null) {
				server.logger.warning(
					String.format(
						"Ambiguous plugin name `%s'. It is also provided by `%s'",
						description.getName(),
						removedProvided
					)
				)
			}
			val softDependencySet: Collection<String> = description.getSoftDepend()
			if (softDependencySet != null && !softDependencySet.isEmpty()) {
				if (softDependencies.containsKey(description.getName())) {
					// Duplicates do not matter, they will be removed together if applicable
					softDependencies[description.getName()]!!.addAll(softDependencySet)
				} else {
					softDependencies[description.getName()] = LinkedList(softDependencySet)
				}
				for (depend: String in softDependencySet) {
					dependencyGraph.putEdge(description.getName(), depend)
				}
			}
			val dependencySet: Collection<String> = description.getDepend()
			if (dependencySet != null && !dependencySet.isEmpty()) {
				dependencies[description.getName()] = LinkedList(dependencySet)
				for (depend: String in dependencySet) {
					dependencyGraph.putEdge(description.getName(), depend)
				}
			}
			val loadBeforeSet: Collection<String> = description.getLoadBefore()
			if (loadBeforeSet != null && !loadBeforeSet.isEmpty()) {
				for (loadBeforeTarget: String in loadBeforeSet) {
					if (softDependencies.containsKey(loadBeforeTarget)) {
						softDependencies[loadBeforeTarget]!!.add(description.getName())
					} else {
						// softDependencies is never iterated, so 'ghost' plugins aren't an issue
						val shortSoftDependency: MutableCollection<String> = LinkedList()
						shortSoftDependency.add(description.getName())
						softDependencies[loadBeforeTarget] = shortSoftDependency
					}
					dependencyGraph.putEdge(loadBeforeTarget, description.getName())
				}
			}
		}
		while (!plugins.isEmpty()) {
			var missingDependency = true
			var pluginIterator: MutableIterator<Map.Entry<String, File>> = plugins.entries.iterator()
			while (pluginIterator.hasNext()) {
				val entry = pluginIterator.next()
				val plugin = entry.key
				if (dependencies.containsKey(plugin)) {
					val dependencyIterator = dependencies[plugin]!!
						.iterator()
					val missingHardDependencies: MutableSet<String> = HashSet(
						dependencies[plugin]!!.size
					) // Paper - list all missing hard depends
					while (dependencyIterator.hasNext()) {
						val dependency = dependencyIterator.next()

						// Dependency loaded
						if (loadedPlugins.contains(dependency)) {
							dependencyIterator.remove()

							// We have a dependency not found
						} else if (!plugins.containsKey(dependency) && !pluginsProvided.containsKey(dependency)) {
							// Paper start
							missingHardDependencies.add(dependency)
						}
					}
					if (!missingHardDependencies.isEmpty()) {
						// Paper end
						missingDependency = false
						pluginIterator.remove()
						softDependencies.remove(plugin)
						dependencies.remove(plugin)
						server.logger.log(
							Level.SEVERE,
							"Could not load '" + entry.value.path + "' in folder '" + entry.value.parentFile.path + "'",  // Paper
							UnknownDependencyException(Exception(missingHardDependencies.joinToString()), plugin)
						) // Paper
					}
					if (dependencies.containsKey(plugin) && dependencies[plugin]!!.isEmpty()) {
						dependencies.remove(plugin)
					}
				}
				if (softDependencies.containsKey(plugin)) {
					val softDependencyIterator = softDependencies[plugin]!!
						.iterator()
					while (softDependencyIterator.hasNext()) {
						val softDependency = softDependencyIterator.next()

						// Soft depend is no longer around
						if (!plugins.containsKey(softDependency) && !pluginsProvided.containsKey(softDependency)) {
							softDependencyIterator.remove()
						}
					}
					if (softDependencies[plugin]!!.isEmpty()) {
						softDependencies.remove(plugin)
					}
				}
				if (!(dependencies.containsKey(plugin) || softDependencies.containsKey(plugin)) && plugins.containsKey(
						plugin
					)
				) {
					// We're clear to load, no more soft or hard dependencies left
					val file = plugins[plugin]
					pluginIterator.remove()
					missingDependency = false
					try {
						val loadedPlugin = loadPlugin(file!!)
						if (loadedPlugin != null) {
							result.add(loadedPlugin)
							loadedPlugins.add(loadedPlugin.name)
							//loadedPlugins.addAll(loadedPlugin.description.pro)
						} else {
							server.logger.log(
								Level.SEVERE,
								"Could not load '" + file.path + "' in folder '" + file.parentFile.path + "'"
							) // Paper
						}
						continue
					} catch (ex: InvalidPluginException) {
						server.logger.log(
							Level.SEVERE,
							"Could not load '" + file!!.path + "' in folder '" + file.parentFile.path + "'",
							ex
						) // Paper
					}
				}
			}
			if (missingDependency) {
				// We now iterate over plugins until something loads
				// This loop will ignore soft dependencies
				pluginIterator = plugins.entries.iterator()
				while (pluginIterator.hasNext()) {
					val entry = pluginIterator.next()
					val plugin = entry.key
					if (!dependencies.containsKey(plugin)) {
						softDependencies.remove(plugin)
						missingDependency = false
						val file = entry.value
						pluginIterator.remove()
						try {
							val loadedPlugin = loadPlugin(file)
							if (loadedPlugin != null) {
								result.add(loadedPlugin)
								loadedPlugins.add(loadedPlugin.name)
								//loadedPlugins.addAll(loadedPlugin.description.provides)
							} else {
								server.logger.log(
									Level.SEVERE,
									"Could not load '" + file.path + "' in folder '" + file.parentFile.path + "'"
								) // Paper
							}
							break
						} catch (ex: InvalidPluginException) {
							server.logger.log(
								Level.SEVERE,
								"Could not load '" + file.path + "' in folder '" + file.parentFile.path + "'",
								ex
							) // Paper
						}
					}
				}
				// We have no plugins left without a depend
				if (missingDependency) {
					softDependencies.clear()
					dependencies.clear()
					val failedPluginIterator = plugins.values.iterator()
					while (failedPluginIterator.hasNext()) {
						val file = failedPluginIterator.next()
						failedPluginIterator.remove()
						server.logger.log(
							Level.SEVERE,
							"Could not load '" + file.path + "' in folder '" + file.parentFile.path + "': circular dependency detected"
						) // Paper
					}
				}
			}
		}
		return result.toTypedArray()
	}

	/**
	 * Loads the plugin in the specified file
	 *
	 *
	 * File must be valid according to the current enabled Plugin interfaces
	 *
	 * @param file File containing the plugin to load
	 * @return The Plugin loaded, or null if it was invalid
	 * @throws InvalidPluginException Thrown when the specified file is not a
	 * valid plugin
	 * @throws UnknownDependencyException If a required dependency could not
	 * be found
	 */
	@Synchronized
	@Throws(InvalidPluginException::class, UnknownDependencyException::class)
	override fun loadPlugin(file: File): Plugin? {
		Validate.notNull(file, "File cannot be null")
		checkUpdate(file)
		val filters: Set<Pattern> = fileAssociations.keys
		var result: Plugin? = null
		for (filter: Pattern in filters) {
			val name = file.name
			val match = filter.matcher(name)
			if (match.find()) {
				val loader = fileAssociations[filter]
				result = loader!!.loadPlugin(file)
			}
		}
		if (result != null) {
			plugins.add(result)
			lookupNames[result.description.name.lowercase()] = result // Paper
		}
		return result
	}

	private fun checkUpdate(file: File) {
		if (updateDirectory == null || !updateDirectory!!.isDirectory) {
			return
		}
		val updateFile = File(updateDirectory, file.name)
		if (updateFile.isFile && FileUtil.copy(updateFile, file)) {
			updateFile.delete()
		}
	}

	/**
	 * Checks if the given plugin is loaded and returns it when applicable
	 *
	 *
	 * Please note that the name of the plugin is case-sensitive
	 *
	 * @param name Name of the plugin to check
	 * @return Plugin if it exists, otherwise null
	 */
	@Synchronized
	override fun getPlugin(name: String): Plugin? {
		return lookupNames[name.replace(' ', '_').lowercase()] // Paper
	}

	@Synchronized
	override fun getPlugins(): Array<Plugin> {
		return plugins.toTypedArray()
	}

	/**
	 * Checks if the given plugin is enabled or not
	 *
	 *
	 * Please note that the name of the plugin is case-sensitive.
	 *
	 * @param name Name of the plugin to check
	 * @return true if the plugin is enabled, otherwise false
	 */
	override fun isPluginEnabled(name: String): Boolean {
		val plugin = getPlugin(name)
		return isPluginEnabled(plugin)
	}

	/**
	 * Checks if the given plugin is enabled or not
	 *
	 * @param plugin Plugin to check
	 * @return true if the plugin is enabled, otherwise false
	 */
	@Synchronized
	override fun isPluginEnabled(plugin: Plugin?): Boolean { // Paper - synchronize
		return if ((plugin != null) && (plugins.contains(plugin))) {
			plugin.isEnabled
		} else {
			false
		}
	}

	@Synchronized
	override fun enablePlugin(plugin: Plugin) { // Paper - synchronize
		if (!plugin.isEnabled) {
			val pluginCommands = PluginCommandYamlParser.parse(plugin)
			if (!pluginCommands.isEmpty()) {
				commandMap.registerAll(plugin.description.name, pluginCommands)
			}
			try {
				plugin.pluginLoader.enablePlugin(plugin)
			} catch (ex: Throwable) {
				handlePluginException(
					"Error occurred (in the plugin loader) while enabling "
						+ plugin.description.fullName + " (Is it up to date?)", ex, plugin
				)
			}
			HandlerList.bakeAll()
		}
	}

	override fun disablePlugins() {
		disablePlugins(false)
	}

	fun disablePlugins(closeClassloaders: Boolean) {
		// Paper end - close Classloader on disable
		val plugins = getPlugins()
		for (i in plugins.indices.reversed()) {
			disablePlugin(plugins[i]) // Paper - close Classloader on disable
		}
	}

	@Synchronized
	override fun disablePlugin(plugin: Plugin?) { // Paper - synchronize
		plugin ?: return
		// Paper end - close Classloader on disable
		if (plugin.isEnabled) {
			try {
				plugin.pluginLoader.disablePlugin(plugin) // Paper - close Classloader on disable
			} catch (ex: Throwable) {
				handlePluginException(
					("Error occurred (in the plugin loader) while disabling "
						+ plugin.description.fullName + " (Is it up to date?)"), ex, plugin
				) // Paper
			}
			try {
				server.scheduler.cancelTasks(plugin)
			} catch (ex: Throwable) {
				handlePluginException(
					("Error occurred (in the plugin loader) while cancelling tasks for "
						+ plugin.description.fullName + " (Is it up to date?)"), ex, plugin
				) // Paper
			}
			try {
				server.servicesManager.unregisterAll(plugin)
			} catch (ex: Throwable) {
				handlePluginException(
					("Error occurred (in the plugin loader) while unregistering services for "
						+ plugin.description.fullName + " (Is it up to date?)"), ex, plugin
				) // Paper
			}
			try {
				HandlerList.unregisterAll(plugin)
			} catch (ex: Throwable) {
				handlePluginException(
					("Error occurred (in the plugin loader) while unregistering events for "
						+ plugin.description.fullName + " (Is it up to date?)"), ex, plugin
				) // Paper
			}
			try {
				server.messenger.unregisterIncomingPluginChannel(plugin)
				server.messenger.unregisterOutgoingPluginChannel(plugin)
			} catch (ex: Throwable) {
				handlePluginException(
					("Error occurred (in the plugin loader) while unregistering plugin channels for "
						+ plugin.description.fullName + " (Is it up to date?)"), ex, plugin
				) // Paper
			}
		}
	}

	// Paper start
	private fun handlePluginException(msg: String, ex: Throwable, plugin: Plugin) {
		server.logger.log(Level.SEVERE, msg, ex)
	}

	// Paper end
	override fun clearPlugins() {
		synchronized(this) {
			disablePlugins(true) // Paper - close Classloader on disable
			plugins.clear()
			lookupNames.clear()
			dependencyGraph = GraphBuilder.directed().build()
			HandlerList.unregisterAll()
			fileAssociations.clear()
			permissions.clear()
			defaultPerms.get(true)!!.clear()
			defaultPerms.get(false)!!.clear()
		}
	}

	private fun fireEvent(event: Event) {
		callEvent(event)
	} // Paper - support old method incase plugin uses reflection

	/**
	 * Calls an event with the given details.
	 *
	 * @param event Event details
	 */
	override fun callEvent(event: Event) {
		// Paper - replace callEvent by merging to below method
		/*if (event.isAsynchronous && server.isPrimaryThread) {
			throw IllegalStateException(event.eventName + " may only be triggered asynchronously.")
		} else if (!event.isAsynchronous && !server.isPrimaryThread && !server.isStopping) {
			throw IllegalStateException(event.eventName + " may only be triggered synchronously.")
		}*/

		val handlers = event.handlers
		val listeners = handlers.registeredListeners
		for (registration: RegisteredListener in listeners) {
			if (!registration.plugin.isEnabled) {
				continue
			}
			try {
				registration.callEvent(event)
			} catch (ex: AuthorNagException) {
				val plugin = registration.plugin
				if (plugin.isNaggable) {
					plugin.isNaggable = false
					server.logger.log(
						Level.SEVERE, String.format(
							"Nag author(s): '%s' of '%s' about the following: %s",
							plugin.description.authors,
							plugin.description.fullName,
							ex.message
						)
					)
				}
			} catch (ex: Throwable) {
				// Paper start - error reporting
				val msg = "Could not pass event " + event.eventName + " to " + registration.plugin.description.fullName
				server.logger.log(Level.SEVERE, msg, ex)
			}
		}
	}

	override fun registerEvents(listener: Listener, plugin: Plugin) {
		if (!plugin.isEnabled) {
			throw IllegalPluginAccessException("Plugin attempted to register $listener while not enabled")
		}
		for (entry: Map.Entry<Class<out Event>, Set<RegisteredListener?>?> in plugin.pluginLoader.createRegisteredListeners(
			listener,
			plugin
		).entries) {
			getEventListeners(getRegistrationClass(entry.key)).registerAll((entry.value)!!)
		}
	}

	override fun registerEvent(
		event: Class<out Event>,
		listener: Listener,
		priority: EventPriority,
		executor: EventExecutor,
		plugin: Plugin
	) {
		registerEvent(event, listener, priority, executor, plugin, false)
	}

	/**
	 * Registers the given event to the specified listener using a directly
	 * passed EventExecutor
	 *
	 * @param event Event class to register
	 * @param listener PlayerListener to register
	 * @param priority Priority of this event
	 * @param executor EventExecutor to register
	 * @param plugin Plugin to register
	 * @param ignoreCancelled Do not call executor if event was already
	 * cancelled
	 */
	override fun registerEvent(
		event: Class<out Event>,
		listener: Listener,
		priority: EventPriority,
		executor: EventExecutor,
		plugin: Plugin,
		ignoreCancelled: Boolean
	) {
		var executor = executor
		Validate.notNull(listener, "Listener cannot be null")
		Validate.notNull(priority, "Priority cannot be null")
		Validate.notNull(executor, "Executor cannot be null")
		Validate.notNull(plugin, "Plugin cannot be null")
		if (!plugin.isEnabled) {
			throw IllegalPluginAccessException("Plugin attempted to register $event while not enabled")
		}

		if (false) { // Spigot - RL handles useTimings check now // Paper
			getEventListeners(event).register(
				TimedRegisteredListener(
					listener,
					executor,
					priority,
					plugin,
					ignoreCancelled
				)
			)
		} else {
			getEventListeners(event).register(RegisteredListener(listener, executor, priority, plugin, ignoreCancelled))
		}
	}

	private fun getEventListeners(type: Class<out Event>): HandlerList {
		try {
			val method = getRegistrationClass(type).getDeclaredMethod("getHandlerList")
			method.isAccessible = true
			return method.invoke(null) as HandlerList
		} catch (e: Exception) {
			throw IllegalPluginAccessException(e.toString())
		}
	}

	private fun getRegistrationClass(clazz: Class<out Event>): Class<out Event> {
		try {
			clazz.getDeclaredMethod("getHandlerList")
			return clazz
		} catch (e: NoSuchMethodException) {
			return if (((clazz.superclass != null
					) && !(clazz.superclass == Event::class.java)
					&& Event::class.java.isAssignableFrom(clazz.superclass))
			) {
				getRegistrationClass(clazz.superclass.asSubclass(Event::class.java))
			} else {
				throw IllegalPluginAccessException("Unable to find handler list for event " + clazz.name + ". Static getHandlerList method required!")
			}
		}
	}

	override fun getPermission(name: String): Permission? {
		return permissions[name.lowercase()]
	}

	override fun addPermission(perm: Permission) {
		addPermission(perm, true)
	}

	@Deprecated("")
	fun addPermission(perm: Permission, dirty: Boolean) {
		val name = perm.name.lowercase()
		if (permissions.containsKey(name)) {
			throw IllegalArgumentException("The permission $name is already defined!")
		}
		permissions[name] = perm
		calculatePermissionDefault(perm, dirty)
	}

	override fun getDefaultPermissions(op: Boolean): Set<Permission> {
		return ImmutableSet.copyOf(defaultPerms[op])
	}

	override fun removePermission(perm: Permission) {
		removePermission(perm.name)
	}

	override fun removePermission(name: String) {
		permissions.remove(name.lowercase())
	}

	override fun recalculatePermissionDefaults(perm: Permission) {
		if (perm != null && permissions.containsKey(perm.name.lowercase())) {
			defaultPerms[true]!!.remove(perm)
			defaultPerms[false]!!.remove(perm)
			calculatePermissionDefault(perm, true)
		}
	}

	private fun calculatePermissionDefault(perm: Permission, dirty: Boolean) {
		if ((perm.default == PermissionDefault.OP) || (perm.default == PermissionDefault.TRUE)) {
			defaultPerms[true]!!.add(perm)
			if (dirty) {
				dirtyPermissibles(true)
			}
		}
		if ((perm.default == PermissionDefault.NOT_OP) || (perm.default == PermissionDefault.TRUE)) {
			defaultPerms[false]!!.add(perm)
			if (dirty) {
				dirtyPermissibles(false)
			}
		}
	}

	@Deprecated("")
	fun dirtyPermissibles() {
		dirtyPermissibles(true)
		dirtyPermissibles(false)
	}

	private fun dirtyPermissibles(op: Boolean) {
		val permissibles = getDefaultPermSubscriptions(op)
		for (p: Permissible in permissibles) {
			p.recalculatePermissions()
		}
	}

	override fun subscribeToPermission(permission: String, permissible: Permissible) {
		val name = permission.lowercase()
		var map = permSubs[name]
		if (map == null) {
			map = WeakHashMap()
			permSubs[name] = map
		}
		map[permissible] = true
	}

	override fun unsubscribeFromPermission(permission: String, permissible: Permissible) {
		val name = permission.lowercase()
		val map = permSubs[name]
		if (map != null) {
			map.remove(permissible)
			if (map.isEmpty()) {
				permSubs.remove(name)
			}
		}
	}

	override fun getPermissionSubscriptions(permission: String): Set<Permissible> {
		val name = permission.lowercase()
		val map: Map<Permissible, Boolean>? = permSubs[name]
		return if (map == null) {
			ImmutableSet.of()
		} else {
			ImmutableSet.copyOf(map.keys)
		}
	}

	override fun subscribeToDefaultPerms(op: Boolean, permissible: Permissible) {
		var map = defSubs[op]
		if (map == null) {
			map = WeakHashMap()
			defSubs[op] = map
		}
		map[permissible] = true
	}

	override fun unsubscribeFromDefaultPerms(op: Boolean, permissible: Permissible) {
		val map = defSubs[op]
		if (map != null) {
			map.remove(permissible)
			if (map.isEmpty()) {
				defSubs.remove(op)
			}
		}
	}

	override fun getDefaultPermSubscriptions(op: Boolean): Set<Permissible> {
		val map: Map<Permissible, Boolean>? = defSubs[op]
		return if (map == null) {
			ImmutableSet.of()
		} else {
			ImmutableSet.copyOf(map.keys)
		}
	}

	override fun getPermissions(): Set<Permission> {
		return HashSet(permissions.values)
	}

	fun isTransitiveDepend(plugin: PluginDescriptionFile, depend: PluginDescriptionFile): Boolean {
		Preconditions.checkArgument(plugin != null, "plugin")
		Preconditions.checkArgument(depend != null, "depend")
		if (dependencyGraph.nodes().contains(plugin.name)) {
			val reachableNodes = Graphs.reachableNodes(dependencyGraph, plugin.name)
			if (reachableNodes.contains(depend.name)) {
				return true
			}
		}
		return false
	}

	override fun useTimings(): Boolean {
		return true // Spigot
	}

	/**
	 * Sets whether or not per event timing code should be used
	 *
	 * @param use True if per event timing code should be used
	 */
	fun useTimings(use: Boolean) {

	}

	// Paper start
	fun clearPermissions() {
		permissions.clear()
		defaultPerms[true]!!.clear()
		defaultPerms[false]!!.clear()
	} // Paper end
}