package world.cepi.bukstom.util

import net.minestom.server.instance.block.Block
import org.bukkit.World
import world.cepi.bukstom.world.MinestomBlock

fun Block.toBukkit(world: World): MinestomBlock = MinestomBlock(world, this)
