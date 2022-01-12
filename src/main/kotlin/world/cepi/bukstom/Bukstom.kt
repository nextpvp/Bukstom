package world.cepi.bukstom

import net.minestom.server.MinecraftServer
import org.bukkit.Bukkit
import org.bukkit.plugin.PluginLoadOrder
import world.cepi.bukstom.listener.bukkitEvents
import java.util.logging.Logger

object Bukstom {
	@JvmStatic
	val server = MinestomServer()

	val logger = Logger.getLogger("Bukstom")
	var initialized = false

	//? Maybe throw?
	@JvmStatic
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

	@JvmStatic
	fun terminate() {
		if (!initialized) return
		server.disablePlugins()
		logger.info("Bukkit api terminated!")
	}
}
