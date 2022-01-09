package world.cepi.bukstom.scoreboard

import net.kyori.adventure.text.Component
import net.minestom.server.scoreboard.Sidebar
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.scoreboard.ScoreboardManager

// We use custom so not much point implementing this rn
class MinestomScoreboardManager : ScoreboardManager {
	private val main = MinestomScoreboard(Sidebar(Component.text("")))

	override fun getMainScoreboard(): Scoreboard {
		return main
	}

	override fun getNewScoreboard(): Scoreboard {
		TODO("Not yet implemented")
	}
}