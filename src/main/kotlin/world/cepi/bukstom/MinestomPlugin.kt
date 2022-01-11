package world.cepi.bukstom

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.generator.ChunkGenerator
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.PluginLoader
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.logging.Logger

open class MinestomPlugin(
	private val minestomServer: MinestomServer,
	private val desc: PluginDescriptionFile
): Plugin {
	override fun onTabComplete(
		sender: CommandSender,
		command: Command,
		alias: String,
		args: Array<out String>
	): MutableList<String>? {
		return null
	}

	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
		return false
	}

	override fun getDataFolder(): File {
		throw Exception("")
	}

	override fun getDescription(): PluginDescriptionFile {
		return desc
	}

	override fun getConfig(): FileConfiguration {
		throw Exception("")
	}

	override fun getResource(filename: String): InputStream? {
		return try {
			val url: URL = javaClass.classLoader.getResource(filename) ?: return null
			val connection = url.openConnection()
			connection.useCaches = false
			connection.getInputStream()
		} catch (ex: IOException) {
			null
		}
	}

	override fun saveConfig() {

	}

	override fun saveDefaultConfig() {

	}

	override fun saveResource(resourcePath: String, replace: Boolean) {

	}

	override fun reloadConfig() {

	}

	override fun getPluginLoader(): PluginLoader {
		throw Exception("")
	}

	override fun getServer(): MinestomServer {
		return minestomServer
	}

	override fun isEnabled(): Boolean {
		return server.pluginManager.isPluginEnabled(this)
	}

	override fun onDisable() {

	}

	override fun onLoad() {

	}

	override fun onEnable() {

	}

	override fun isNaggable(): Boolean {
		return false
	}

	override fun setNaggable(canNag: Boolean) {

	}

	override fun getDefaultWorldGenerator(worldName: String, id: String?): ChunkGenerator? {
		return null
	}

	//TODO: Add plugin name prefix
	override fun getLogger(): Logger {
		return server.logger
	}

	override fun getName(): String {
		return desc.name
	}
}