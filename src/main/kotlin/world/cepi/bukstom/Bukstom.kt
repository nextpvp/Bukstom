package world.cepi.bukstom

import net.minestom.server.MinecraftServer
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
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
		// create a default instance for this
		server.enablePlugins(PluginLoadOrder.POSTWORLD) // TODO actually post world

		logger.info("Bukkit api initialized!")
	}

	fun terminate() {
		if (!initialized) return
		server.disablePlugins()
		logger.info("Bukkit api terminated!")
	}
}
