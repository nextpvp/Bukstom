package world.cepi.bukstom.util

import net.minestom.server.collision.BoundingBox
import net.minestom.server.coordinate.Pos
import net.minestom.server.coordinate.Vec
import net.minestom.server.entity.Entity
import org.bukkit.Location
import org.bukkit.util.Vector
import world.cepi.bukstom.world.MinestomWorld

fun Location.toPosition(): Pos =
	Pos(this.x, this.y, this.z, this.yaw, this.pitch)

fun Entity.teleport(location: Location) {
	this.setInstance(
		(location.world as? MinestomWorld)?.instance ?: return,
		location.toPosition()
	)
}

fun Pos.toLocation(world: MinestomWorld): Location =
	Location(world, this.x, this.y, this.z, this.yaw, this.pitch)

fun Vector.toMinestomVector(): Vec =
	Vec(this.x, this.y, this.z)

fun Vec.toSpigotVector(): Vector = Vector(x, y, z)

fun BoundingBox.toSpigotBoundingBox(): org.bukkit.util.BoundingBox =
	org.bukkit.util.BoundingBox(minX, minY, minZ, maxX, maxY, maxZ)