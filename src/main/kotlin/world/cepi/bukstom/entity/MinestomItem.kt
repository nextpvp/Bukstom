package world.cepi.bukstom.entity

import net.minestom.server.entity.ItemEntity
import org.bukkit.entity.Item
import org.bukkit.inventory.ItemStack
import world.cepi.bukstom.util.toBukkit
import world.cepi.bukstom.util.toMinestom
import world.cepi.bukstom.world.MinestomWorld
import java.time.Duration

class MinestomItem(entity: ItemEntity, minestomWorld: MinestomWorld) : Item,
	MinestomEntity<ItemEntity>(entity, minestomWorld) {
	override fun getItemStack(): ItemStack {
		return entity.itemStack.toBukkit()
	}

	override fun setItemStack(stack: ItemStack) {
		entity.itemStack = stack.toMinestom()
	}

	override fun getPickupDelay(): Int {
		return entity.pickupDelay.toInt()
	}

	override fun setPickupDelay(delay: Int) {
		entity.setPickupDelay(Duration.ofSeconds(delay.toLong()))
	}
}