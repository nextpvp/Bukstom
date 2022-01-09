package world.cepi.bukstom.world

import org.bukkit.Chunk
import org.bukkit.ChunkSnapshot
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.block.BlockState
import org.bukkit.block.data.BlockData
import org.bukkit.entity.Entity
import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.plugin.Plugin
import java.util.function.Predicate

class MinestomChunk(val minestomWorld: MinestomWorld, val minestomChunk: net.minestom.server.instance.Chunk) : Chunk {
	override fun getPersistentDataContainer(): PersistentDataContainer {
		TODO("Not yet implemented")
	}

	override fun getX(): Int {
		return minestomChunk.chunkX
	}

	override fun getZ(): Int {
		return minestomChunk.chunkZ
	}

	override fun getWorld(): World {
		return minestomWorld
	}

	override fun getBlock(x: Int, y: Int, z: Int): Block {
		return MinestomBlock(minestomWorld, minestomChunk.getBlock(x, y, z))
	}

	override fun getChunkSnapshot(): ChunkSnapshot {
		TODO("Not yet implemented")
	}

	override fun getChunkSnapshot(
		includeMaxblocky: Boolean,
		includeBiome: Boolean,
		includeBiomeTempRain: Boolean
	): ChunkSnapshot {
		TODO("Not yet implemented")
	}

	override fun getEntities(): Array<Entity> {
		return world.entities.filter { it.location.chunk == this }.toTypedArray()
	}

	override fun getTileEntities(useSnapshot: Boolean): Array<BlockState> {
		TODO("Not yet implemented")
	}

	override fun getTileEntities(
		blockPredicate: Predicate<Block>,
		useSnapshot: Boolean
	): MutableCollection<BlockState> {
		TODO("Not yet implemented")
	}

	//For now if we have a chunk its loaded
	override fun isLoaded(): Boolean {
		return true
	}

	override fun load(generate: Boolean): Boolean {
		TODO("Not yet implemented")
	}

	override fun load(): Boolean {
		TODO("Not yet implemented")
	}

	override fun unload(save: Boolean): Boolean {
		TODO("Not yet implemented")
	}

	override fun unload(): Boolean {
		TODO("Not yet implemented")
	}

	override fun isSlimeChunk(): Boolean {
		TODO("Not yet implemented")
	}

	override fun isForceLoaded(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setForceLoaded(forced: Boolean) {
		TODO("Not yet implemented")
	}

	override fun addPluginChunkTicket(plugin: Plugin): Boolean {
		TODO("Not yet implemented")
	}

	override fun removePluginChunkTicket(plugin: Plugin): Boolean {
		TODO("Not yet implemented")
	}

	override fun getPluginChunkTickets(): MutableCollection<Plugin> {
		TODO("Not yet implemented")
	}

	override fun getInhabitedTime(): Long {
		TODO("Not yet implemented")
	}

	override fun setInhabitedTime(ticks: Long) {
		TODO("Not yet implemented")
	}

	override fun contains(block: BlockData): Boolean {
		TODO("Not yet implemented")
	}
}