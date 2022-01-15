package world.cepi.bukstom.entity

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import net.minestom.server.entity.LivingEntity
import org.bukkit.EntityEffect
import org.bukkit.Location
import org.bukkit.Server
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.metadata.MetadataValue
import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionAttachment
import org.bukkit.permissions.PermissionAttachmentInfo
import org.bukkit.plugin.Plugin
import org.bukkit.util.Vector
import world.cepi.bukstom.util.toMinestomVector
import world.cepi.bukstom.util.toPosition
import world.cepi.bukstom.util.toSpigotVector
import world.cepi.bukstom.world.MinestomWorld
import java.util.*

open class MinestomEntity<T : net.minestom.server.entity.Entity>(val entity: T, val minestomWorld: MinestomWorld) :
	Entity {
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

	override fun getCustomName(): String? {
		return name
	}

	override fun setCustomName(name: String?) {
		entity.customName = Component.text(name ?: "")
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

	override fun isOnGround(): Boolean {
		return entity.isOnGround
	}

	override fun getWorld(): World {
		return minestomWorld
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
		return if (entity.isOnFire) {
			1
		} else {
			0
		}
	}

	override fun getMaxFireTicks(): Int {
		TODO("Not yet implemented")
	}

	override fun setFireTicks(ticks: Int) {
		entity.isOnFire = ticks > 0
	}

	override fun remove() {
		entity.remove()
	}

	override fun isDead(): Boolean {
		return (entity as? LivingEntity)?.isDead ?: false
	}

	override fun isValid(): Boolean {
		TODO("Not yet implemented")
	}

	override fun getPassenger(): Entity? {
		val passenger = entity.passengers.firstOrNull() ?: return null
		return MinestomEntity(passenger, minestomWorld)
	}

	override fun setPassenger(passenger: Entity): Boolean {
		entity.passengers.forEach { entity.removePassenger(it) }
		return entity.passengers.add((passenger as MinestomEntity<*>).entity)
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

	companion object {
		operator fun invoke(entity: net.minestom.server.entity.Entity, minestomWorld: MinestomWorld) =
			MinestomEntity<net.minestom.server.entity.Entity>(entity, minestomWorld)
	}
}