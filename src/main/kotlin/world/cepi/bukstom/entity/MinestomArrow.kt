package world.cepi.bukstom.entity

import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.arrow.ArrowMeta
import org.bukkit.entity.Arrow
import org.bukkit.entity.LivingEntity
import org.bukkit.projectiles.ProjectileSource
import world.cepi.bukstom.util.toBukkit
import world.cepi.bukstom.util.toMinestom
import world.cepi.bukstom.world.MinestomWorld

class MinestomArrow(entity: Entity, minestomWorld: MinestomWorld) : Arrow,
	MinestomEntity<Entity>(entity, minestomWorld) {
	val meta = entity.entityMeta as ArrowMeta

	override fun spigot(): Arrow.Spigot {
		return object : Arrow.Spigot() {}
	}

	override fun _INVALID_getShooter(): LivingEntity {
		throw Exception()
	}

	override fun getShooter(): ProjectileSource {
		if (meta.shooter !is net.minestom.server.entity.LivingEntity) throw Exception("Cannot convert minestom shooter to bukkit because it is not living")
		return (meta.shooter?.toBukkit() as? LivingEntity) ?: throw Exception()
	}

	override fun _INVALID_setShooter(shooter: LivingEntity?) {
		throw Exception()
	}

	override fun setShooter(source: ProjectileSource?) {
		if (source !is LivingEntity) return
		source.let { meta.shooter = source.toMinestom() }
	}

	override fun doesBounce(): Boolean {
		return meta.isNoClip
	}

	override fun setBounce(doesBounce: Boolean) {
		meta.isNoClip = doesBounce
	}

	override fun getKnockbackStrength(): Int {
		return 0
	}

	override fun setKnockbackStrength(knockbackStrength: Int) {

	}

	override fun isCritical(): Boolean {
		return meta.isCritical
	}

	override fun setCritical(critical: Boolean) {
		meta.isCritical = critical
	}
}
