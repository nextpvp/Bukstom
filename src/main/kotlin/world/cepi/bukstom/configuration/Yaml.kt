package world.cepi.bukstom.configuration

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.KSerializer
import java.io.File

val yaml = Yaml()

inline fun <T> Yaml.config(serializer: KSerializer<T>, file: File, default: () -> T) =
	if (file.exists()) decodeFromString(
		serializer,
		file.readText()
	) else default().also {
		file.createNewFile()
		file.writeText(yaml.encodeToString(serializer, it))
	}

//fun main() {
//    println(yaml.config(BukkitConfig.serializer(), File("bukkit.yml")) { BukkitConfig() })
//}