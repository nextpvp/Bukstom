package world.cepi.bukstom.entity

import net.minestom.server.entity.Entity
import org.bukkit.entity.Snowball
import world.cepi.bukstom.world.MinestomWorld

class MinestomSnowball(entity: Entity, minestomWorld: MinestomWorld) : MinestomProjectile(entity, minestomWorld),
	Snowball {}