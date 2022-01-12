package world.cepi.bukstom.listener

import net.minestom.server.event.Event
import net.minestom.server.event.EventNode

fun bukkitEvents(): EventNode<out Event> {
	val node = EventNode.all("bukkit")

	node.addChild(playerEvents())

	return node
}