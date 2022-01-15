package world.cepi.bukstom.entity

import net.minestom.server.entity.LivingEntity
import net.minestom.server.entity.metadata.other.ArmorStandMeta
import org.bukkit.entity.ArmorStand
import org.bukkit.inventory.ItemStack
import org.bukkit.util.EulerAngle
import world.cepi.bukstom.util.toBukkit
import world.cepi.bukstom.world.MinestomWorld

class MinestomArmorStand(entity: LivingEntity, minestomWorld: MinestomWorld) : ArmorStand, MinestomLivingEntity(
	entity,
	minestomWorld
) {
	val meta = entity.entityMeta as ArmorStandMeta

	override fun getItemInHand(): ItemStack {
		return entity.itemInMainHand.toBukkit()
	}

	override fun setItemInHand(item: ItemStack?) {
		entity.itemInMainHand
	}

	override fun getBoots(): ItemStack {
		TODO("Not yet implemented")
	}

	override fun setBoots(item: ItemStack?) {
		TODO("Not yet implemented")
	}

	override fun getLeggings(): ItemStack {
		TODO("Not yet implemented")
	}

	override fun setLeggings(item: ItemStack?) {
		TODO("Not yet implemented")
	}

	override fun getChestplate(): ItemStack {
		TODO("Not yet implemented")
	}

	override fun setChestplate(item: ItemStack?) {
		TODO("Not yet implemented")
	}

	override fun getHelmet(): ItemStack {
		TODO("Not yet implemented")
	}

	override fun setHelmet(item: ItemStack?) {
		TODO("Not yet implemented")
	}

	override fun getBodyPose(): EulerAngle {
		TODO("Not yet implemented")
	}

	override fun setBodyPose(pose: EulerAngle?) {
		TODO("Not yet implemented")
	}

	override fun getLeftArmPose(): EulerAngle {
		TODO("Not yet implemented")
	}

	override fun setLeftArmPose(pose: EulerAngle?) {
		TODO("Not yet implemented")
	}

	override fun getRightArmPose(): EulerAngle {
		TODO("Not yet implemented")
	}

	override fun setRightArmPose(pose: EulerAngle?) {
		TODO("Not yet implemented")
	}

	override fun getLeftLegPose(): EulerAngle {
		TODO("Not yet implemented")
	}

	override fun setLeftLegPose(pose: EulerAngle?) {
		TODO("Not yet implemented")
	}

	override fun getRightLegPose(): EulerAngle {
		TODO("Not yet implemented")
	}

	override fun setRightLegPose(pose: EulerAngle?) {
		TODO("Not yet implemented")
	}

	override fun getHeadPose(): EulerAngle {
		TODO("Not yet implemented")
	}

	override fun setHeadPose(pose: EulerAngle?) {
		TODO("Not yet implemented")
	}

	override fun hasBasePlate(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setBasePlate(basePlate: Boolean) {
		TODO("Not yet implemented")
	}

	override fun hasGravity(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setGravity(gravity: Boolean) {
		TODO("Not yet implemented")
	}

	override fun isVisible(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setVisible(visible: Boolean) {
		TODO("Not yet implemented")
	}

	override fun hasArms(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setArms(arms: Boolean) {
		TODO("Not yet implemented")
	}

	override fun isSmall(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setSmall(small: Boolean) {
		TODO("Not yet implemented")
	}

	override fun isMarker(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setMarker(marker: Boolean) {
		TODO("Not yet implemented")
	}

}