package world.cepi.bukstom

import com.avaje.ebean.config.ServerConfig
import net.minestom.server.MinecraftServer
import net.minestom.server.extras.MojangAuth
import org.bukkit.*
import org.bukkit.command.CommandSender
import org.bukkit.command.PluginCommand
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.generator.ChunkGenerator
import org.bukkit.help.HelpMap
import org.bukkit.inventory.*
import org.bukkit.map.MapView
import org.bukkit.permissions.Permission
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginLoadOrder
import org.bukkit.plugin.ServicesManager
import org.bukkit.plugin.SimpleServicesManager
import org.bukkit.plugin.java.JavaPluginLoader
import org.bukkit.plugin.messaging.Messenger
import org.bukkit.plugin.messaging.StandardMessenger
import org.bukkit.scoreboard.ScoreboardManager
import org.bukkit.util.CachedServerIcon
import world.cepi.bukstom.command.MinestomCommandMap
import world.cepi.bukstom.command.MinestomCommandSender
import world.cepi.bukstom.command.MinestomConsoleCommandSender
import world.cepi.bukstom.entity.MinestomPlayer
import world.cepi.bukstom.item.MinestomItemFactory
import world.cepi.bukstom.plugin.MinestomPluginManager
import world.cepi.bukstom.scheduler.MinestomScheduler
import world.cepi.bukstom.scoreboard.MinestomScoreboardManager
import world.cepi.bukstom.util.MinestomUnsafeValues
import world.cepi.bukstom.util.toBukkit
import world.cepi.bukstom.util.toMinestom
import world.cepi.bukstom.world.MinestomWorld
import java.awt.image.BufferedImage
import java.io.File
import java.util.*
import java.util.logging.Level
import java.util.logging.Logger


class MinestomServer : Server {
	val commandMap = MinestomCommandMap(this)

	private val pluginManager = MinestomPluginManager(this, commandMap)
	private val logger = Logger.getLogger("Minecraft")
	private val helpMap = MinestomHelpMap(this)
	private val messenger = StandardMessenger()
	private val servicesManager = SimpleServicesManager()
	private val scheduler = MinestomScheduler()
	private val minestomScoreboardManager = MinestomScoreboardManager()

	override fun sendPluginMessage(source: Plugin, channel: String, message: ByteArray) {
		for (player in onlinePlayers) {
			player.sendPluginMessage(source, channel, message)
		}
	}

	override fun getListeningPluginChannels(): MutableSet<String> {
		val channels = mutableSetOf<String>()

		for (player in onlinePlayers) {
			channels += player.listeningPluginChannels
		}

		return channels
	}

	override fun getName(): String {
		return MinecraftServer.getBrandName()
	}

	override fun getVersion(): String {
		return MinecraftServer.VERSION_NAME
	}

	override fun getBukkitVersion(): String {
		return MinecraftServer.VERSION_NAME
	}

	override fun _INVALID_getOnlinePlayers(): Array<Player> {
		val players = mutableListOf<Player>()
		for (world in worlds) {
			players += world.players
		}
		return players.toTypedArray()
	}

	override fun getOnlinePlayers(): MutableCollection<out Player> {
		return Collections.unmodifiableList(MinecraftServer.getConnectionManager().onlinePlayers.map {
			it.toBukkit()
		})
	}

	override fun getMaxPlayers(): Int {
		throw Exception("Not implementing")
	}

	override fun getPort(): Int {
		return MinecraftServer.getServer().port
	}

	override fun getViewDistance(): Int {
		return MinecraftServer.getChunkViewDistance()
	}

	override fun getIp(): String {
		return MinecraftServer.getServer().address
	}

	override fun getServerName(): String {
		TODO("Not yet implemented")
	}

	override fun getServerId(): String {
		TODO("Not yet implemented")
	}

	override fun getWorldType(): String {
		return "custom"
	}

	override fun getGenerateStructures(): Boolean {
		return false
	}

	override fun getAllowEnd(): Boolean {
		return false
	}

	override fun getAllowNether(): Boolean {
		return false
	}

	override fun hasWhitelist(): Boolean {
		return false
	}

