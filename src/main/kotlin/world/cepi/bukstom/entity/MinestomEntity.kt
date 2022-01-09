package world.cepi.bukstom.entity

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.*
import org.bukkit.block.BlockFace
import org.bukkit.block.PistonMoveReaction
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Pose
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.metadata.MetadataValue
import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionAttachment
import org.bukkit.permissions.PermissionAttachmentInfo
import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.plugin.Plugin
import org.bukkit.util.BoundingBox
import org.bukkit.util.Vector
import world.cepi.bukstom.util.*
import world.cepi.bukstom.world.MinestomWorld
import java.util.*

open class MinestomEntity(val entity: net.minestom.server.entity.Entity, val minestomWorld: MinestomWorld) : Entity {
	override fun setMetadata(metadataKey: String, newMetadataValue: MetadataValue) {
		TODO("Not yet implemented")
	}

	override fun getMetadata(metadataKey: String): MutableList<MetadataValue> {
		TODO("Not yet implemented")
	}

	override fun hasMetadata(metadataKey: String): Boolean {
		TODO("Not yet implemented")
	}

	override fun removeMetadata(metadataKey: String, owningPlugin: Plugin) {
		TODO("Not yet implemented")
	}

	override fun sendMessage(message: String) {

	}

	override fun sendMessage(messages: Array<out String>) {

	}

	override fun sendMessage(sender: UUID?, message: String) {

	}

	override fun sendMessage(sender: UUID?, messages: Array<out String>) {

	}

	override fun isOp(): Boolean {
		return false
	}

	override fun setOp(value: Boolean) {
	}

	override fun isPermissionSet(name: String): Boolean {
		return false
	}

	override fun isPermissionSet(perm: Permission): Boolean {
		return false
	}

	override fun hasPermission(name: String): Boolean {
		return false
	}

	override fun hasPermission(perm: Permission): Boolean {
		return false
	}

	override fun addAttachment(plugin: Plugin, name: String, value: Boolean): PermissionAttachment {
		TODO("Not yet implemented")
	}

	override fun addAttachment(plugin: Plugin): PermissionAttachment {
		TODO("Not yet implemented")
	}

	override fun addAttachment(plugin: Plugin, name: String, value: Boolean, ticks: Int): PermissionAttachment? {
		TODO("Not yet implemented")
	}

	override fun addAttachment(plugin: Plugin, ticks: Int): PermissionAttachment? {
		TODO("Not yet implemented")
	}

	override fun removeAttachment(attachment: PermissionAttachment) {
		TODO("Not yet implemented")
	}

	override fun recalculatePermissions() {
		TODO("Not yet implemented")
	}

	override fun getEffectivePermissions(): MutableSet<PermissionAttachmentInfo> {
		return mutableSetOf()
	}

	override fun getServer(): Server {
		return (world as? MinestomWorld)?.server ?: throw Exception("not in a minestom world")
	}

	override fun getName(): String {
		val sb = StringBuilder()
		entity.customName?.let { PlainTextComponentSerializer.plainText().serialize(sb, it) }
		return sb.toString()
	}

	// Spigot adds nothing so object should be fine
	override fun spigot(): Entity.Spigot {
		return object : Entity.Spigot() {}
	}

	override fun customName(): Component? {
		return entity.customName
	}

	override fun customName(customName: Component?) {
		entity.customName = customName
	}

	override fun getCustomName(): String? {
		return name
	}

	override fun setCustomName(name: String?) {
		customName(Component.text(name ?: ""))
	}

	override fun getPersistentDataContainer(): PersistentDataContainer {
		TODO("Not yet implemented")
	}

	override fun getLocation(): Location {
		val pos = entity.position
		return Location(world, pos.x, pos.y, pos.z)
	}

	override fun getLocation(loc: Location?): Location? {
		val pos = entity.position

		loc?.x = pos.x
		loc?.y = pos.y
		loc?.z = pos.z

		return loc
	}

	override fun setVelocity(velocity: Vector) {
		entity.velocity = velocity.toMinestomVector()
	}

	override fun getVelocity(): Vector {
		return entity.velocity.toSpigotVector()
	}

	override fun getHeight(): Double {
		return entity.entityType.height()
	}

	override fun getWidth(): Double {
		return entity.entityType.width()
	}

	override fun getBoundingBox(): BoundingBox {
		return entity.boundingBox.toSpigotBoundingBox()
	}

	override fun isOnGround(): Boolean {
		return entity.isOnGround
	}

	override fun isInWater(): Boolean {
		return false
	}

	override fun getWorld(): World {
		return minestomWorld
	}

	override fun setRotation(yaw: Float, pitch: Float) {
		entity.refreshPosition(entity.position.withYaw(yaw).withPitch(pitch))
	}

	override fun teleport(location: Location): Boolean {
		entity.teleport(location.toPosition())
		return true
	}

	override fun teleport(location: Location, cause: PlayerTeleportEvent.TeleportCause): Boolean {
		return teleport(location)
	}

	override fun teleport(destination: Entity): Boolean {
		entity.teleport(destination.location.toPosition())
		return true
	}

	override fun teleport(destination: Entity, cause: PlayerTeleportEvent.TeleportCause): Boolean {
		return teleport(destination)
	}

	override fun getNearbyEntities(x: Double, y: Double, z: Double): MutableList<Entity> {
		TODO("Not yet implemented")
	}

	override fun getEntityId(): Int {
		return entity.entityId
	}

