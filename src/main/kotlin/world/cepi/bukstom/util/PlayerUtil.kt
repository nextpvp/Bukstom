package world.cepi.bukstom.util

import net.minestom.server.entity.Player
import org.bukkit.Bukkit
import world.cepi.bukstom.MinestomServer
import world.cepi.bukstom.entity.MinestomPlayer

fun Player.toBukkit() = MinestomPlayer(this, instance!!.toBukkit(), Bukkit.getServer() as MinestomServer)