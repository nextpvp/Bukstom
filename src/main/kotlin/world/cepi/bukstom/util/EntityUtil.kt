package world.cepi.bukstom.util

import net.minestom.server.entity.*
import org.bukkit.Bukkit
import world.cepi.bukstom.MinestomServer
import world.cepi.bukstom.entity.MinestomArmorStand
import world.cepi.bukstom.entity.MinestomArrow
import world.cepi.bukstom.entity.MinestomItem
import world.cepi.bukstom.entity.MinestomPlayer

fun org.bukkit.entity.Entity.toMinestom(): Entity {
	return when (type) {
		org.bukkit.entity.EntityType.DROPPED_ITEM -> TODO()
		org.bukkit.entity.EntityType.EXPERIENCE_ORB -> TODO()
		org.bukkit.entity.EntityType.LEASH_HITCH -> TODO()
		org.bukkit.entity.EntityType.PAINTING -> TODO()
		org.bukkit.entity.EntityType.ARROW -> TODO()
		org.bukkit.entity.EntityType.SNOWBALL -> TODO()
		org.bukkit.entity.EntityType.FIREBALL -> TODO()
		org.bukkit.entity.EntityType.SMALL_FIREBALL -> TODO()
		org.bukkit.entity.EntityType.ENDER_PEARL -> TODO()
		org.bukkit.entity.EntityType.ENDER_SIGNAL -> TODO()
		org.bukkit.entity.EntityType.THROWN_EXP_BOTTLE -> TODO()
		org.bukkit.entity.EntityType.ITEM_FRAME -> TODO()
		org.bukkit.entity.EntityType.WITHER_SKULL -> TODO()
		org.bukkit.entity.EntityType.PRIMED_TNT -> TODO()
		org.bukkit.entity.EntityType.FALLING_BLOCK -> TODO()
		org.bukkit.entity.EntityType.FIREWORK -> TODO()
		org.bukkit.entity.EntityType.ARMOR_STAND -> TODO()
		org.bukkit.entity.EntityType.MINECART_COMMAND -> TODO()
		org.bukkit.entity.EntityType.BOAT -> TODO()
		org.bukkit.entity.EntityType.MINECART -> TODO()
		org.bukkit.entity.EntityType.MINECART_CHEST -> TODO()
		org.bukkit.entity.EntityType.MINECART_FURNACE -> TODO()
		org.bukkit.entity.EntityType.MINECART_TNT -> TODO()
		org.bukkit.entity.EntityType.MINECART_HOPPER -> TODO()
		org.bukkit.entity.EntityType.MINECART_MOB_SPAWNER -> TODO()
		org.bukkit.entity.EntityType.CREEPER -> TODO()
		org.bukkit.entity.EntityType.SKELETON -> TODO()
		org.bukkit.entity.EntityType.SPIDER -> TODO()
		org.bukkit.entity.EntityType.GIANT -> TODO()
		org.bukkit.entity.EntityType.ZOMBIE -> TODO()
		org.bukkit.entity.EntityType.SLIME -> TODO()
		org.bukkit.entity.EntityType.GHAST -> TODO()
		org.bukkit.entity.EntityType.PIG_ZOMBIE -> TODO()
		org.bukkit.entity.EntityType.ENDERMAN -> TODO()
		org.bukkit.entity.EntityType.CAVE_SPIDER -> TODO()
		org.bukkit.entity.EntityType.SILVERFISH -> TODO()
		org.bukkit.entity.EntityType.BLAZE -> TODO()
		org.bukkit.entity.EntityType.MAGMA_CUBE -> TODO()
		org.bukkit.entity.EntityType.ENDER_DRAGON -> TODO()
		org.bukkit.entity.EntityType.WITHER -> TODO()
		org.bukkit.entity.EntityType.BAT -> TODO()
		org.bukkit.entity.EntityType.WITCH -> TODO()
		org.bukkit.entity.EntityType.ENDERMITE -> TODO()
		org.bukkit.entity.EntityType.GUARDIAN -> TODO()
		org.bukkit.entity.EntityType.PIG -> TODO()
		org.bukkit.entity.EntityType.SHEEP -> TODO()
		org.bukkit.entity.EntityType.COW -> TODO()
		org.bukkit.entity.EntityType.CHICKEN -> TODO()
		org.bukkit.entity.EntityType.SQUID -> TODO()
		org.bukkit.entity.EntityType.WOLF -> TODO()
		org.bukkit.entity.EntityType.MUSHROOM_COW -> TODO()
		org.bukkit.entity.EntityType.SNOWMAN -> TODO()
		org.bukkit.entity.EntityType.OCELOT -> TODO()
		org.bukkit.entity.EntityType.IRON_GOLEM -> TODO()
		org.bukkit.entity.EntityType.HORSE -> TODO()
		org.bukkit.entity.EntityType.RABBIT -> TODO()
		org.bukkit.entity.EntityType.VILLAGER -> TODO()
		org.bukkit.entity.EntityType.ENDER_CRYSTAL -> TODO()
		org.bukkit.entity.EntityType.SPLASH_POTION -> TODO()
		org.bukkit.entity.EntityType.EGG -> TODO()
		org.bukkit.entity.EntityType.FISHING_HOOK -> TODO()
		org.bukkit.entity.EntityType.LIGHTNING -> TODO()
		org.bukkit.entity.EntityType.WEATHER -> TODO()
		org.bukkit.entity.EntityType.PLAYER -> TODO()
		org.bukkit.entity.EntityType.COMPLEX_PART -> TODO()
		org.bukkit.entity.EntityType.UNKNOWN -> TODO()
		null -> throw Exception("Invalid entity type")
	}

}

