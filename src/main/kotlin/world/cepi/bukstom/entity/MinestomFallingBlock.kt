package world.cepi.bukstom.entity

import net.minestom.server.entity.metadata.other.FallingBlockMeta
import net.minestom.server.instance.block.Block
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.FallingBlock
import world.cepi.bukstom.util.toBukkit
import world.cepi.bukstom.world.MinestomWorld

class MinestomFallingBlock(entity: net.minestom.server.entity.Entity, minestomWorld: MinestomWorld) : FallingBlock, MinestomEntity<net.minestom.server.entity.Entity>(entity,
	minestomWorld
) {
	val meta = entity.entityMeta as FallingBlockMeta

	fun setMaterial(mat: Material) {
		meta.block = Block.fromBlockId(mat.id)!!
	}

	override fun getMaterial(): Material {
		return meta.block.toBukkit(minestomWorld).type
	}

	override fun getBlockId(): Int {
		return meta.block.id()
	}

	//TODO: Look into this
	fun setBlockData(byte: Byte) {

	}

	override fun getBlockData(): Byte {
		return meta.objectData.toByte()
	}

	override fun getDropItem(): Boolean {
		return false
	}

	override fun setDropItem(drop: Boolean) {

	}

	override fun canHurtEntities(): Boolean {
		TODO("Not yet implemented")
	}

	override fun setHurtEntities(hurtEntities: Boolean) {
		TODO("Not yet implemented")
	}
}
