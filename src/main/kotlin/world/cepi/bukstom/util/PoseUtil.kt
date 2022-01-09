package world.cepi.bukstom.util

import org.bukkit.entity.Pose

fun net.minestom.server.entity.Entity.Pose.toSpigotPose(): Pose {
	return when (this) {
		net.minestom.server.entity.Entity.Pose.STANDING -> Pose.STANDING
		net.minestom.server.entity.Entity.Pose.FALL_FLYING -> Pose.FALL_FLYING
		net.minestom.server.entity.Entity.Pose.SLEEPING -> Pose.SLEEPING
		net.minestom.server.entity.Entity.Pose.SWIMMING -> Pose.SWIMMING
		net.minestom.server.entity.Entity.Pose.SPIN_ATTACK -> Pose.SPIN_ATTACK
		net.minestom.server.entity.Entity.Pose.SNEAKING -> Pose.SNEAKING
		net.minestom.server.entity.Entity.Pose.DYING -> Pose.DYING
	}
}