fun Entity.toBukkit(): org.bukkit.entity.Entity {
	instance ?: throw Exception("Entity is not in an instance")

	return when (entityType) {
		EntityType.ITEM -> MinestomItem(this as ItemEntity, instance!!.toBukkit())
		EntityType.PLAYER -> MinestomPlayer(this as Player, instance!!.toBukkit(), Bukkit.getServer() as MinestomServer)
		EntityType.ARMOR_STAND -> MinestomArmorStand(this as LivingEntity, instance!!.toBukkit())
		EntityType.ARROW -> MinestomArrow(this, instance!!.toBukkit())
		else -> throw Exception("Invalid entity type. (might be a non 1.8 entity)")
	}

}

fun org.bukkit.entity.EntityType.toMinestom(): EntityType {
	return when (this) {
		org.bukkit.entity.EntityType.DROPPED_ITEM -> EntityType.ITEM
		org.bukkit.entity.EntityType.EXPERIENCE_ORB -> EntityType.EXPERIENCE_ORB
		org.bukkit.entity.EntityType.LEASH_HITCH -> EntityType.LEASH_KNOT
		org.bukkit.entity.EntityType.PAINTING -> EntityType.PAINTING
		org.bukkit.entity.EntityType.ARROW -> EntityType.ARROW
		org.bukkit.entity.EntityType.SNOWBALL -> EntityType.SNOWBALL
		org.bukkit.entity.EntityType.FIREBALL -> EntityType.FIREBALL
		org.bukkit.entity.EntityType.SMALL_FIREBALL -> EntityType.SMALL_FIREBALL
		org.bukkit.entity.EntityType.ENDER_PEARL -> EntityType.ENDER_PEARL
		org.bukkit.entity.EntityType.ENDER_SIGNAL -> EntityType.EYE_OF_ENDER
		org.bukkit.entity.EntityType.THROWN_EXP_BOTTLE -> EntityType.EXPERIENCE_BOTTLE
		org.bukkit.entity.EntityType.ITEM_FRAME -> EntityType.ITEM_FRAME
		org.bukkit.entity.EntityType.WITHER_SKULL -> EntityType.WITHER_SKULL
		org.bukkit.entity.EntityType.PRIMED_TNT -> EntityType.TNT
		org.bukkit.entity.EntityType.FALLING_BLOCK -> EntityType.FALLING_BLOCK
		org.bukkit.entity.EntityType.FIREWORK -> EntityType.FIREWORK_ROCKET
		org.bukkit.entity.EntityType.ARMOR_STAND -> EntityType.ARMOR_STAND
		org.bukkit.entity.EntityType.MINECART_COMMAND -> EntityType.COMMAND_BLOCK_MINECART
		org.bukkit.entity.EntityType.BOAT -> EntityType.BOAT
		org.bukkit.entity.EntityType.MINECART -> EntityType.MINECART
		org.bukkit.entity.EntityType.MINECART_CHEST -> EntityType.CHEST_MINECART
		org.bukkit.entity.EntityType.MINECART_FURNACE -> EntityType.FURNACE_MINECART
		org.bukkit.entity.EntityType.MINECART_TNT -> EntityType.TNT_MINECART
		org.bukkit.entity.EntityType.MINECART_HOPPER -> EntityType.HOPPER_MINECART
		org.bukkit.entity.EntityType.MINECART_MOB_SPAWNER -> EntityType.SPAWNER_MINECART
		org.bukkit.entity.EntityType.CREEPER -> EntityType.CREEPER
		org.bukkit.entity.EntityType.SKELETON -> EntityType.SKELETON
		org.bukkit.entity.EntityType.SPIDER -> EntityType.SPIDER
		org.bukkit.entity.EntityType.GIANT -> EntityType.GIANT
		org.bukkit.entity.EntityType.ZOMBIE -> EntityType.ZOMBIE
		org.bukkit.entity.EntityType.SLIME -> EntityType.SLIME
		org.bukkit.entity.EntityType.GHAST -> EntityType.GHAST
		org.bukkit.entity.EntityType.PIG_ZOMBIE -> EntityType.ZOMBIFIED_PIGLIN
		org.bukkit.entity.EntityType.ENDERMAN -> EntityType.ENDERMAN
		org.bukkit.entity.EntityType.CAVE_SPIDER -> EntityType.CAVE_SPIDER
		org.bukkit.entity.EntityType.SILVERFISH -> EntityType.SILVERFISH
		org.bukkit.entity.EntityType.BLAZE -> EntityType.BLAZE
		org.bukkit.entity.EntityType.MAGMA_CUBE -> EntityType.MAGMA_CUBE
		org.bukkit.entity.EntityType.ENDER_DRAGON -> EntityType.ENDER_DRAGON
		org.bukkit.entity.EntityType.WITHER -> EntityType.WITHER
		org.bukkit.entity.EntityType.BAT -> EntityType.BAT
		org.bukkit.entity.EntityType.WITCH -> EntityType.WITCH
		org.bukkit.entity.EntityType.ENDERMITE -> EntityType.ENDERMITE
		org.bukkit.entity.EntityType.GUARDIAN -> EntityType.GUARDIAN
		org.bukkit.entity.EntityType.PIG -> EntityType.PIG
		org.bukkit.entity.EntityType.SHEEP -> EntityType.SHEEP
		org.bukkit.entity.EntityType.COW -> EntityType.COW
		org.bukkit.entity.EntityType.CHICKEN -> EntityType.CHICKEN
		org.bukkit.entity.EntityType.SQUID -> EntityType.SQUID
		org.bukkit.entity.EntityType.WOLF -> EntityType.WOLF
		org.bukkit.entity.EntityType.MUSHROOM_COW -> EntityType.MOOSHROOM
		org.bukkit.entity.EntityType.SNOWMAN -> EntityType.SNOW_GOLEM
		org.bukkit.entity.EntityType.OCELOT -> EntityType.OCELOT
		org.bukkit.entity.EntityType.IRON_GOLEM -> EntityType.IRON_GOLEM
		org.bukkit.entity.EntityType.HORSE -> EntityType.HORSE
		org.bukkit.entity.EntityType.RABBIT -> EntityType.RABBIT
		org.bukkit.entity.EntityType.VILLAGER -> EntityType.VILLAGER
		org.bukkit.entity.EntityType.ENDER_CRYSTAL -> EntityType.END_CRYSTAL
		org.bukkit.entity.EntityType.SPLASH_POTION -> EntityType.POTION
		org.bukkit.entity.EntityType.EGG -> EntityType.EGG
		org.bukkit.entity.EntityType.FISHING_HOOK -> EntityType.FISHING_BOBBER
		org.bukkit.entity.EntityType.LIGHTNING -> EntityType.LIGHTNING_BOLT
		org.bukkit.entity.EntityType.WEATHER -> throw Exception("Not supported")
		org.bukkit.entity.EntityType.PLAYER -> EntityType.PLAYER
		org.bukkit.entity.EntityType.COMPLEX_PART -> throw Exception("Not supported")
		org.bukkit.entity.EntityType.UNKNOWN -> throw Exception("Not supported")
		else -> throw Exception()
	}
}


