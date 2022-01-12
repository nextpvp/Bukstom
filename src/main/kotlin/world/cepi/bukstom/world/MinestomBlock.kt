package world.cepi.bukstom.world

import org.bukkit.Chunk
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.*
import org.bukkit.inventory.ItemStack
import org.bukkit.metadata.MetadataValue
import org.bukkit.plugin.Plugin

class MinestomBlock(val internalWorld: World, val block: net.minestom.server.instance.block.Block) : Block {
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

	override fun getData(): Byte {
		TODO("Not yet implemented")
	}

	override fun getRelative(modX: Int, modY: Int, modZ: Int): Block {
		TODO("Not yet implemented")
	}

	override fun getRelative(face: BlockFace): Block {
		TODO("Not yet implemented")
	}

	override fun getRelative(face: BlockFace, distance: Int): Block {
		TODO("Not yet implemented")
	}

	override fun getType(): Material {
		TODO("Not yet implemented")
	}

	override fun getTypeId(): Int {
		TODO("Not yet implemented")
	}

	override fun getLightLevel(): Byte {
		TODO("Not yet implemented")
	}

	override fun getLightFromSky(): Byte {
		TODO("Not yet implemented")
	}

	override fun getLightFromBlocks(): Byte {
		TODO("Not yet implemented")
	}

	override fun getWorld(): World {
		TODO("Not yet implemented")
	}

	override fun getX(): Int {
		TODO("Not yet implemented")
	}

	override fun getY(): Int {
		TODO("Not yet implemented")
	}

	override fun getZ(): Int {
		TODO("Not yet implemented")
	}

	override fun getLocation(): Location {
		TODO("Not yet implemented")
	}

	override fun getLocation(loc: Location?): Location? {
		TODO("Not yet implemented")
	}

	override fun getChunk(): Chunk {
		TODO("Not yet implemented")
	}

	override fun setData(data: Byte) {
		TODO("Not yet implemented")
	}

	override fun setData(data: Byte, applyPhysics: Boolean) {
		TODO("Not yet implemented")
	}

	override fun setType(type: Material) {
		TODO("Not yet implemented")
	}

	override fun setType(type: Material, applyPhysics: Boolean) {
		TODO("Not yet implemented")
	}

	override fun setTypeId(type: Int): Boolean {
		TODO("Not yet implemented")
	}

	override fun setTypeId(type: Int, applyPhysics: Boolean): Boolean {
		TODO("Not yet implemented")
	}

	override fun setTypeIdAndData(type: Int, data: Byte, applyPhysics: Boolean): Boolean {
		TODO("Not yet implemented")
	}

	override fun getFace(block: Block): BlockFace? {
		TODO("Not yet implemented")
	}

	override fun getState(): BlockState {
		TODO("Not yet implemented")
	}

	override fun getBiome(): Biome {
		TODO("Not yet implemented")
	}

	override fun setBiome(bio: Biome) {
		TODO("Not yet implemented")
	}

	override fun isBlockPowered(): Boolean {
		TODO("Not yet implemented")
	}

	override fun isBlockIndirectlyPowered(): Boolean {
		TODO("Not yet implemented")
	}

	override fun isBlockFacePowered(face: BlockFace): Boolean {
		TODO("Not yet implemented")
	}

	override fun isBlockFaceIndirectlyPowered(face: BlockFace): Boolean {
		TODO("Not yet implemented")
	}

	override fun getBlockPower(face: BlockFace): Int {
		TODO("Not yet implemented")
	}

	override fun getBlockPower(): Int {
		TODO("Not yet implemented")
	}

	override fun isEmpty(): Boolean {
		TODO("Not yet implemented")
	}

	override fun isLiquid(): Boolean {
		TODO("Not yet implemented")
	}

	override fun getTemperature(): Double {
		TODO("Not yet implemented")
	}

	override fun getHumidity(): Double {
		TODO("Not yet implemented")
	}

	override fun getPistonMoveReaction(): PistonMoveReaction {
		TODO("Not yet implemented")
	}

	override fun breakNaturally(): Boolean {
		TODO("Not yet implemented")
	}

	override fun breakNaturally(tool: ItemStack?): Boolean {
		TODO("Not yet implemented")
	}

	override fun getDrops(): MutableCollection<ItemStack> {
		TODO("Not yet implemented")
	}

	override fun getDrops(tool: ItemStack?): MutableCollection<ItemStack> {
		TODO("Not yet implemented")
	}
}
