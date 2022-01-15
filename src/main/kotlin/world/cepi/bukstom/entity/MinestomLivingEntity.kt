package world.cepi.bukstom.entity

import net.minestom.server.attribute.Attribute
import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.damage.DamageType
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.*
import org.bukkit.inventory.EntityEquipment
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.util.Vector
import world.cepi.bukstom.util.toBukkit
import world.cepi.bukstom.util.toMinestom
import world.cepi.bukstom.world.MinestomWorld

open class MinestomLivingEntity(entity: net.minestom.server.entity.LivingEntity, minestomWorld: MinestomWorld) :
	LivingEntity, MinestomEntity<net.minestom.server.entity.LivingEntity>(
	entity,
	minestomWorld
) {
	override fun damage(amount: Double) {
		entity.damage(DamageType("plugin"), amount.toFloat())
	}

	override fun damage(amount: Double, source: org.bukkit.entity.Entity) {
		entity.damage(DamageType.fromEntity(source.toMinestom()), amount.toFloat())
	}

	override fun _INVALID_damage(amount: Int) {
		TODO("Not yet implemented")
	}

	override fun _INVALID_damage(amount: Int, source: org.bukkit.entity.Entity?) {
		TODO("Not yet implemented")
	}

	override fun getHealth(): Double {
		return entity.health.toDouble()
	}

	override fun _INVALID_getHealth(): Int {
		TODO("Not yet implemented")
	}

	override fun setHealth(health: Double) {
		entity.health = health.toFloat()
	}

	override fun _INVALID_setHealth(health: Int) {
	}

	override fun getMaxHealth(): Double {
		return entity.getAttribute(Attribute.MAX_HEALTH).baseValue.toDouble()
	}

	override fun _INVALID_getMaxHealth(): Int {
		throw Exception()
	}

	override fun setMaxHealth(health: Double) {
		entity.getAttribute(Attribute.MAX_HEALTH).baseValue = health.toFloat()
	}

	override fun _INVALID_setMaxHealth(health: Int) {
		throw Exception()
	}

	override fun resetMaxHealth() {
		//TODO: Get default max health of entity
		entity.getAttribute(Attribute.MAX_HEALTH).baseValue = 20f
	}

	override fun <T : Projectile?> launchProjectile(projectile: Class<out T>?): T {
		return launchProjectile(projectile, null)
	}

	override fun <T : Projectile?> launchProjectile(projectile: Class<out T>?, velocity: Vector?): T {
		var entity: Projectile? = null

		//TODO: Implement rest of projectiles
		if (Snowball::class.java.isAssignableFrom(projectile)) {
			entity = MinestomSnowball(Entity(EntityType.SNOWBALL), minestomWorld)
		}

		return entity as T ?: throw IllegalArgumentException()
	}

	override fun getEyeHeight(): Double {
		return entity.eyeHeight
	}

	override fun getEyeHeight(ignoreSneaking: Boolean): Double {
		return eyeHeight
	}

	override fun getEyeLocation(): Location {
		return location.add(0.0, eyeHeight, 0.0)
	}

	override fun getLineOfSight(transparent: HashSet<Byte>?, maxDistance: Int): MutableList<Block> {
		return mutableListOf()
	}

	override fun getLineOfSight(transparent: MutableSet<Material>?, maxDistance: Int): MutableList<Block> {
		return mutableListOf()
	}

	override fun getTargetBlock(transparent: HashSet<Byte>?, maxDistance: Int): Block {
		return getTargetBlock(mutableSetOf(), maxDistance)
	}

	override fun getTargetBlock(transparent: MutableSet<Material>?, maxDistance: Int): Block {
		val targetPoint = entity.getTargetBlockPosition(maxDistance)
		val block = entity.instance?.getBlock(targetPoint) ?: throw Exception("Player is not in an instance")
		return block.toBukkit(world)
	}

	override fun getLastTwoTargetBlocks(transparent: HashSet<Byte>?, maxDistance: Int): MutableList<Block> {
		TODO("Not yet implemented")
	}

	override fun getLastTwoTargetBlocks(transparent: MutableSet<Material>?, maxDistance: Int): MutableList<Block> {
		TODO("Not yet implemented")
	}

	override fun throwEgg(): Egg {
		return launchProjectile(Egg::class.java)
	}

	override fun throwSnowball(): Snowball {
		return launchProjectile(Snowball::class.java)
	}

	override fun shootArrow(): Arrow {
		return launchProjectile(Arrow::class.java)
	}

	override fun getRemainingAir(): Int {
		TODO("Not yet implemented")
	}

	override fun setRemainingAir(ticks: Int) {
		TODO("Not yet implemented")
	}

	override fun getMaximumAir(): Int {
		TODO("Not yet implemented")
	}

	override fun setMaximumAir(ticks: Int) {
		TODO("Not yet implemented")
	}

	override fun getMaximumNoDamageTicks(): Int {
		TODO("Not yet implemented")
	}

	override fun setMaximumNoDamageTicks(ticks: Int) {
		TODO("Not yet implemented")
	}

	override fun getLastDamage(): Double {
		TODO("Not yet implemented")
	}

	override fun _INVALID_getLastDamage(): Int {
		TODO("Not yet implemented")
	}

	override fun setLastDamage(damage: Double) {
		TODO("Not yet implemented")
	}

	override fun _INVALID_setLastDamage(damage: Int) {
		TODO("Not yet implemented")
	}

	override fun getNoDamageTicks(): Int {
		TODO("Not yet implemented")
	}

	override fun setNoDamageTicks(ticks: Int) {
		TODO("Not yet implemented")
	}

	override fun getKiller(): Player {
		TODO("Not yet implemented")
	}

	override fun addPotionEffect(effect: PotionEffect?): Boolean {
		TODO("Not yet implemented")
	}

	override fun addPotionEffect(effect: PotionEffect?, force: Boolean): Boolean {
		TODO("Not yet implemented")
	}

	override fun addPotionEffects(effects: MutableCollection<PotionEffect>?): Boolean {
		TODO("Not yet implemented")
	}

	override fun hasPotionEffect(type: PotionEffectType?): Boolean {
		TODO("Not yet implemented")
	}

	override fun removePotionEffect(type: PotionEffectType?) {
		TODO("Not yet implemented")
	}

	override fun getActivePotionEffects(): MutableCollection<PotionEffect> {
		TODO("Not yet implemented")
	}

	override fun hasLineOfSight(other: org.bukkit.entity.Entity): Boolean {
		return entity.hasLineOfSight(other.toMinestom())
	}

	override fun getRemoveWhenFarAway(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setRemoveWhenFarAway(remove: Boolean) {
		TODO("Not yet implemented")
	}

	override fun getEquipment(): EntityEquipment {
		TODO("Not yet implemented")
	}

	override fun setCanPickupItems(pickup: Boolean) {
		TODO("Not yet implemented")
	}

	override fun getCanPickupItems(): Boolean {
		TODO("Not yet implemented")
	}

	override fun isLeashed(): Boolean {
		TODO("Not yet implemented")
	}

	override fun getLeashHolder(): org.bukkit.entity.Entity {
		TODO("Not yet implemented")
	}

	override fun setLeashHolder(holder: org.bukkit.entity.Entity?): Boolean {
		TODO("Not yet implemented")
	}
}