package world.cepi.bukstom.world

import net.minestom.server.instance.Instance
import org.bukkit.*
import org.bukkit.block.Biome
import org.bukkit.block.Block
import org.bukkit.entity.*
import org.bukkit.generator.BlockPopulator
import org.bukkit.generator.ChunkGenerator
import org.bukkit.inventory.ItemStack
import org.bukkit.metadata.MetadataValue
import org.bukkit.plugin.Plugin
import org.bukkit.util.Vector
import world.cepi.bukstom.entity.MinestomItemEntity
import world.cepi.bukstom.util.toPosition
import java.io.File
import java.util.*

class MinestomWorld(val server: Server, val instance: Instance, val creator: WorldCreator?) : World {
	override fun sendPluginMessage(source: Plugin, channel: String, message: ByteArray) {
		for (player in players) {
			player.sendPluginMessage(source, channel, message)
		}
	}

	override fun getListeningPluginChannels(): MutableSet<String> {
		val channels = mutableSetOf<String>()

		for (player in players) {
			channels += player.listeningPluginChannels
		}

		return channels
	}

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

	override fun playSound(location: Location, sound: Sound, volume: Float, pitch: Float) {
		//playSound(location, sound, volume, pitch)
	}

	override fun getBlockAt(x: Int, y: Int, z: Int): Block {
		return MinestomBlock(this, instance.getBlock(x, y, z))
	}

	override fun getBlockAt(location: Location): Block {
		return getBlockAt(location.blockX, location.blockY, location.blockZ)
	}

	override fun getBlockTypeIdAt(x: Int, y: Int, z: Int): Int {
		TODO("Not yet implemented")
	}

	override fun getBlockTypeIdAt(location: Location?): Int {
		TODO("Not yet implemented")
	}

	override fun getHighestBlockYAt(x: Int, z: Int): Int {
		TODO("Not yet implemented")
	}

	override fun getHighestBlockYAt(location: Location): Int {
		TODO("Not yet implemented")
	}

	override fun getHighestBlockAt(x: Int, z: Int): Block {
		TODO("Not yet implemented")
	}

	override fun getHighestBlockAt(location: Location): Block {
		TODO("Not yet implemented")
	}

	override fun getChunkAt(x: Int, z: Int): Chunk {
		TODO("Not yet implemented")
	}

	override fun getChunkAt(location: Location): Chunk {
		TODO("Not yet implemented")
	}

	override fun getChunkAt(block: Block): Chunk {
		TODO("Not yet implemented")
	}

	override fun isChunkLoaded(chunk: Chunk): Boolean {
		return isChunkLoaded(chunk.x, chunk.z)
	}

	override fun isChunkLoaded(x: Int, z: Int): Boolean {
		return instance.isChunkLoaded(x, z)
	}

	override fun getLoadedChunks(): Array<Chunk> {
		return instance.chunks.map { MinestomChunk(this, it) }.toTypedArray()
	}

	override fun loadChunk(chunk: Chunk) {
		instance.loadChunk(chunk.x, chunk.z)
	}

	override fun loadChunk(x: Int, z: Int) {
		instance.loadChunk(x, z)
	}

	override fun loadChunk(x: Int, z: Int, generate: Boolean): Boolean {
		TODO("Not yet implemented")
	}

	override fun isChunkInUse(x: Int, z: Int): Boolean {
		TODO("Not yet implemented")
	}

	override fun unloadChunk(chunk: Chunk): Boolean {
		instance.unloadChunk(chunk.x, chunk.z)
		return true
	}

	override fun unloadChunk(x: Int, z: Int): Boolean {
		instance.unloadChunk(x, z)
		return true
	}

	// lol no save for u
	override fun unloadChunk(x: Int, z: Int, save: Boolean): Boolean {
		return unloadChunk(x, z)
	}

	override fun unloadChunk(x: Int, z: Int, save: Boolean, safe: Boolean): Boolean {
		TODO("Not yet implemented")
	}

	override fun unloadChunkRequest(x: Int, z: Int): Boolean {
		return unloadChunk(x, z)
	}

	override fun unloadChunkRequest(x: Int, z: Int, safe: Boolean): Boolean {
		TODO("Not yet implemented")
	}

	override fun regenerateChunk(x: Int, z: Int): Boolean {
		TODO("Not yet implemented")
	}

	override fun refreshChunk(x: Int, z: Int): Boolean {
		TODO("Not yet implemented")
	}

	override fun dropItem(location: Location, item: ItemStack): Item {
		val entity = net.minestom.server.entity.Entity(net.minestom.server.entity.EntityType.ITEM)
		entity.setInstance(instance, location.toPosition())
		return MinestomItemEntity(entity, this)
	}

