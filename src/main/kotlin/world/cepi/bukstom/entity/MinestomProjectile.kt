package world.cepi.bukstom.entity

import net.minestom.server.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Projectile
import org.bukkit.projectiles.ProjectileSource
import world.cepi.bukstom.world.MinestomWorld

open class MinestomProjectile(entity: Entity, minestomWorld: MinestomWorld) :
	MinestomEntity<Entity>(entity, minestomWorld), Projectile {
	override fun _INVALID_getShooter(): LivingEntity {
		TODO("Not yet implemented")
	}

	override fun getShooter(): ProjectileSource {
		TODO("Not yet implemented")
	}

	override fun _INVALID_setShooter(shooter: LivingEntity?) {
		TODO("Not yet implemented")
	}

	override fun setShooter(source: ProjectileSource?) {
		TODO("Not yet implemented")
	}

	override fun doesBounce(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setBounce(doesBounce: Boolean) {
		TODO("Not yet implemented")
	}
}