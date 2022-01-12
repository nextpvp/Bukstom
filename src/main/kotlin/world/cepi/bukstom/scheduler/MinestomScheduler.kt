package world.cepi.bukstom.scheduler

import net.minestom.server.MinecraftServer
import net.minestom.server.timer.TaskStatus
import net.minestom.server.utils.time.TimeUnit
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitScheduler
import org.bukkit.scheduler.BukkitTask
import org.bukkit.scheduler.BukkitWorker
import java.util.concurrent.Callable
import java.util.concurrent.Future

class MinestomScheduler : BukkitScheduler {
	override fun scheduleSyncDelayedTask(plugin: Plugin, task: Runnable, delay: Long): Int =
		MinecraftServer.getSchedulerManager().buildTask(task).delay(delay, TimeUnit.TICK).schedule().id

	override fun scheduleSyncDelayedTask(plugin: Plugin, task: BukkitRunnable, delay: Long) =
		MinecraftServer.getSchedulerManager().buildTask(task).delay(delay, TimeUnit.TICK).schedule().id

	override fun scheduleSyncDelayedTask(plugin: Plugin, task: Runnable): Int =
		MinecraftServer.getSchedulerManager().buildTask(task).schedule().id

	override fun scheduleSyncDelayedTask(plugin: Plugin, task: BukkitRunnable): Int =
		MinecraftServer.getSchedulerManager().buildTask(task).schedule().id

	override fun scheduleSyncRepeatingTask(plugin: Plugin, task: Runnable, delay: Long, period: Long): Int =
		MinecraftServer.getSchedulerManager().buildTask(task).delay(delay, TimeUnit.TICK).repeat(period, TimeUnit.TICK)
			.schedule().id

	override fun scheduleSyncRepeatingTask(plugin: Plugin, task: BukkitRunnable, delay: Long, period: Long): Int =
		MinecraftServer.getSchedulerManager().buildTask(task).delay(delay, TimeUnit.TICK).repeat(period, TimeUnit.TICK)
			.schedule().id

	override fun scheduleAsyncDelayedTask(plugin: Plugin, task: Runnable, delay: Long): Int =
		MinecraftServer.getSchedulerManager().buildTask(task).delay(delay, TimeUnit.TICK).schedule().id

	override fun scheduleAsyncDelayedTask(plugin: Plugin, task: Runnable): Int =
		MinecraftServer.getSchedulerManager().buildTask(task).schedule().id

	override fun scheduleAsyncRepeatingTask(plugin: Plugin, task: Runnable, delay: Long, period: Long): Int =
		MinecraftServer.getSchedulerManager().buildTask(task).delay(delay, TimeUnit.TICK).repeat(period, TimeUnit.TICK)
			.schedule().id

	override fun <T : Any?> callSyncMethod(plugin: Plugin, task: Callable<T>): Future<T> {
		TODO("Not yet implemented")
	}

	override fun cancelTask(taskId: Int) {
		MinecraftServer.getSchedulerManager().getTask(taskId)
			?.let { MinecraftServer.getSchedulerManager().removeTask(it) }
	}

	override fun cancelTasks(plugin: Plugin) {
		MinecraftServer.getSchedulerManager().tasks.forEach {
			MinecraftServer.getSchedulerManager().removeTask(it)
		}
	}

	override fun cancelAllTasks() {
		MinecraftServer.getSchedulerManager().tasks.forEach { it.cancel() }
	}

	override fun isCurrentlyRunning(taskId: Int) = when (MinecraftServer.getSchedulerManager().getTask(taskId).status) {
		TaskStatus.SCHEDULED -> false
		TaskStatus.FINISHED -> false
		TaskStatus.CANCELLED -> false
	}


	override fun isQueued(taskId: Int) = when (MinecraftServer.getSchedulerManager().getTask(taskId).status) {
		TaskStatus.SCHEDULED -> true
		TaskStatus.FINISHED -> false
		TaskStatus.CANCELLED -> false
	}

	override fun getActiveWorkers(): MutableList<BukkitWorker> {
		TODO("Not yet implemented")
	}

	override fun getPendingTasks(): MutableList<BukkitTask> {
		TODO("Not yet implemented")
	}

	override fun runTask(plugin: Plugin, task: Runnable): BukkitTask =
		MinestomTask(MinecraftServer.getSchedulerManager().buildTask(task).schedule(), plugin)

	override fun runTask(plugin: Plugin, task: BukkitRunnable) =
		MinestomTask(MinecraftServer.getSchedulerManager().buildTask(task).schedule(), plugin)

	override fun runTaskAsynchronously(plugin: Plugin, task: Runnable): BukkitTask =
		MinestomTask(MinecraftServer.getSchedulerManager().buildTask(task).schedule(), plugin)

	override fun runTaskAsynchronously(plugin: Plugin, task: BukkitRunnable) =
		MinestomTask(MinecraftServer.getSchedulerManager().buildTask(task).schedule(), plugin)

	override fun runTaskLater(plugin: Plugin, task: Runnable, delay: Long): BukkitTask =
		MinestomTask(
			MinecraftServer.getSchedulerManager().buildTask(task).delay(delay, TimeUnit.TICK).schedule(),
			plugin
		)

	override fun runTaskLater(plugin: Plugin, task: BukkitRunnable, delay: Long): BukkitTask =
		MinestomTask(
			MinecraftServer.getSchedulerManager().buildTask(task).delay(delay, TimeUnit.TICK).schedule(),
			plugin
		)

	override fun runTaskLaterAsynchronously(plugin: Plugin, task: Runnable, delay: Long) =
		MinestomTask(
			MinecraftServer.getSchedulerManager().buildTask(task).delay(delay, TimeUnit.TICK).schedule(),
			plugin
		)

	override fun runTaskLaterAsynchronously(plugin: Plugin, task: BukkitRunnable, delay: Long) =
		MinestomTask(
			MinecraftServer.getSchedulerManager().buildTask(task).delay(delay, TimeUnit.TICK).schedule(),
			plugin
		)

	override fun runTaskTimer(plugin: Plugin, task: Runnable, delay: Long, period: Long) =
		MinestomTask(
			MinecraftServer.getSchedulerManager().buildTask(task).delay(delay, TimeUnit.TICK)
				.repeat(period, TimeUnit.TICK).schedule(),
			plugin
		)

	override fun runTaskTimer(plugin: Plugin, task: BukkitRunnable, delay: Long, period: Long) =
		MinestomTask(
			MinecraftServer.getSchedulerManager().buildTask(task).delay(delay, TimeUnit.TICK)
				.repeat(period, TimeUnit.TICK).schedule(),
			plugin
		)

	override fun runTaskTimerAsynchronously(plugin: Plugin, task: Runnable, delay: Long, period: Long) =
		MinestomTask(
			MinecraftServer.getSchedulerManager().buildTask(task).delay(delay, TimeUnit.TICK).schedule(),
			plugin
		)

	override fun runTaskTimerAsynchronously(
		plugin: Plugin,
		task: BukkitRunnable,
		delay: Long,
		period: Long
	): BukkitTask {
		TODO("Not yet implemented")
	}
}