package world.cepi.bukstom.listener

import io.papermc.paper.chat.ChatRenderer
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.minestom.server.adventure.audience.Audiences
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode
import net.minestom.server.event.player.PlayerChatEvent
import net.minestom.server.event.player.PlayerLoginEvent
import org.bukkit.Bukkit
import org.bukkit.event.player.PlayerJoinEvent
import world.cepi.bukstom.MinestomServer
import world.cepi.bukstom.entity.MinestomPlayer

fun bukkitEvents(): EventNode<out Event> {
	val node = EventNode.all("bukkit")

	node.addListener(PlayerLoginEvent::class.java) {
		Bukkit.getPluginManager().callEvent(
			PlayerJoinEvent(
				MinestomPlayer(it.player, Bukkit.getServer() as MinestomServer),
				Component.empty()
			)
		)
	}

	node.addListener(PlayerChatEvent::class.java) {
		it.isCancelled = true

		Bukkit.getPluginManager().callEvent(
			AsyncChatEvent(
				true,
				MinestomPlayer(it.player, Bukkit.getServer() as MinestomServer),
				setOf(
					Audiences.all()
				),
				ChatRenderer.defaultRenderer(),
				Component.text(it.message),
				Component.text(it.message)
			)
		)
	}

	return node
}