fun EntityType.toBukkit(): org.bukkit.entity.EntityType {
	return when (this) {
		EntityType.ITEM -> org.bukkit.entity.EntityType.DROPPED_ITEM
		EntityType.EXPERIENCE_ORB -> org.bukkit.entity.EntityType.EXPERIENCE_ORB
		EntityType.LEASH_KNOT -> org.bukkit.entity.EntityType.LEASH_HITCH
		EntityType.PAINTING -> org.bukkit.entity.EntityType.PAINTING
		EntityType.ARROW -> org.bukkit.entity.EntityType.ARROW
		EntityType.SNOWBALL -> org.bukkit.entity.EntityType.SNOWBALL
		EntityType.FIREBALL -> org.bukkit.entity.EntityType.FIREBALL
		EntityType.SMALL_FIREBALL -> org.bukkit.entity.EntityType.SMALL_FIREBALL
		EntityType.ENDER_PEARL -> org.bukkit.entity.EntityType.ENDER_PEARL
		EntityType.EYE_OF_ENDER -> org.bukkit.entity.EntityType.ENDER_SIGNAL
		EntityType.EXPERIENCE_BOTTLE -> org.bukkit.entity.EntityType.THROWN_EXP_BOTTLE
		EntityType.ITEM_FRAME -> org.bukkit.entity.EntityType.ITEM_FRAME
		EntityType.WITHER_SKULL -> org.bukkit.entity.EntityType.WITHER_SKULL
		EntityType.TNT -> org.bukkit.entity.EntityType.PRIMED_TNT
		EntityType.FALLING_BLOCK -> org.bukkit.entity.EntityType.FALLING_BLOCK
		EntityType.FIREWORK_ROCKET -> org.bukkit.entity.EntityType.FIREWORK
		EntityType.ARMOR_STAND -> org.bukkit.entity.EntityType.ARMOR_STAND
		EntityType.COMMAND_BLOCK_MINECART -> org.bukkit.entity.EntityType.MINECART_COMMAND
		EntityType.BOAT -> org.bukkit.entity.EntityType.BOAT
		EntityType.MINECART -> org.bukkit.entity.EntityType.MINECART
		EntityType.CHEST_MINECART -> org.bukkit.entity.EntityType.MINECART_CHEST
		EntityType.FURNACE_MINECART -> org.bukkit.entity.EntityType.MINECART_FURNACE
		EntityType.TNT_MINECART -> org.bukkit.entity.EntityType.MINECART_TNT
		EntityType.HOPPER_MINECART -> org.bukkit.entity.EntityType.MINECART_HOPPER
		EntityType.SPAWNER_MINECART -> org.bukkit.entity.EntityType.MINECART_MOB_SPAWNER
		EntityType.CREEPER -> org.bukkit.entity.EntityType.CREEPER
		EntityType.SKELETON -> org.bukkit.entity.EntityType.SKELETON
		EntityType.SPIDER -> org.bukkit.entity.EntityType.SPIDER
		EntityType.GIANT -> org.bukkit.entity.EntityType.GIANT
		EntityType.ZOMBIE -> org.bukkit.entity.EntityType.ZOMBIE
		EntityType.SLIME -> org.bukkit.entity.EntityType.SLIME
		EntityType.GHAST -> org.bukkit.entity.EntityType.GHAST
		EntityType.ZOMBIFIED_PIGLIN -> org.bukkit.entity.EntityType.PIG_ZOMBIE
		EntityType.ENDERMAN -> org.bukkit.entity.EntityType.ENDERMAN
		EntityType.CAVE_SPIDER -> org.bukkit.entity.EntityType.CAVE_SPIDER
		EntityType.SILVERFISH -> org.bukkit.entity.EntityType.SILVERFISH
		EntityType.BLAZE -> org.bukkit.entity.EntityType.BLAZE
		EntityType.MAGMA_CUBE -> org.bukkit.entity.EntityType.MAGMA_CUBE
		EntityType.ENDER_DRAGON -> org.bukkit.entity.EntityType.ENDER_DRAGON
		EntityType.WITHER -> org.bukkit.entity.EntityType.WITHER
		EntityType.BAT -> org.bukkit.entity.EntityType.BAT
		EntityType.WITCH -> org.bukkit.entity.EntityType.WITCH
		EntityType.ENDERMITE -> org.bukkit.entity.EntityType.ENDERMITE
		EntityType.GUARDIAN -> org.bukkit.entity.EntityType.GUARDIAN
		EntityType.PIG -> org.bukkit.entity.EntityType.PIG
		EntityType.SHEEP -> org.bukkit.entity.EntityType.SHEEP
		EntityType.COW -> org.bukkit.entity.EntityType.COW
		EntityType.CHICKEN -> org.bukkit.entity.EntityType.CHICKEN
		EntityType.SQUID -> org.bukkit.entity.EntityType.SQUID
		EntityType.WOLF -> org.bukkit.entity.EntityType.WOLF
		EntityType.MOOSHROOM -> org.bukkit.entity.EntityType.MUSHROOM_COW
		EntityType.SNOW_GOLEM -> org.bukkit.entity.EntityType.SNOWMAN
		EntityType.OCELOT -> org.bukkit.entity.EntityType.OCELOT
		EntityType.IRON_GOLEM -> org.bukkit.entity.EntityType.IRON_GOLEM
		EntityType.HORSE -> org.bukkit.entity.EntityType.HORSE
		EntityType.RABBIT -> org.bukkit.entity.EntityType.RABBIT
		EntityType.VILLAGER -> org.bukkit.entity.EntityType.VILLAGER
		EntityType.END_CRYSTAL -> org.bukkit.entity.EntityType.ENDER_CRYSTAL
		EntityType.POTION -> org.bukkit.entity.EntityType.SPLASH_POTION
		EntityType.EGG -> org.bukkit.entity.EntityType.EGG
		EntityType.FISHING_BOBBER -> org.bukkit.entity.EntityType.FISHING_HOOK
		EntityType.LIGHTNING_BOLT -> org.bukkit.entity.EntityType.LIGHTNING
		EntityType.PLAYER -> org.bukkit.entity.EntityType.PLAYER
		else -> throw IllegalArgumentException("Entity type not possible for 1.8")
	}
}