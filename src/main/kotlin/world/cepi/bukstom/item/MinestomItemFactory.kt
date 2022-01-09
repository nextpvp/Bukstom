package world.cepi.bukstom.item

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.HoverEvent
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.hover.content.Content
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemFactory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.function.UnaryOperator

class MinestomItemFactory : ItemFactory {
	override fun equals(meta1: ItemMeta?, meta2: ItemMeta?): Boolean {
		TODO("Not yet implemented")
	}

	override fun getItemMeta(material: Material): ItemMeta {
		TODO("Not yet implemented")
	}

	override fun isApplicable(meta: ItemMeta?, stack: ItemStack?): Boolean {
		TODO("Not yet implemented")
	}

	override fun isApplicable(meta: ItemMeta?, material: Material?): Boolean {
		TODO("Not yet implemented")
	}

	override fun asMetaFor(meta: ItemMeta, stack: ItemStack): ItemMeta? {
		TODO("Not yet implemented")
	}

	override fun asMetaFor(meta: ItemMeta, material: Material): ItemMeta? {
		TODO("Not yet implemented")
	}

	override fun getDefaultLeatherColor(): Color {
		TODO("Not yet implemented")
	}

	override fun updateMaterial(meta: ItemMeta, material: Material): Material {
		TODO("Not yet implemented")
	}

	override fun asHoverEvent(
		item: ItemStack,
		op: UnaryOperator<HoverEvent.ShowItem>
	): HoverEvent<HoverEvent.ShowItem> {
		TODO("Not yet implemented")
	}

	override fun displayName(itemStack: ItemStack): Component {
		TODO("Not yet implemented")
	}

	override fun ensureServerConversions(item: ItemStack): ItemStack {
		TODO("Not yet implemented")
	}

	override fun getI18NDisplayName(item: ItemStack?): String? {
		TODO("Not yet implemented")
	}

	override fun hoverContentOf(itemStack: ItemStack): Content {
		TODO("Not yet implemented")
	}

	override fun hoverContentOf(entity: Entity): Content {
		TODO("Not yet implemented")
	}

	override fun hoverContentOf(entity: Entity, customName: String?): Content {
		TODO("Not yet implemented")
	}

	override fun hoverContentOf(entity: Entity, customName: BaseComponent?): Content {
		TODO("Not yet implemented")
	}

	override fun hoverContentOf(entity: Entity, customName: Array<out BaseComponent>): Content {
		TODO("Not yet implemented")
	}
}