	override fun dropItemNaturally(location: Location, item: ItemStack): Item {
		TODO("Not yet implemented")
	}

	override fun spawnArrow(location: Location, direction: Vector, speed: Float, spread: Float): Arrow {
		TODO("Not yet implemented")
	}

	override fun generateTree(location: Location, type: TreeType): Boolean {
		TODO("Not yet implemented")
	}

	override fun generateTree(loc: Location, type: TreeType, delegate: BlockChangeDelegate): Boolean {
		TODO("Not yet implemented")
	}

	override fun spawnEntity(loc: Location, type: EntityType): Entity {
		TODO("Not yet implemented")
	}

	override fun spawnCreature(loc: Location?, type: EntityType?): LivingEntity {
		TODO("Not yet implemented")
	}

	override fun spawnCreature(loc: Location?, type: CreatureType?): LivingEntity {
		TODO("Not yet implemented")
	}

	override fun strikeLightning(loc: Location): LightningStrike {
		TODO("Not yet implemented")
	}

	override fun strikeLightningEffect(loc: Location): LightningStrike {
		TODO("Not yet implemented")
	}

	override fun getEntities(): MutableList<Entity> {
		TODO("Not yet implemented")
	}

	override fun getLivingEntities(): MutableList<LivingEntity> {
		TODO("Not yet implemented")
	}

	override fun <T : Entity?> getEntitiesByClass(vararg classes: Class<T>): MutableCollection<T> {
		TODO("Not yet implemented")
	}

	override fun <T : Entity?> getEntitiesByClass(cls: Class<T>): MutableCollection<T> {
		TODO("Not yet implemented")
	}

	override fun getEntitiesByClasses(vararg classes: Class<*>): MutableCollection<Entity> {
		TODO("Not yet implemented")
	}

	override fun getPlayers(): MutableList<Player> {
		TODO("Not yet implemented")
	}

	override fun getNearbyEntities(location: Location, x: Double, y: Double, z: Double): MutableCollection<Entity> {
		TODO("Not yet implemented")
	}

	override fun getName(): String {
		TODO("Not yet implemented")
	}

	override fun getUID(): UUID {
		TODO("Not yet implemented")
	}

	override fun getSpawnLocation(): Location {
		TODO("Not yet implemented")
	}

	override fun setSpawnLocation(x: Int, y: Int, z: Int): Boolean {
		TODO("Not yet implemented")
	}

	override fun getTime(): Long {
		TODO("Not yet implemented")
	}

	override fun setTime(time: Long) {
		TODO("Not yet implemented")
	}

	override fun getFullTime(): Long {
		TODO("Not yet implemented")
	}

	override fun setFullTime(time: Long) {
		TODO("Not yet implemented")
	}

