package world.cepi.bukstom

import net.minestom.server.MinecraftServer
import org.bukkit.Bukkit
import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.PluginLoadOrder
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.JavaPluginLoader
import world.cepi.bukstom.listener.bukkitEvents
import java.io.File
import java.util.logging.Logger
import kotlin.reflect.KClass

object Bukstom {
	val server = MinestomServer()
	val logger = Logger.getLogger("Bukstom")
	var initialized = false

	//? Maybe throw?
	fun init() {
		if (initialized) return
		initialized = true
		Bukkit.setServer(server)

		MinecraftServer.getGlobalEventHandler().addChild(bukkitEvents())

		server.loadPlugins()
		server.enablePlugins(PluginLoadOrder.STARTUP)
		server.enablePlugins(PluginLoadOrder.POSTWORLD) // TODO actually post world

		logger.info("Bukkit api initialized!")
	}

	fun terminate() {
		if (!initialized) return
		server.disablePlugins()
		logger.info("Bukkit api terminated!")
	}

	inline fun <reified T : JavaPlugin> loadPlugin(name: String) = loadPlugin(name, T::class)
	fun loadPlugin(name: String, clazz: KClass<out JavaPlugin>) = loadPlugin(name, clazz.java)

	fun loadPlugin(name: String, clazz: Class<out JavaPlugin>) {
		if (!initialized) throw IllegalStateException("Bukkit api not initialized yet")

		val descriptionFile = PluginDescriptionFile(name, "", "")
		val loader = JavaPluginLoader(server)

		val plugin = clazz.getConstructor(
			JavaPluginLoader::class.java,
			PluginDescriptionFile::class.java, File::class.java, File::class.java
		).newInstance(loader, descriptionFile, File.createTempFile("", null), File.createTempFile("", null))

		loader.enablePlugin(plugin)
		server.pluginManager.enablePlugin(plugin)
	}
}
