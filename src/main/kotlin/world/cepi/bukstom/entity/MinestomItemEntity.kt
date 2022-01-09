package world.cepi.bukstom.entity

import net.minestom.server.entity.Entity
import org.bukkit.entity.Item
import org.bukkit.inventory.ItemStack
import world.cepi.bukstom.world.MinestomWorld
import java.util.*

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

	override fun setOwner(owner: UUID?) {
		TODO("Not yet implemented")
	}

	override fun getOwner(): UUID? {
		TODO("Not yet implemented")
	}

	override fun setThrower(uuid: UUID?) {
		TODO("Not yet implemented")
	}

	override fun getThrower(): UUID? {
		TODO("Not yet implemented")
	}

	override fun canMobPickup(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setCanMobPickup(canMobPickup: Boolean) {
		TODO("Not yet implemented")
	}

	override fun canPlayerPickup(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setCanPlayerPickup(canPlayerPickup: Boolean) {
		TODO("Not yet implemented")
	}

	override fun willAge(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setWillAge(willAge: Boolean) {
		TODO("Not yet implemented")
	}
}