	override fun getFireTicks(): Int {
		TODO("Not yet implemented")
	}

	override fun getMaxFireTicks(): Int {
		TODO("Not yet implemented")
	}

	override fun setFireTicks(ticks: Int) {
		TODO("Not yet implemented")
	}

	override fun remove() {
		entity.remove()
	}

	override fun isDead(): Boolean {
		TODO("Not yet implemented")
	}

	override fun isValid(): Boolean {
		TODO("Not yet implemented")
	}

	override fun isPersistent(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setPersistent(persistent: Boolean) {
		TODO("Not yet implemented")
	}

	override fun getPassenger(): Entity? {
		val passenger = entity.passengers.firstOrNull() ?: return null
		return MinestomEntity(passenger, minestomWorld)
	}

	override fun setPassenger(passenger: Entity): Boolean {
		entity.passengers.forEach { entity.removePassenger(it) }
		return addPassenger(passenger)
	}

	override fun getPassengers(): MutableList<Entity> {
		return entity.passengers.map { MinestomEntity(it, minestomWorld) }.toMutableList()
	}

	override fun addPassenger(passenger: Entity): Boolean {
		if (passenger !is MinestomEntity) return false
		entity.addPassenger(passenger.entity)
		return true
	}

	override fun removePassenger(passenger: Entity): Boolean {
		if (passenger !is MinestomEntity) return false
		entity.removePassenger(passenger.entity)
		return true
	}

	override fun isEmpty(): Boolean {
		return entity.passengers.isEmpty()
	}

	override fun eject(): Boolean {
		entity.passengers.forEach { entity.removePassenger(it) }
		return true
	}

	override fun getFallDistance(): Float {
		TODO("Not yet implemented")
	}

	override fun setFallDistance(distance: Float) {
		TODO("Not yet implemented")
	}

	override fun setLastDamageCause(event: EntityDamageEvent?) {
		TODO("Not yet implemented")
	}

	override fun getLastDamageCause(): EntityDamageEvent? {
		TODO("Not yet implemented")
	}

	override fun getUniqueId(): UUID {
		return entity.uuid
	}

	override fun getTicksLived(): Int {
		return entity.aliveTicks.toInt()
	}

	override fun setTicksLived(value: Int) {
		TODO("Not yet implemented")
	}

	override fun playEffect(type: EntityEffect) {
		TODO("Not yet implemented")
	}

	override fun getType(): EntityType {
		return EntityType.fromId(entity.entityType.id())!!
	}

	override fun isInsideVehicle(): Boolean {
		TODO("Not yet implemented")
	}

	override fun leaveVehicle(): Boolean {
		TODO("Not yet implemented")
	}

	override fun getVehicle(): Entity? {
		TODO("Not yet implemented")
	}

	override fun setCustomNameVisible(flag: Boolean) {
		entity.isCustomNameVisible = flag
	}

	override fun isCustomNameVisible(): Boolean {
		return entity.isCustomNameVisible
	}

	override fun setGlowing(flag: Boolean) {
		entity.isGlowing = flag
	}

	override fun isGlowing(): Boolean {
		return entity.isGlowing
	}

	override fun setInvulnerable(flag: Boolean) {
		TODO("Not yet implemented")
	}

	override fun isInvulnerable(): Boolean {
		TODO("Not yet implemented")
	}

	override fun isSilent(): Boolean {
		return entity.isSilent
	}

	override fun setSilent(flag: Boolean) {
		entity.isSilent = flag
	}

	override fun hasGravity(): Boolean {
		return entity.hasNoGravity()
	}

	override fun setGravity(gravity: Boolean) {
		entity.setNoGravity(!gravity)
	}

	override fun getPortalCooldown(): Int {
		return 0
	}

	override fun setPortalCooldown(cooldown: Int) {
		TODO("Not yet implemented")
	}

	override fun getScoreboardTags(): MutableSet<String> {
		TODO("Not yet implemented")
	}

	override fun addScoreboardTag(tag: String): Boolean {
		TODO("Not yet implemented")
	}

	override fun removeScoreboardTag(tag: String): Boolean {
		TODO("Not yet implemented")
	}

	override fun getPistonMoveReaction(): PistonMoveReaction {
		TODO("Not yet implemented")
	}

	override fun getFacing(): BlockFace {
		TODO("Not yet implemented")
	}

	override fun getPose(): Pose {
		return entity.pose.toSpigotPose()
	}

	override fun getOrigin(): Location? {
		TODO("Not yet implemented")
	}

	override fun fromMobSpawner(): Boolean {
		TODO("Not yet implemented")
	}

	override fun getChunk(): Chunk {
		return location.chunk
	}

	override fun getEntitySpawnReason(): CreatureSpawnEvent.SpawnReason {
		return CreatureSpawnEvent.SpawnReason.CUSTOM
	}

	override fun isInRain(): Boolean {
		TODO("Not yet implemented")
	}

	override fun isInBubbleColumn(): Boolean {
		TODO("Not yet implemented")
	}

	override fun isInWaterOrRain(): Boolean {
		TODO("Not yet implemented")
	}

	override fun isInWaterOrBubbleColumn(): Boolean {
		TODO("Not yet implemented")
	}

	override fun isInWaterOrRainOrBubbleColumn(): Boolean {
		TODO("Not yet implemented")
	}

	override fun isInLava(): Boolean {
		TODO("Not yet implemented")
	}

	override fun isTicking(): Boolean {
		TODO("Not yet implemented")
	}
}
