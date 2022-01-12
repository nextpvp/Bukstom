package world.cepi.bukstom.entity

import org.bukkit.*
import org.bukkit.block.Block
import org.bukkit.conversations.Conversation
import org.bukkit.conversations.ConversationAbandonedEvent
import org.bukkit.entity.*
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.inventory.*
import org.bukkit.map.MapView
import org.bukkit.permissions.Permission
import org.bukkit.plugin.Plugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.util.Vector
import world.cepi.bukstom.MinestomServer
import world.cepi.bukstom.util.teleport
import world.cepi.bukstom.util.toMinestomVector
import world.cepi.bukstom.world.MinestomWorld
import java.net.InetSocketAddress
import java.util.*

class MinestomPlayer(
	val minestomPlayer: net.minestom.server.entity.Player,
	minestomWorld: MinestomWorld,
	val internalServer: MinestomServer
) :
	MinestomEntity(minestomPlayer, minestomWorld), Player {

	override fun hasPermission(name: String): Boolean {
		return minestomPlayer.hasPermission(name)
	}

	override fun hasPermission(perm: Permission): Boolean {
		return minestomPlayer.hasPermission(perm.name)
	}

	override fun recalculatePermissions() {

	}

	override fun sendMessage(message: String) {
		minestomPlayer.sendMessage(message)
	}

	override fun sendMessage(messages: Array<out String>) {
		minestomPlayer.sendMessage(messages)
	}

	override fun getServer(): Server {
		return internalServer
	}

	override fun getName(): String {
		return minestomPlayer.username
	}

	override fun setVelocity(velocity: Vector) {
		minestomPlayer.velocity = velocity.toMinestomVector()
	}

	override fun getWorld(): World =
		minestomPlayer.instance?.let { MinestomWorld(server, it, null) } ?: internalServer.worlds.first()

	override fun teleport(location: Location): Boolean {
		minestomPlayer.teleport(location)
		return true
	}

	override fun teleport(location: Location, cause: PlayerTeleportEvent.TeleportCause): Boolean {
		minestomPlayer.teleport(location)
		return true
	}

	override fun teleport(destination: Entity): Boolean {
		minestomPlayer.teleport(destination.location)
		return true
	}

	override fun teleport(destination: Entity, cause: PlayerTeleportEvent.TeleportCause): Boolean {
		minestomPlayer.teleport(destination.location)
		return true
	}

	override fun getEntityId() = minestomPlayer.entityId

	override fun setFireTicks(ticks: Int) {
		minestomPlayer.setFireForDuration(ticks)
	}

	override fun remove() = minestomPlayer.remove()

	override fun isDead(): Boolean {
		return minestomPlayer.isDead
	}

	override fun getUniqueId(): UUID {
		return minestomPlayer.uuid
	}

	override fun getTicksLived() = minestomPlayer.aliveTicks.toInt()

	override fun setTicksLived(value: Int) {
		TODO("Not yet implemented")
	}

	override fun playEffect(loc: Location?, effect: Effect?, data: Int) {
		TODO("Not yet implemented")
	}

	override fun <T : Any?> playEffect(loc: Location?, effect: Effect?, data: T) {
		TODO("Not yet implemented")
	}

	override fun getType(): EntityType {
		return EntityType.PLAYER
	}

	override fun spigot(): Player.Spigot {
		TODO("Not yet implemented")
	}

	override fun damage(amount: Double) {
		TODO("Not yet implemented")
	}

	override fun damage(amount: Double, source: Entity?) {
		TODO("Not yet implemented")
	}

	override fun _INVALID_damage(amount: Int) {
		TODO("Not yet implemented")
	}

	override fun _INVALID_damage(amount: Int, source: Entity?) {
		TODO("Not yet implemented")
	}

	override fun getHealth(): Double {
		TODO("Not yet implemented")
	}

	override fun _INVALID_getHealth(): Int {
		TODO("Not yet implemented")
	}

	override fun setHealth(health: Double) {
		TODO("Not yet implemented")
	}

	override fun _INVALID_setHealth(health: Int) {
		TODO("Not yet implemented")
	}

	override fun getMaxHealth(): Double {
		TODO("Not yet implemented")
	}

	override fun _INVALID_getMaxHealth(): Int {
		TODO("Not yet implemented")
	}

	override fun setMaxHealth(health: Double) {
		TODO("Not yet implemented")
	}

	override fun _INVALID_setMaxHealth(health: Int) {
		TODO("Not yet implemented")
	}

	override fun resetMaxHealth() {
		TODO("Not yet implemented")
	}

	override fun <T : Projectile?> launchProjectile(projectile: Class<out T>?): T {
		TODO("Not yet implemented")
	}

	override fun <T : Projectile?> launchProjectile(projectile: Class<out T>?, velocity: Vector?): T {
		TODO("Not yet implemented")
	}

	override fun getEyeHeight(): Double {
		TODO("Not yet implemented")
	}

	override fun getEyeHeight(ignoreSneaking: Boolean): Double {
		TODO("Not yet implemented")
	}

	override fun getEyeLocation(): Location {
		TODO("Not yet implemented")
	}

	override fun getLineOfSight(transparent: HashSet<Byte>?, maxDistance: Int): MutableList<Block> {
		TODO("Not yet implemented")
	}

	override fun getLineOfSight(transparent: MutableSet<Material>?, maxDistance: Int): MutableList<Block> {
		TODO("Not yet implemented")
	}

	override fun getTargetBlock(transparent: HashSet<Byte>?, maxDistance: Int): Block {
		TODO("Not yet implemented")
	}

	override fun getTargetBlock(transparent: MutableSet<Material>?, maxDistance: Int): Block {
		TODO("Not yet implemented")
	}

	override fun getLastTwoTargetBlocks(transparent: HashSet<Byte>?, maxDistance: Int): MutableList<Block> {
		TODO("Not yet implemented")
	}

	override fun getLastTwoTargetBlocks(transparent: MutableSet<Material>?, maxDistance: Int): MutableList<Block> {
		TODO("Not yet implemented")
	}

	override fun throwEgg(): Egg {
		TODO("Not yet implemented")
	}

	override fun throwSnowball(): Snowball {
		TODO("Not yet implemented")
	}

	override fun shootArrow(): Arrow {
		TODO("Not yet implemented")
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

	override fun hasLineOfSight(other: Entity?): Boolean {
		TODO("Not yet implemented")
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

	override fun getLeashHolder(): Entity {
		TODO("Not yet implemented")
	}

	override fun setLeashHolder(holder: Entity?): Boolean {
		TODO("Not yet implemented")
	}

	override fun getInventory(): PlayerInventory {
		TODO("Not yet implemented")
	}

	override fun getEnderChest(): Inventory {
		TODO("Not yet implemented")
	}

	override fun setWindowProperty(prop: InventoryView.Property?, value: Int): Boolean {
		TODO("Not yet implemented")
	}

	override fun getOpenInventory(): InventoryView {
		TODO("Not yet implemented")
	}

	override fun openInventory(inventory: Inventory?): InventoryView {
		TODO("Not yet implemented")
	}

	override fun openInventory(inventory: InventoryView?) {
		TODO("Not yet implemented")
	}

	override fun openWorkbench(location: Location?, force: Boolean): InventoryView {
		TODO("Not yet implemented")
	}

	override fun openEnchanting(location: Location?, force: Boolean): InventoryView {
		TODO("Not yet implemented")
	}

	override fun closeInventory() {
		TODO("Not yet implemented")
	}

	override fun getItemInHand(): ItemStack {
		TODO("Not yet implemented")
	}

	override fun setItemInHand(item: ItemStack?) {
		TODO("Not yet implemented")
	}

	override fun getItemOnCursor(): ItemStack {
		TODO("Not yet implemented")
	}

	override fun setItemOnCursor(item: ItemStack?) {
		TODO("Not yet implemented")
	}

	override fun isSleeping(): Boolean {
		TODO("Not yet implemented")
	}

	override fun getSleepTicks(): Int {
		TODO("Not yet implemented")
	}

	override fun getGameMode(): GameMode {
		TODO("Not yet implemented")
	}

	override fun setGameMode(mode: GameMode?) {
		TODO("Not yet implemented")
	}

	override fun isBlocking(): Boolean {
		TODO("Not yet implemented")
	}

	override fun getExpToLevel(): Int {
		TODO("Not yet implemented")
	}

	override fun isConversing(): Boolean {
		TODO("Not yet implemented")
	}

	override fun acceptConversationInput(input: String?) {
		TODO("Not yet implemented")
	}

	override fun beginConversation(conversation: Conversation?): Boolean {
		TODO("Not yet implemented")
	}

	override fun abandonConversation(conversation: Conversation?) {
		TODO("Not yet implemented")
	}

	override fun abandonConversation(conversation: Conversation?, details: ConversationAbandonedEvent?) {
		TODO("Not yet implemented")
	}

	override fun sendRawMessage(message: String?) {
		TODO("Not yet implemented")
	}

	override fun serialize(): MutableMap<String, Any> {
		TODO("Not yet implemented")
	}

	override fun isOnline(): Boolean {
		TODO("Not yet implemented")
	}

	override fun isBanned(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setBanned(banned: Boolean) {
		TODO("Not yet implemented")
	}

	override fun isWhitelisted(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setWhitelisted(value: Boolean) {
		TODO("Not yet implemented")
	}

	override fun getPlayer(): Player {
		TODO("Not yet implemented")
	}

	override fun getFirstPlayed(): Long {
		TODO("Not yet implemented")
	}

	override fun getLastPlayed(): Long {
		TODO("Not yet implemented")
	}

	override fun hasPlayedBefore(): Boolean {
		TODO("Not yet implemented")
	}

	override fun getBedSpawnLocation(): Location {
		TODO("Not yet implemented")
	}

	override fun sendPluginMessage(source: Plugin?, channel: String?, message: ByteArray?) {
		TODO("Not yet implemented")
	}

	override fun getListeningPluginChannels(): MutableSet<String> {
		TODO("Not yet implemented")
	}

	override fun getDisplayName(): String {
		TODO("Not yet implemented")
	}

	override fun setDisplayName(name: String?) {
		TODO("Not yet implemented")
	}

	override fun getPlayerListName(): String {
		TODO("Not yet implemented")
	}

	override fun setPlayerListName(name: String?) {
		TODO("Not yet implemented")
	}

	override fun setCompassTarget(loc: Location?) {
		TODO("Not yet implemented")
	}

	override fun getCompassTarget(): Location {
		TODO("Not yet implemented")
	}

	override fun getAddress(): InetSocketAddress {
		TODO("Not yet implemented")
	}

	override fun kickPlayer(message: String?) {
		TODO("Not yet implemented")
	}

	override fun chat(msg: String?) {
		TODO("Not yet implemented")
	}

	override fun performCommand(command: String?): Boolean {
		TODO("Not yet implemented")
	}

	override fun isSneaking(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setSneaking(sneak: Boolean) {
		TODO("Not yet implemented")
	}

	override fun isSprinting(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setSprinting(sprinting: Boolean) {
		TODO("Not yet implemented")
	}

	override fun saveData() {
		TODO("Not yet implemented")
	}

	override fun loadData() {
		TODO("Not yet implemented")
	}

	override fun setSleepingIgnored(isSleeping: Boolean) {
		TODO("Not yet implemented")
	}

	override fun isSleepingIgnored(): Boolean {
		TODO("Not yet implemented")
	}

	override fun playNote(loc: Location?, instrument: Byte, note: Byte) {
		TODO("Not yet implemented")
	}

	override fun playNote(loc: Location?, instrument: Instrument?, note: Note?) {
		TODO("Not yet implemented")
	}

	override fun playSound(location: Location?, sound: Sound?, volume: Float, pitch: Float) {
		TODO("Not yet implemented")
	}

	override fun playSound(location: Location?, sound: String?, volume: Float, pitch: Float) {
		TODO("Not yet implemented")
	}

	override fun sendBlockChange(loc: Location?, material: Material?, data: Byte) {
		TODO("Not yet implemented")
	}

	override fun sendBlockChange(loc: Location?, material: Int, data: Byte) {
		TODO("Not yet implemented")
	}

	override fun sendChunkChange(loc: Location?, sx: Int, sy: Int, sz: Int, data: ByteArray?): Boolean {
		TODO("Not yet implemented")
	}

	override fun sendSignChange(loc: Location?, lines: Array<out String>?) {
		TODO("Not yet implemented")
	}

	override fun sendMap(map: MapView?) {
		TODO("Not yet implemented")
	}

	override fun updateInventory() {
		TODO("Not yet implemented")
	}

	override fun awardAchievement(achievement: Achievement?) {
		TODO("Not yet implemented")
	}

	override fun removeAchievement(achievement: Achievement?) {
		TODO("Not yet implemented")
	}

	override fun hasAchievement(achievement: Achievement?): Boolean {
		TODO("Not yet implemented")
	}

	override fun incrementStatistic(statistic: Statistic?) {
		TODO("Not yet implemented")
	}

	override fun incrementStatistic(statistic: Statistic?, amount: Int) {
		TODO("Not yet implemented")
	}

	override fun incrementStatistic(statistic: Statistic?, material: Material?) {
		TODO("Not yet implemented")
	}

	override fun incrementStatistic(statistic: Statistic?, material: Material?, amount: Int) {
		TODO("Not yet implemented")
	}

	override fun incrementStatistic(statistic: Statistic?, entityType: EntityType?) {
		TODO("Not yet implemented")
	}

	override fun incrementStatistic(statistic: Statistic?, entityType: EntityType?, amount: Int) {
		TODO("Not yet implemented")
	}

	override fun decrementStatistic(statistic: Statistic?) {
		TODO("Not yet implemented")
	}

	override fun decrementStatistic(statistic: Statistic?, amount: Int) {
		TODO("Not yet implemented")
	}

	override fun decrementStatistic(statistic: Statistic?, material: Material?) {
		TODO("Not yet implemented")
	}

	override fun decrementStatistic(statistic: Statistic?, material: Material?, amount: Int) {
		TODO("Not yet implemented")
	}

	override fun decrementStatistic(statistic: Statistic?, entityType: EntityType?) {
		TODO("Not yet implemented")
	}

	override fun decrementStatistic(statistic: Statistic?, entityType: EntityType?, amount: Int) {
		TODO("Not yet implemented")
	}

	override fun setStatistic(statistic: Statistic?, newValue: Int) {
		TODO("Not yet implemented")
	}

	override fun setStatistic(statistic: Statistic?, material: Material?, newValue: Int) {
		TODO("Not yet implemented")
	}

	override fun setStatistic(statistic: Statistic?, entityType: EntityType?, newValue: Int) {
		TODO("Not yet implemented")
	}

	override fun getStatistic(statistic: Statistic?): Int {
		TODO("Not yet implemented")
	}

	override fun getStatistic(statistic: Statistic?, material: Material?): Int {
		TODO("Not yet implemented")
	}

	override fun getStatistic(statistic: Statistic?, entityType: EntityType?): Int {
		TODO("Not yet implemented")
	}

	override fun setPlayerTime(time: Long, relative: Boolean) {
		TODO("Not yet implemented")
	}

	override fun getPlayerTime(): Long {
		TODO("Not yet implemented")
	}

	override fun getPlayerTimeOffset(): Long {
		TODO("Not yet implemented")
	}

	override fun isPlayerTimeRelative(): Boolean {
		TODO("Not yet implemented")
	}

	override fun resetPlayerTime() {
		TODO("Not yet implemented")
	}

	override fun setPlayerWeather(type: WeatherType?) {
		TODO("Not yet implemented")
	}

	override fun getPlayerWeather(): WeatherType {
		TODO("Not yet implemented")
	}

	override fun resetPlayerWeather() {
		TODO("Not yet implemented")
	}

	override fun giveExp(amount: Int) {
		TODO("Not yet implemented")
	}

	override fun giveExpLevels(amount: Int) {
		TODO("Not yet implemented")
	}

	override fun getExp(): Float {
		TODO("Not yet implemented")
	}

	override fun setExp(exp: Float) {
		TODO("Not yet implemented")
	}

	override fun getLevel(): Int {
		TODO("Not yet implemented")
	}

	override fun setLevel(level: Int) {
		TODO("Not yet implemented")
	}

	override fun getTotalExperience(): Int {
		TODO("Not yet implemented")
	}

	override fun setTotalExperience(exp: Int) {
		TODO("Not yet implemented")
	}

	override fun getExhaustion(): Float {
		TODO("Not yet implemented")
	}

	override fun setExhaustion(value: Float) {
		TODO("Not yet implemented")
	}

	override fun getSaturation(): Float {
		TODO("Not yet implemented")
	}

	override fun setSaturation(value: Float) {
		TODO("Not yet implemented")
	}

	override fun getFoodLevel(): Int {
		TODO("Not yet implemented")
	}

	override fun setFoodLevel(value: Int) {
		TODO("Not yet implemented")
	}

	override fun setBedSpawnLocation(location: Location?) {
		TODO("Not yet implemented")
	}

	override fun setBedSpawnLocation(location: Location?, force: Boolean) {
		TODO("Not yet implemented")
	}

	override fun getAllowFlight(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setAllowFlight(flight: Boolean) {
		TODO("Not yet implemented")
	}

	override fun hidePlayer(player: Player?) {
		TODO("Not yet implemented")
	}

	override fun showPlayer(player: Player?) {
		TODO("Not yet implemented")
	}

	override fun canSee(player: Player?): Boolean {
		TODO("Not yet implemented")
	}

	override fun isFlying(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setFlying(value: Boolean) {
		TODO("Not yet implemented")
	}

	override fun setFlySpeed(value: Float) {
		TODO("Not yet implemented")
	}

	override fun setWalkSpeed(value: Float) {
		TODO("Not yet implemented")
	}

	override fun getFlySpeed(): Float {
		TODO("Not yet implemented")
	}

	override fun getWalkSpeed(): Float {
		TODO("Not yet implemented")
	}

	override fun setTexturePack(url: String?) {
		TODO("Not yet implemented")
	}

	override fun setResourcePack(url: String?) {
		TODO("Not yet implemented")
	}

	override fun getScoreboard(): Scoreboard {
		TODO("Not yet implemented")
	}

	override fun setScoreboard(scoreboard: Scoreboard?) {
		TODO("Not yet implemented")
	}

	override fun isHealthScaled(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setHealthScaled(scale: Boolean) {
		TODO("Not yet implemented")
	}

	override fun setHealthScale(scale: Double) {
		TODO("Not yet implemented")
	}

	override fun getHealthScale(): Double {
		TODO("Not yet implemented")
	}

	override fun getSpectatorTarget(): Entity {
		TODO("Not yet implemented")
	}

	override fun setSpectatorTarget(entity: Entity?) {
		TODO("Not yet implemented")
	}

	override fun sendTitle(title: String?, subtitle: String?) {
		TODO("Not yet implemented")
	}

	override fun resetTitle() {
		TODO("Not yet implemented")
	}

	/*override fun canSee(player: Player): Boolean {
		return (player as? MinestomPlayer)?.let { minestomPlayer.isViewer(it.minestomPlayer) } ?: false
	}*/
}