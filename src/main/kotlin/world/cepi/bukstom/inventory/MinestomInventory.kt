package world.cepi.bukstom.inventory

import org.bukkit.Material
import org.bukkit.entity.HumanEntity
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack
import world.cepi.bukstom.util.toBukkit
import world.cepi.bukstom.util.toMinestom

class MinestomInventory(val minestomInventory: net.minestom.server.inventory.Inventory, var invName: String) :
	Inventory {
	override fun iterator(): MutableListIterator<ItemStack> {
		return minestomInventory.itemStacks.toBukkit().listIterator()
	}

	override fun iterator(index: Int): MutableListIterator<ItemStack> {
		return minestomInventory.itemStacks.toBukkit().take(index).toMutableList().listIterator()
	}

	override fun getSize(): Int {
		return minestomInventory.size
	}

	override fun getMaxStackSize(): Int {
		return 64
	}

	override fun setMaxStackSize(size: Int) {

	}

	override fun getName(): String {
		return invName
	}

	override fun getItem(index: Int): ItemStack? {
		val item = minestomInventory.getItemStack(index).toBukkit()
		if (item.type == Material.AIR) return null
		return item
	}

	override fun setItem(index: Int, item: ItemStack?) {
		minestomInventory.setItemStack(index, item?.toMinestom() ?: net.minestom.server.item.ItemStack.AIR)
	}

	override fun addItem(vararg items: ItemStack): HashMap<Int, ItemStack> {
		return hashMapOf()
	}

	override fun removeItem(vararg items: ItemStack): HashMap<Int, ItemStack> {
		return hashMapOf()
	}

	override fun getContents(): Array<ItemStack?> {
		return minestomInventory.itemStacks.toBukkit().toTypedArray()
	}

	override fun setContents(items: Array<out ItemStack>) {
		for ((slot, item) in items.withIndex()) {
			minestomInventory.itemStacks[slot] = item.toMinestom()
		}
	}

	override fun contains(materialId: Int): Boolean {
		return false
	}

	override fun contains(material: Material): Boolean {
		return false
	}

	override fun contains(item: ItemStack?): Boolean {
		TODO("Not yet implemented")
	}

	override fun contains(materialId: Int, amount: Int): Boolean {
		TODO("Not yet implemented")
	}

	override fun contains(material: Material, amount: Int): Boolean {
		TODO("Not yet implemented")
	}

	override fun contains(item: ItemStack?, amount: Int): Boolean {
		TODO("Not yet implemented")
	}

	override fun containsAtLeast(item: ItemStack?, amount: Int): Boolean {
		TODO("Not yet implemented")
	}

	override fun all(materialId: Int): HashMap<Int, out ItemStack> {
		TODO("Not yet implemented")
	}

	override fun all(material: Material): HashMap<Int, out ItemStack> {
		TODO("Not yet implemented")
	}

	override fun all(item: ItemStack?): HashMap<Int, out ItemStack> {
		TODO("Not yet implemented")
	}

	override fun first(materialId: Int): Int {
		TODO("Not yet implemented")
	}

	override fun first(material: Material): Int {
		TODO("Not yet implemented")
	}

	override fun first(item: ItemStack): Int {
		TODO("Not yet implemented")
	}

	override fun firstEmpty(): Int {
		TODO("Not yet implemented")
	}

	override fun remove(materialId: Int) {
		TODO("Not yet implemented")
	}

	override fun remove(material: Material) {
		TODO("Not yet implemented")
	}

	override fun remove(item: ItemStack) {
		TODO("Not yet implemented")
	}

	override fun clear(index: Int) {
		TODO("Not yet implemented")
	}

	override fun clear() {
		TODO("Not yet implemented")
	}

	override fun getViewers(): MutableList<HumanEntity> {
		TODO("Not yet implemented")
	}

	override fun getTitle(): String {
		TODO("Not yet implemented")
	}

	override fun getType(): InventoryType {
		TODO("Not yet implemented")
	}

	override fun getHolder(): InventoryHolder? {
		return null
	}
}