	override fun hasStorm(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setStorm(hasStorm: Boolean) {
		TODO("Not yet implemented")
	}

	override fun getWeatherDuration(): Int {
		TODO("Not yet implemented")
	}

	override fun setWeatherDuration(duration: Int) {
		TODO("Not yet implemented")
	}

	override fun isThundering(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setThundering(thundering: Boolean) {
		TODO("Not yet implemented")
	}

	override fun getThunderDuration(): Int {
		TODO("Not yet implemented")
	}

	override fun setThunderDuration(duration: Int) {
		TODO("Not yet implemented")
	}

	override fun createExplosion(x: Double, y: Double, z: Double, power: Float): Boolean {
		TODO("Not yet implemented")
	}

	override fun createExplosion(x: Double, y: Double, z: Double, power: Float, setFire: Boolean): Boolean {
		TODO("Not yet implemented")
	}

	override fun createExplosion(
		x: Double,
		y: Double,
		z: Double,
		power: Float,
		setFire: Boolean,
		breakBlocks: Boolean
	): Boolean {
		TODO("Not yet implemented")
	}

	override fun createExplosion(loc: Location, power: Float): Boolean {
		TODO("Not yet implemented")
	}

	override fun createExplosion(loc: Location, power: Float, setFire: Boolean): Boolean {
		TODO("Not yet implemented")
	}

	override fun getEnvironment(): World.Environment {
		TODO("Not yet implemented")
	}

	override fun getSeed(): Long {
		TODO("Not yet implemented")
	}

	override fun getPVP(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setPVP(pvp: Boolean) {
		TODO("Not yet implemented")
	}

	override fun getGenerator(): ChunkGenerator? {
		TODO("Not yet implemented")
	}

	override fun save() {
		TODO("Not yet implemented")
	}

	override fun getPopulators(): MutableList<BlockPopulator> {
		TODO("Not yet implemented")
	}

	override fun <T : Entity?> spawn(location: Location, clazz: Class<T>): T {
		TODO("Not yet implemented")
	}

	override fun spawnFallingBlock(location: Location, material: Material, data: Byte): FallingBlock {
		TODO("Not yet implemented")
	}

	override fun spawnFallingBlock(location: Location?, blockId: Int, blockData: Byte): FallingBlock {
		TODO("Not yet implemented")
	}

	override fun playEffect(location: Location, effect: Effect, data: Int) {
		TODO("Not yet implemented")
	}

	override fun playEffect(location: Location, effect: Effect, data: Int, radius: Int) {
		TODO("Not yet implemented")
	}

	override fun <T : Any?> playEffect(location: Location, effect: Effect, data: T?) {
		TODO("Not yet implemented")
	}

	override fun <T : Any?> playEffect(location: Location, effect: Effect, data: T?, radius: Int) {
		TODO("Not yet implemented")
	}

	override fun getEmptyChunkSnapshot(
		x: Int,
		z: Int,
		includeBiome: Boolean,
		includeBiomeTemp: Boolean
	): ChunkSnapshot {
		TODO("Not yet implemented")
	}

	override fun setSpawnFlags(allowMonsters: Boolean, allowAnimals: Boolean) {
		TODO("Not yet implemented")
	}

	override fun getAllowAnimals(): Boolean {
		TODO("Not yet implemented")
	}

	override fun getAllowMonsters(): Boolean {
		TODO("Not yet implemented")
	}

	override fun getBiome(x: Int, z: Int): Biome {
		TODO("Not yet implemented")
	}

	override fun setBiome(x: Int, z: Int, bio: Biome) {
		TODO("Not yet implemented")
	}

	override fun getTemperature(x: Int, z: Int): Double {
		TODO("Not yet implemented")
	}

	override fun getHumidity(x: Int, z: Int): Double {
		TODO("Not yet implemented")
	}

	override fun getMaxHeight(): Int {
		TODO("Not yet implemented")
	}

	override fun getSeaLevel(): Int {
		TODO("Not yet implemented")
	}

	override fun getKeepSpawnInMemory(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setKeepSpawnInMemory(keepLoaded: Boolean) {
		TODO("Not yet implemented")
	}

	override fun isAutoSave(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setAutoSave(value: Boolean) {
		TODO("Not yet implemented")
	}

	override fun setDifficulty(difficulty: Difficulty) {
		TODO("Not yet implemented")
	}

	override fun getDifficulty(): Difficulty {
		TODO("Not yet implemented")
	}

	override fun getWorldFolder(): File {
		TODO("Not yet implemented")
	}

	override fun getWorldType(): WorldType? {
		TODO("Not yet implemented")
	}

	override fun canGenerateStructures(): Boolean {
		TODO("Not yet implemented")
	}

	override fun getTicksPerAnimalSpawns(): Long {
		TODO("Not yet implemented")
	}

	override fun setTicksPerAnimalSpawns(ticksPerAnimalSpawns: Int) {
		TODO("Not yet implemented")
	}

	override fun getTicksPerMonsterSpawns(): Long {
		TODO("Not yet implemented")
	}

	override fun setTicksPerMonsterSpawns(ticksPerMonsterSpawns: Int) {
		TODO("Not yet implemented")
	}

	override fun getMonsterSpawnLimit(): Int {
		TODO("Not yet implemented")
	}

	override fun setMonsterSpawnLimit(limit: Int) {
		TODO("Not yet implemented")
	}

	override fun getAnimalSpawnLimit(): Int {
		TODO("Not yet implemented")
	}

	override fun setAnimalSpawnLimit(limit: Int) {
		TODO("Not yet implemented")
	}

	override fun getWaterAnimalSpawnLimit(): Int {
		TODO("Not yet implemented")
	}

	override fun setWaterAnimalSpawnLimit(limit: Int) {
		TODO("Not yet implemented")
	}

	override fun getAmbientSpawnLimit(): Int {
		TODO("Not yet implemented")
	}

	override fun setAmbientSpawnLimit(limit: Int) {
		TODO("Not yet implemented")
	}

	override fun getGameRules(): Array<String> {
		TODO("Not yet implemented")
	}

	override fun getGameRuleValue(rule: String?): String? {
		TODO("Not yet implemented")
	}

	override fun setGameRuleValue(rule: String, value: String): Boolean {
		TODO("Not yet implemented")
	}

	override fun isGameRule(rule: String): Boolean {
		TODO("Not yet implemented")
	}

	override fun spigot(): World.Spigot {
		TODO("Not yet implemented")
	}

	override fun getWorldBorder(): WorldBorder {
		TODO("Not yet implemented")
	}
}