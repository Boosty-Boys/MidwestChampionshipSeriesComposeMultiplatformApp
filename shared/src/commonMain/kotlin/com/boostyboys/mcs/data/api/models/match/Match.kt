package com.boostyboys.mcs.data.api.models.match

import cafe.adriel.voyager.core.lifecycle.JavaSerializable
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Match(
    @SerialName("_id") val id: String,
    @Serializable(with = MatchStatusSerializer::class)
    @SerialName("status") val status: MatchStatus,
    @SerialName("week") val week: Int,
    @SerialName("best_of") val bestOf: Int,
    @SerialName("team_one_id") val teamOneId: String?,
    @SerialName("team_two_id") val teamTwoId: String?,
    @SerialName("winning_team_id") val winningTeamId: String?,
    @SerialName("is_forfeit") val isForfeit: Boolean,
    @SerialName("scheduled_date_time") private val scheduledDateTimeWrapper: LocalDateTimeWrapper?,
    @SerialName("players_to_teams") val playersToTeams: List<PlayersToTeam>,
    @SerialName("stream_link") val streamLink: String?,
    @SerialName("games") val games: List<Game>,
) : JavaSerializable {
    val scheduledDateTime: LocalDateTime?
        get() = scheduledDateTimeWrapper?.localDateTime
}
