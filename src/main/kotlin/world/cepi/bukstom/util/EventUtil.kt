package world.cepi.bukstom.util

import org.bukkit.Bukkit
import org.bukkit.event.Event

fun callEvent(ev: Event) = Bukkit.getPluginManager().callEvent(ev)