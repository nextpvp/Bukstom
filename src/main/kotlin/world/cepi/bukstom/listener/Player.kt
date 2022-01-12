package world.cepi.bukstom.listener

import net.minestom.server.event.Event
import net.minestom.server.event.EventNode
import net.minestom.server.event.player.PlayerChatEvent
import net.minestom.server.event.player.PlayerLoginEvent
import net.minestom.server.event.player.PlayerSpawnEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent
import world.cepi.bukstom.util.callEvent
import world.cepi.bukstom.util.toBukkit
import java.net.InetAddress

fun playerEvents(): EventNode<out Event> {
	val node = EventNode.all("player")

	node.addListener(PlayerLoginEvent::class.java) {
		callEvent(
			org.bukkit.event.player.PlayerLoginEvent(
				it.player.toBukkit(),
				it.player.playerConnection.serverAddress ?: "",
				//it.player.playerConnection.remoteAddress
				//TODO: Implement this
				InetAddress.getLocalHost()
			)
		)
	}

	node.addListener(PlayerSpawnEvent::class.java) {
		callEvent(
			PlayerJoinEvent(
				it.player.toBukkit(),
				""
			)
		)
	}

	node.addListener(PlayerChatEvent::class.java) {
		it.isCancelled = true

		callEvent(
			AsyncPlayerChatEvent(
				true,
				it.player.toBukkit(),
				it.message,
				setOf()
			)
		)
	}

	return node
}