	override fun setWhitelist(value: Boolean) {

	}

	override fun getWhitelistedPlayers(): MutableSet<OfflinePlayer> {
		throw Exception("Not implementing")
	}

	override fun reloadWhitelist() {
	}

	override fun broadcastMessage(message: String): Int {
		//? Can't get length from this?
		//Audiences.all().sendMessage(Component.text(message))

		for (player in onlinePlayers) {
			player.sendMessage(message)
		}

		return onlinePlayers.size
	}

	override fun getUpdateFolder(): String {
		throw Exception("Not implementing")
	}

	override fun getUpdateFolderFile(): File {
		throw Exception("Not implementing")
	}

	override fun getConnectionThrottle(): Long {
		throw Exception("Not implementing")
	}

	override fun getTicksPerAnimalSpawns(): Int {
		throw Exception("Not implementing")
	}

	override fun getTicksPerMonsterSpawns(): Int {
		throw Exception("Not implementing")
	}

	override fun getPlayer(name: String): MinestomPlayer? {
		return MinecraftServer.getConnectionManager().findPlayer(name)?.toBukkit()
	}

	override fun getPlayer(id: UUID): Player? {
		return MinecraftServer.getConnectionManager().getPlayer(id)?.toBukkit()
	}

	override fun getPlayerExact(name: String): Player? {
		return MinecraftServer.getConnectionManager().getPlayer(name)?.toBukkit()
	}

	override fun matchPlayer(name: String): MutableList<Player> {
		val matching = mutableListOf<Player>()
		for (player in MinecraftServer.getConnectionManager().onlinePlayers) {
			if (player.username.startsWith(name)) {
				matching.add(player.toBukkit())
			}
		}
		return matching
	}

	override fun getPluginManager(): MinestomPluginManager {
		return pluginManager
	}

	override fun getScheduler(): MinestomScheduler = scheduler

	override fun getServicesManager(): ServicesManager = servicesManager

	override fun getWorlds(): MutableList<World> {
		val instances = MinecraftServer.getInstanceManager().instances
		val worlds = mutableListOf<World>()

		for (instance in instances) {
			worlds.add(MinestomWorld(this, instance, null))
		}

		return worlds
	}


	override fun createWorld(creator: WorldCreator): MinestomWorld {
		val instance = MinecraftServer.getInstanceManager().createInstanceContainer()
		val world = MinestomWorld(this, instance, creator)

		if (creator.generator() != null)
			instance.chunkGenerator = creator.generator()!!.toMinestom(world)

		return world
	}

	override fun unloadWorld(name: String, save: Boolean): Boolean {
		val id = UUID.fromString(name)
		val instance =
			MinecraftServer.getInstanceManager().instances.firstOrNull() { it.uniqueId == id } ?: return false
		MinecraftServer.getInstanceManager().unregisterInstance(instance)
		return true
	}

	override fun unloadWorld(world: World, save: Boolean): Boolean {
		if (world is MinestomWorld) {
			MinecraftServer.getInstanceManager().unregisterInstance(world.instance)
			return true
		}

		return false
	}

	override fun getWorld(name: String): MinestomWorld? {
		return getWorld(UUID.fromString(name))
	}

	override fun getWorld(uid: UUID): MinestomWorld? {
		return MinecraftServer.getInstanceManager().getInstance(uid)?.toBukkit()
	}

	override fun getMap(id: Short): MapView {
		TODO("Not yet implemented")
	}

	override fun createMap(world: World): MapView {
		throw Exception("Not Implementing")
	}

	override fun reload() {
		//TODO: See what craftbukkit does
	}

	override fun getLogger(): Logger {
		return logger
	}

	override fun getPluginCommand(name: String): PluginCommand? {
		return commandMap.getCommand(name) as? PluginCommand
	}

	override fun savePlayers() {
		// not needed
	}

	override fun dispatchCommand(sender: CommandSender, commandLine: String): Boolean {
		if (commandMap.dispatch(sender, commandLine)) {
			return true
		}

		if (sender is Player) {
			sender.sendMessage("Unknown command. Type \"/help\" for help.")
		} else {
			sender.sendMessage("Unknown command. Type \"help\" for help.")
		}

		return false

	}

