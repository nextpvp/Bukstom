package world.cepi.bukstom.world

import org.bukkit.Location
import org.bukkit.WorldBorder

class MinestomWorldBorder(
	val border: net.minestom.server.instance.WorldBorder
): WorldBorder {
	override fun reset() {

	}

	override fun getSize(): Double {
		return border.diameter
	}

	override fun setSize(newSize: Double) {
		border.diameter = newSize
	}

	override fun setSize(newSize: Double, seconds: Long) {
		border.setDiameter(newSize, seconds)
	}

	override fun getCenter(): Location {
		throw Exception("")
	}

	override fun setCenter(x: Double, z: Double) {
		border.setCenter(x.toFloat(), z.toFloat())
	}

	override fun setCenter(location: Location) {
		setCenter(location.x, location.z)
	}

	override fun getDamageBuffer(): Double {
		return 1.toDouble()
	}

	override fun setDamageBuffer(blocks: Double) {
	}

	override fun getDamageAmount(): Double {
		return 1.toDouble()
	}

	override fun setDamageAmount(damage: Double) {

	}

	override fun getWarningTime(): Int {
		return border.warningTime
	}

	override fun setWarningTime(seconds: Int) {
		border.warningTime = seconds
	}

	override fun getWarningDistance(): Int {
		return border.warningBlocks
	}

	override fun setWarningDistance(distance: Int) {
		border.warningBlocks = distance
	}
}