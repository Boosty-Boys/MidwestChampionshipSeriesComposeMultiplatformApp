package com.boostyboys.mcs.networking.impl

import com.boostyboys.mcs.data.api.models.Match
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchResponse(
    @SerialName("week") val week: Int,
    @SerialName("team_one") val teamOne: TeamResponse?,
    @SerialName("team_two") val teamTwo: TeamResponse?,
    @SerialName("winning_team_id") val winningTeamId: String?,
    @SerialName("date_time") val dateTime: String,
    @SerialName("games") val games: List<GameResponse>,
) {
    fun toLocalModel(): Match {
        val instant = Instant.parse(dateTime)
        val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

        return Match(
            week = week,
            teamOne = teamOne?.toLocalModel(),
            teamTwo = teamTwo?.toLocalModel(),
            winningTeamId = winningTeamId,
            dateTime = dateTime,
            games = games.map { it.toLocalModel() },
        )
    }
}
