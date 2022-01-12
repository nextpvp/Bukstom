package world.cepi.bukstom.util

import org.bukkit.Achievement
import org.bukkit.Material
import org.bukkit.Statistic
import org.bukkit.UnsafeValues
import org.bukkit.inventory.ItemStack

object MinestomUnsafeValues : UnsafeValues {
	override fun getMaterialFromInternalName(name: String?): Material {
		return Material.getMaterial(name)
	}

	override fun tabCompleteInternalMaterialName(
		token: String?,
		completions: MutableList<String>?
	): MutableList<String> {
		return mutableListOf()
	}

	override fun modifyItemStack(stack: ItemStack?, arguments: String?): ItemStack {
		TODO("Not yet implemented")
	}

	override fun getStatisticFromInternalName(name: String?): Statistic {
		TODO("Not yet implemented")
	}

	override fun getAchievementFromInternalName(name: String?): Achievement {
		TODO("Not yet implemented")
	}

	override fun tabCompleteInternalStatisticOrAchievementName(
		token: String?,
		completions: MutableList<String>?
	): MutableList<String> {
		TODO("Not yet implemented")
	}

	//TODO: Look into this
	/*override fun processClass(pdf: PluginDescriptionFile, path: String, clazz: ByteArray): ByteArray? {
		return try {
			Commodore.convert(clazz, !isLegacy(pdf))
		} catch (ex: Exception) {
			//Bukkit.getLogger().log(Level.SEVERE, "Fatal error trying to convert " + pdf.fullName + ":" + path, ex)
			null
		}

	}*/
}