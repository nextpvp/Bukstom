package world.cepi.bukstom.scoreboard

import net.kyori.adventure.text.Component
import org.bukkit.OfflinePlayer
import org.bukkit.scoreboard.*

class MinestomScoreboard(
	val internalScoreboard: net.minestom.server.scoreboard.Scoreboard
) : Scoreboard {
	override fun registerNewObjective(name: String, criteria: String): Objective {
		TODO("Not yet implemented")
	}

	override fun registerNewObjective(name: String, criteria: String, displayName: Component?): Objective {
		TODO("Not yet implemented")
	}

	override fun registerNewObjective(
		name: String,
		criteria: String,
		displayName: Component?,
		renderType: RenderType
	): Objective {
		TODO("Not yet implemented")
	}

	override fun registerNewObjective(name: String, criteria: String, displayName: String): Objective {
		TODO("Not yet implemented")
	}

	override fun registerNewObjective(
		name: String,
		criteria: String,
		displayName: String,
		renderType: RenderType
	): Objective {
		TODO("Not yet implemented")
	}

	override fun getObjective(name: String): Objective? {
		TODO("Not yet implemented")
	}

	override fun getObjective(slot: DisplaySlot): Objective? {
		TODO("Not yet implemented")
	}

	override fun getObjectivesByCriteria(criteria: String): MutableSet<Objective> {
		TODO("Not yet implemented")
	}

	override fun getObjectives(): MutableSet<Objective> {
		TODO("Not yet implemented")
	}

	override fun getScores(player: OfflinePlayer): MutableSet<Score> {
		TODO("Not yet implemented")
	}

	override fun getScores(entry: String): MutableSet<Score> {
		TODO("Not yet implemented")
	}

	override fun resetScores(player: OfflinePlayer) {
		TODO("Not yet implemented")
	}

	override fun resetScores(entry: String) {
		TODO("Not yet implemented")
	}

	override fun getPlayerTeam(player: OfflinePlayer): Team? {
		TODO("Not yet implemented")
	}

	override fun getEntryTeam(entry: String): Team? {
		TODO("Not yet implemented")
	}

	override fun getTeam(teamName: String): Team? {
		TODO("Not yet implemented")
	}

	override fun getTeams(): MutableSet<Team> {
		TODO("Not yet implemented")
	}

	override fun registerNewTeam(name: String): Team {
		TODO("Not yet implemented")
	}

	override fun getPlayers(): MutableSet<OfflinePlayer> {
		TODO("Not yet implemented")
	}

	override fun getEntries(): MutableSet<String> {
		TODO("Not yet implemented")
	}

	override fun clearSlot(slot: DisplaySlot) {
		TODO("Not yet implemented")
	}

}
