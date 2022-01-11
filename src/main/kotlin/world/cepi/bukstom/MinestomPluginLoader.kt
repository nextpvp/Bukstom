package world.cepi.bukstom

import co.aikar.timings.TimedEventExecutor
import org.bukkit.Warning
import org.bukkit.Warning.WarningState
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.*
import java.io.File
import java.lang.Deprecated
import java.lang.reflect.Method
import java.util.*
import java.util.logging.Level
import java.util.regex.Pattern
import kotlin.Array
import kotlin.Boolean
import kotlin.String
import kotlin.also
import kotlin.emptyArray

class MinestomPluginLoader(
	val plugin: Plugin
): PluginLoader {
	override fun loadPlugin(file: File): Plugin {
		return plugin
	}

	override fun getPluginDescription(file: File): PluginDescriptionFile {
		return plugin.description
	}

	override fun getPluginFileFilters(): Array<Pattern> {
		return emptyArray()
	}

	override fun createRegisteredListeners(
		listener: Listener,
		plugin: Plugin
	): MutableMap<Class<out Event>, MutableSet<RegisteredListener>> {
		val ret = mutableMapOf<Class<out Event>, MutableSet<RegisteredListener>>()

		val methods = mutableSetOf<Method>()
		try {
			val publicMethods = listener.javaClass.methods
			val privateMethods = listener.javaClass.declaredMethods

			for (method in publicMethods) {
				methods.add(method)
			}

			for (method in privateMethods) {
				methods.add(method)
			}
		} catch (e: NoClassDefFoundError) {
			plugin.logger.severe("Plugin " + plugin.description.fullName + " has failed to register events for " + listener.javaClass + " because " + e.message + " does not exist.")
			return ret
		}

		for (method in methods) {
			val eh = method.getAnnotation(EventHandler::class.java) ?: continue
			// Do not register bridge or synthetic methods to avoid event duplication
			// Fixes SPIGOT-893
			if (method.isBridge || method.isSynthetic) {
				continue
			}

			var checkClass: Class<*> = this.javaClass
			if (method.parameterTypes.size != 1 || !Event::class.java.isAssignableFrom(method.parameterTypes[0].also {
					checkClass = it
				})) {
				plugin.logger.severe(plugin.description.fullName + " attempted to register an invalid EventHandler method signature \"" + method.toGenericString() + "\" in " + listener.javaClass)
				continue
			}
			val eventClass = checkClass.asSubclass(Event::class.java)
			method.isAccessible = true
			var eventSet = ret[eventClass]
			if (eventSet == null) {
				eventSet = HashSet()
				ret[eventClass] = eventSet
			}
			var clazz: Class<*> = eventClass
			while (Event::class.java.isAssignableFrom(clazz)) {

				// This loop checks for extending deprecated events
				if (clazz.getAnnotation(Deprecated::class.java) != null) {
					val warning = clazz.getAnnotation(Warning::class.java)
					val warningState = plugin.server.warningState
					if (!warningState.printFor(warning)) {
						break
					}
					plugin.logger.log(
						Level.WARNING, String.format(
							"\"%s\" has registered a listener for %s on method \"%s\", but the event is Deprecated. \"%s\"; please notify the authors %s.",
							plugin.description.fullName,
							clazz.name,
							method.toGenericString(),
							if (warning != null && warning.reason.isNotEmpty()) warning.reason else "Server performance will be affected",
							plugin.description.authors.toTypedArray().contentToString()
						),
						if (warningState == WarningState.ON) AuthorNagException(null) else null
					)
					break
				}
				clazz = clazz.superclass
			}
			val executor: EventExecutor = TimedEventExecutor(
				EventExecutor.create(method, eventClass),
				plugin,
				method,
				eventClass
			) // Paper // Paper (Yes.) - Use factory method `EventExecutor.create()`
			eventSet.add(RegisteredListener(listener, executor, eh.priority, plugin, eh.ignoreCancelled))
		}
		return ret
	}

	override fun enablePlugin(plugin: Plugin) {
		plugin.onEnable()
	}

	override fun disablePlugin(plugin: Plugin) {
		plugin.onDisable()
	}
}