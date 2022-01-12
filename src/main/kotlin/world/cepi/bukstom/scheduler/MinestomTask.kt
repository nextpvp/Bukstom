package world.cepi.bukstom.scheduler

import net.minestom.server.timer.Task
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitTask

class MinestomTask(val task: Task, val plugin: Plugin) : BukkitTask {
	override fun getTaskId() = task.id

	override fun getOwner(): Plugin = plugin

	override fun isSync() = false

	override fun cancel() = task.cancel()
}