package com.boostyboys.mcs.data.api.models.match

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Game(
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("date_time_played")
    val dateTimePlayed: String?,
    @SerialName("date_time_processed")
    val dateTimeProcessed: String?,
    @SerialName("forfeit_team_id")
    val forfeitTeamId: String?,
    @SerialName("game_number")
    val gameNumber: Int?,
    @SerialName("_id")
    val id: String,
    @SerialName("replay_origin")
    val replayOrigin: ReplayOrigin?,
    @SerialName("report_type")
    val reportType: String?,
    @SerialName("rl_game_id")
    val rlGameId: String?,
    @SerialName("status")
    val status: String?,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("winning_team_id")
    val winningTeamId: String?,
)
