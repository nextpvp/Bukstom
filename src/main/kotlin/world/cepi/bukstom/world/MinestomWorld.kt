package world.cepi.bukstom.world

import net.minestom.server.coordinate.Point
import net.minestom.server.data.Data
import net.minestom.server.entity.ItemEntity
import net.minestom.server.instance.Explosion
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
import world.cepi.bukstom.entity.MinestomFallingBlock
import world.cepi.bukstom.entity.MinestomItem
import world.cepi.bukstom.util.toBukkit
import world.cepi.bukstom.util.toMinestom
import world.cepi.bukstom.util.toPosition
import java.io.File
import java.util.*

class MinestomWorld(val server: Server, val instance: Instance, val creator: WorldCreator?) : World {

	init {
		instance.setExplosionSupplier { centerX, centerY, centerZ, strength, _ ->
			return@setExplosionSupplier object : Explosion(centerX, centerY, centerZ, strength) {
				override fun prepare(instance: Instance?): MutableList<Point> {
					return mutableListOf()
				}
			}
		}
	}

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
		for (player in players) {
			player.playSound(location, sound, volume, pitch)
		}
	}

	override fun getBlockAt(x: Int, y: Int, z: Int): Block {
		return MinestomBlock(this, instance.getBlock(x, y, z))
	}

	override fun getBlockAt(location: Location): Block {
		return getBlockAt(location.blockX, location.blockY, location.blockZ)
	}

	override fun getBlockTypeIdAt(x: Int, y: Int, z: Int): Int {
		return getBlockAt(x, y, z).typeId
	}

	override fun getBlockTypeIdAt(location: Location): Int {
		return getBlockTypeIdAt(location.blockX, location.blockY, location.blockZ)
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

	override fun getChunkAt(x: Int, z: Int): MinestomChunk {
		val chunk = instance.getChunk(x, z) ?: throw Exception("Chunk not loaded")
		return MinestomChunk(this, chunk)
	}

	override fun getChunkAt(location: Location): Chunk {
		return getChunkAt(location.blockX, location.blockZ)
	}

	override fun getChunkAt(block: Block): Chunk {
		return getChunkAt(block.location)
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
		instance.loadChunk(x, z)
		return true
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
		return unloadChunk(x, z)
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
		val entity = ItemEntity(item.toMinestom())
		entity.setInstance(instance, location.toPosition())
		return MinestomItem(entity, this)
	}

	override fun dropItemNaturally(location: Location, item: ItemStack): Item {
		return dropItem(location, item)
	}

	override fun spawnArrow(location: Location, direction: Vector, speed: Float, spread: Float): Arrow {
		return spawnEntity(location, EntityType.ARROW) as Arrow
	}

	override fun generateTree(location: Location, type: TreeType): Boolean {
		TODO("Not yet implemented")
	}

	override fun generateTree(loc: Location, type: TreeType, delegate: BlockChangeDelegate): Boolean {
		TODO("Not yet implemented")
	}

	override fun spawnEntity(loc: Location, type: EntityType): Entity {
		val ent = net.minestom.server.entity.Entity(type.toMinestom())
		ent.setInstance(instance, loc.toPosition())
		return ent.toBukkit()
	}

	override fun spawnCreature(loc: Location, type: EntityType): LivingEntity {
		return spawnEntity(loc, type) as LivingEntity
	}

	override fun spawnCreature(loc: Location?, type: CreatureType?): LivingEntity {
		throw Exception()
	}

	override fun strikeLightning(loc: Location): LightningStrike {
		return spawnEntity(loc, EntityType.LIGHTNING) as LightningStrike
	}

	override fun strikeLightningEffect(loc: Location): LightningStrike {
		return strikeLightning(loc)
	}

	override fun getEntities(): MutableList<Entity> {
		return instance.entities.map { it.toBukkit() }.toMutableList()
	}

	override fun getLivingEntities(): MutableList<LivingEntity> {
		return entities.filterIsInstance<LivingEntity>().toMutableList()
	}

	// What is the point of this???? The fuck
	override fun <T : Entity?> getEntitiesByClass(vararg classes: Class<T>): MutableCollection<T> {
		val ents = mutableListOf<T>()

		for(clazz in classes) {
			ents += getEntitiesByClass(clazz)
		}

		return ents
	}

	override fun <T : Entity?> getEntitiesByClass(cls: Class<T>): MutableCollection<T> {
		return entities.filter { it.javaClass.isAssignableFrom(cls) }.toMutableList() as MutableCollection<T>
	}

	// Types are a pain
	override fun getEntitiesByClasses(vararg classes: Class<*>): MutableCollection<Entity> {
		val ents = mutableListOf<Entity>()

		for(clazz in classes) {
			ents += entities.filter { it.javaClass.isAssignableFrom(clazz) }
		}

		return ents
	}

	override fun getPlayers(): MutableList<Player> {
		return instance.players.map { it.toBukkit() }.toMutableList()
	}

	override fun getNearbyEntities(location: Location, x: Double, y: Double, z: Double): MutableCollection<Entity> {
		TODO("Not yet implemented")
	}

	override fun getName(): String {
		return instance.uniqueId.toString()
	}

	override fun getUID(): UUID {
		return instance.uniqueId
	}

	override fun getSpawnLocation(): Location {
		TODO("Not yet implemented")
	}

	override fun setSpawnLocation(x: Int, y: Int, z: Int): Boolean {
		TODO("Not yet implemented")
	}

	override fun getTime(): Long {
		return instance.time
	}

	override fun setTime(time: Long) {
		instance.time = time
	}

	override fun getFullTime(): Long {
		return instance.time
	}

	override fun setFullTime(time: Long) {
		instance.time = time
	}

	//TODO: Implement when weather is added: https://github.com/Minestom/Minestom/pull/576
	override fun hasStorm(): Boolean {
		return false
	}

	override fun setStorm(hasStorm: Boolean) {

	}

	override fun getWeatherDuration(): Int {
		return 0
	}

	override fun setWeatherDuration(duration: Int) {

	}

	override fun isThundering(): Boolean {
		return false
	}

	override fun setThundering(thundering: Boolean) {

	}

	override fun getThunderDuration(): Int {
		return 0
	}

	override fun setThunderDuration(duration: Int) {

	}

	override fun createExplosion(x: Double, y: Double, z: Double, power: Float): Boolean {
		val explosion =
			instance.explosionSupplier?.createExplosion(x.toFloat(), y.toFloat(), z.toFloat(), power, Data.EMPTY)
				?: return false
		explosion.apply(instance)
		return true
	}

	override fun createExplosion(x: Double, y: Double, z: Double, power: Float, setFire: Boolean): Boolean {
		return createExplosion(x, y, z, power)
	}

	override fun createExplosion(
		x: Double,
		y: Double,
		z: Double,
		power: Float,
		setFire: Boolean,
		breakBlocks: Boolean
	): Boolean {
		return createExplosion(x, y, z, power)
	}

	override fun createExplosion(loc: Location, power: Float): Boolean {
		return createExplosion(loc.x, loc.y, loc.z, power)
	}

	override fun createExplosion(loc: Location, power: Float, setFire: Boolean): Boolean {
		return createExplosion(loc.x, loc.y, loc.z, power)
	}

	override fun getEnvironment(): World.Environment {
		return World.Environment.NORMAL
	}

	override fun getSeed(): Long {
		throw Exception("Not implementable")
	}

	override fun getPVP(): Boolean {
		// Maybe we can implement with minestompvp
		TODO("Not yet implemented")
	}

	override fun setPVP(pvp: Boolean) {

	}

	override fun getGenerator(): ChunkGenerator? {
		return null
	}

	override fun save() {

	}

	override fun getPopulators(): MutableList<BlockPopulator> {
		return mutableListOf()
	}

	override fun <T : Entity?> spawn(location: Location, clazz: Class<T>): T {
		TODO("Not yet implemented")
	}

	override fun spawnFallingBlock(location: Location, material: Material, data: Byte): FallingBlock {
		val block = spawnEntity(location, EntityType.FALLING_BLOCK) as MinestomFallingBlock

		block.material = material
		block.blockData = data
		return block
	}

	override fun spawnFallingBlock(location: Location, blockId: Int, blockData: Byte): FallingBlock {
		return spawnFallingBlock(location, Material.getMaterial(blockId), blockData)
	}

	override fun playEffect(location: Location, effect: Effect, data: Int) {
		playEffect(location, effect, data, -1)
	}

	override fun playEffect(location: Location, effect: Effect, data: Int, radius: Int) {
		playEffect(location, effect, data, radius)
	}

	override fun <T : Any?> playEffect(location: Location, effect: Effect, data: T?) {
		playEffect(location, effect, data, -1)
	}

	override fun <T : Any?> playEffect(location: Location, effect: Effect, data: T?, radius: Int) {
		for(player in players.filter { if(radius != -1) location.distance(it.location) < radius else true }) {
			player.playEffect(location, effect, data)
		}
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
		return Biome.PLAINS
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
		return MinestomWorldBorder(instance.worldBorder)
	}
}