	override fun configureDbConfig(config: ServerConfig?) {
		TODO("Not yet implemented")
	}

	override fun addRecipe(recipe: Recipe?): Boolean {
		TODO("Not yet implemented")
	}

	override fun getRecipesFor(result: ItemStack): MutableList<Recipe> {
		TODO("Not yet implemented")
	}

	override fun recipeIterator(): MutableIterator<Recipe> {
		TODO("Not yet implemented")
	}

	override fun clearRecipes() {
	}

	override fun resetRecipes() {

	}

	override fun getCommandAliases(): MutableMap<String, Array<String>> {
		// TODO commands configuration
//        val section: ConfigurationSection = commandsConfiguration.getConfigurationSection("aliases")
//        val result: MutableMap<String, Array<String?>> = LinkedHashMap()
//
//        for (key in section.getKeys(false)) {
//            val commands: List<String?> = if (section.isList(key)) {
//                section.getStringList(key)
//            } else {
//                ImmutableList.of(section.getString(key))
//            }
//            result[key] = commands.toTypedArray()
//        }
//
//        return result
		return mutableMapOf()
	}

	override fun getSpawnRadius(): Int {
		TODO("Not yet implemented")
	}

	override fun setSpawnRadius(value: Int) {
		TODO("Not yet implemented")
	}

	override fun getOnlineMode(): Boolean {
		return MojangAuth.isEnabled()
	}

	override fun getAllowFlight(): Boolean {
		TODO("Not yet implemented")
	}

	override fun isHardcore(): Boolean {
		TODO("Not yet implemented")
	}

	override fun useExactLoginLocation(): Boolean {
		TODO("Not yet implemented")
	}

	override fun shutdown() {
		MinecraftServer.stopCleanly()
	}

	override fun broadcast(message: String, permission: String): Int {
		var count = 0
		for (player in onlinePlayers) {
			if (player.hasPermission(permission)) {
				count++
				player.sendMessage(message)
			}
		}
		return count
	}

	override fun getOfflinePlayer(name: String): OfflinePlayer {
		TODO("Not yet implemented")
	}

	override fun getOfflinePlayer(id: UUID): OfflinePlayer {
		TODO("Not yet implemented")
	}

	override fun getIPBans(): MutableSet<String> {
		TODO("Not yet implemented")
	}

	override fun banIP(address: String) {
		TODO("Not yet implemented")
	}

	override fun unbanIP(address: String) {
		TODO("Not yet implemented")
	}

	override fun getBannedPlayers(): MutableSet<OfflinePlayer> {
		TODO("Not yet implemented")
	}

	override fun getBanList(type: BanList.Type): BanList {
		TODO("Not yet implemented")
	}

	override fun getOperators(): MutableSet<OfflinePlayer> {
		TODO("Not yet implemented")
	}

	override fun getDefaultGameMode(): GameMode {
		TODO("Not yet implemented")
	}

	override fun setDefaultGameMode(mode: GameMode) {
		TODO("Not yet implemented")
	}

	override fun getConsoleSender() = MinestomConsoleCommandSender(this)

	override fun getWorldContainer(): File {
		throw Exception("We aren't storing worlds on disk")
	}

	override fun getOfflinePlayers(): Array<OfflinePlayer> {
		TODO("Not yet implemented")
	}

	override fun getMessenger(): Messenger = messenger

	override fun getHelpMap(): HelpMap {
		return helpMap
	}

	override fun createInventory(owner: InventoryHolder?, type: InventoryType): Inventory {
		TODO("Not yet implemented")
	}

	override fun createInventory(owner: InventoryHolder?, type: InventoryType, title: String): Inventory {
		TODO("Not yet implemented")
	}

	override fun createInventory(owner: InventoryHolder?, size: Int): Inventory {
		TODO("Not yet implemented")
	}

	override fun createInventory(owner: InventoryHolder?, size: Int, title: String): Inventory {
		TODO("Not yet implemented")
	}

	override fun getMonsterSpawnLimit(): Int {
		TODO("Not yet implemented")
	}

	override fun getAnimalSpawnLimit(): Int {
		TODO("Not yet implemented")
	}

