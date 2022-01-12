package world.cepi.bukstom.util

import net.minestom.server.instance.ChunkPopulator
import net.minestom.server.instance.Instance
import net.minestom.server.instance.batch.ChunkBatch
import net.minestom.server.instance.block.Block
import net.minestom.server.world.biomes.Biome
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.generator.ChunkGenerator
import world.cepi.bukstom.MinestomServer
import world.cepi.bukstom.world.MinestomWorld
import java.util.*

object CustomBiomeGrid : ChunkGenerator.BiomeGrid {
	override fun getBiome(x: Int, z: Int): org.bukkit.block.Biome {
		return org.bukkit.block.Biome.PLAINS
	}

	override fun setBiome(x: Int, z: Int, bio: org.bukkit.block.Biome) {

	}
}

fun ChunkGenerator.toMinestom(world: World): net.minestom.server.instance.ChunkGenerator {
	return object : net.minestom.server.instance.ChunkGenerator {
		override fun generateChunkData(batch: ChunkBatch, chunkX: Int, chunkZ: Int) {
			val data = this@toMinestom.generateChunkData(world, Random(), chunkX, chunkZ, CustomBiomeGrid)
			for (x in 0 until 16) {
				for (z in 0 until 16) {
					for (y in 0 until 255) {
						Block.fromBlockId(data.getType(x, y, z).id)?.let { batch.setBlock(x, y, z, it) }
					}
				}
			}
		}

		override fun fillBiomes(biomes: Array<out Biome>, chunkX: Int, chunkZ: Int) {

		}

		override fun getPopulators(): MutableList<ChunkPopulator> {
			return mutableListOf()
		}
	}
}

fun Instance.toBukkit() = MinestomWorld(Bukkit.getServer() as MinestomServer, this, null)