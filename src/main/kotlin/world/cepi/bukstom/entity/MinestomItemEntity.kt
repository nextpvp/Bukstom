package world.cepi.bukstom.entity

import net.minestom.server.entity.Entity
import org.bukkit.entity.Item
import org.bukkit.inventory.ItemStack
import world.cepi.bukstom.world.MinestomWorld

class MinestomItemEntity(entity: Entity, minestomWorld: MinestomWorld) : Item, MinestomEntity(entity, minestomWorld) {
	override fun getItemStack(): ItemStack {
		TODO("Not yet implemented")
	}

	override fun setItemStack(stack: ItemStack) {
		TODO("Not yet implemented")
	}

	override fun getPickupDelay(): Int {
		TODO("Not yet implemented")
	}

	override fun setPickupDelay(delay: Int) {
		TODO("Not yet implemented")
	}
}