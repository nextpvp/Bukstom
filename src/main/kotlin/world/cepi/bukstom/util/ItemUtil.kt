package world.cepi.bukstom.util

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

fun net.minestom.server.item.ItemStack.toBukkit(): ItemStack {
	val item = ItemStack(Material.getMaterial(this.material.name()) ?: throw Exception("Invalid material"))


	return item
}

fun Array<net.minestom.server.item.ItemStack>.toBukkit(): MutableList<ItemStack> {
	return mutableListOf()
}

fun ItemStack.toMinestom(): net.minestom.server.item.ItemStack {
	return net.minestom.server.item.ItemStack.AIR
}