	override fun getWaterAnimalSpawnLimit(): Int {
		TODO("Not yet implemented")
	}

	override fun getAmbientSpawnLimit(): Int {
		TODO("Not yet implemented")
	}

	// todo this is a *lie*
	override fun isPrimaryThread(): Boolean {
		return true
	}

	override fun getMotd(): String {
		TODO("Not yet implemented")
	}

	override fun getShutdownMessage(): String? {
		TODO("Not yet implemented")
	}

	override fun getWarningState(): Warning.WarningState {
		return Warning.WarningState.DEFAULT
	}

	override fun getItemFactory(): ItemFactory {
		return MinestomItemFactory()
	}

	override fun getScoreboardManager(): ScoreboardManager = minestomScoreboardManager

	override fun getServerIcon(): CachedServerIcon? {
		TODO("Not yet implemented")
	}

	override fun loadServerIcon(file: File): CachedServerIcon {
		TODO("Not yet implemented")
	}

	override fun loadServerIcon(image: BufferedImage): CachedServerIcon {
		TODO("Not yet implemented")
	}

	override fun setIdleTimeout(threshold: Int) {

	}

	override fun getIdleTimeout(): Int {
		return Int.MAX_VALUE
	}

	override fun createChunkData(world: World): ChunkGenerator.ChunkData {
		TODO("Not yet implemented")
	}

	override fun getUnsafe(): UnsafeValues {
		return MinestomUnsafeValues
	}

	override fun spigot(): Server.Spigot {
		return object : Server.Spigot() {}
	}

	fun loadPlugins() {
		pluginManager.registerInterface(JavaPluginLoader::class.java)
	}

	fun enablePlugins(type: PluginLoadOrder) {
		if (type == PluginLoadOrder.STARTUP) {
			helpMap.clear()
		}
		val plugins = pluginManager.plugins
		for (plugin in plugins) {
			if (!plugin.isEnabled && plugin.description.load === type) {
				enablePlugin(plugin)
			}
		}
		if (type == PluginLoadOrder.POSTWORLD) {
			commandMap.setFallbackCommands()
			commandMap.registerServerAliases()
			syncCommands()
		}
	}

	val currentlyRegisteredCommands = mutableListOf<net.minestom.server.command.builder.Command>()

	fun syncCommands() {
		currentlyRegisteredCommands.forEach { MinecraftServer.getCommandManager().unregister(it) }

		// Register all commands, vanilla ones will be using the old dispatcher references
		for ((label, command) in commandMap.getKnownCommands()) {

			val commandObject = object : net.minestom.server.command.builder.SimpleCommand(label) {
				override fun process(
					sender: net.minestom.server.command.CommandSender, commandLabel: String, args: Array<out String>
				): Boolean {
					return command.execute(
						(sender as? net.minestom.server.entity.Player)?.toBukkit() ?: MinestomCommandSender(
							sender, this@MinestomServer
						), commandLabel, args
					)
				}

				override fun hasAccess(
					sender: net.minestom.server.command.CommandSender, commandString: String?
				): Boolean = command.testPermissionSilent(MinestomCommandSender(sender, this@MinestomServer))
			}

			currentlyRegisteredCommands.add(commandObject)

			MinecraftServer.getCommandManager().register(commandObject)
		}
	}

	fun disablePlugins() {
		pluginManager.disablePlugins()
	}

	private fun enablePlugin(plugin: Plugin) {
		try {
			val perms: List<Permission> = plugin.description.permissions
			for (perm in perms) {
				try {
					pluginManager.addPermission(perm, false)
				} catch (ex: IllegalArgumentException) {
					getLogger().log(
						Level.WARNING,
						"Plugin " + plugin.description.fullName + " tried to register permission '" + perm.name + "' but it's already registered",
						ex
					)
				}
			}
			pluginManager.dirtyPermissibles()
			pluginManager.enablePlugin(plugin)
		} catch (ex: Throwable) {
			Logger.getLogger(MinestomServer::class.java.name)
				.log(Level.SEVERE, ex.message + " loading " + plugin.description.fullName + " (Is it up to date?)", ex)
		}
	}
}
