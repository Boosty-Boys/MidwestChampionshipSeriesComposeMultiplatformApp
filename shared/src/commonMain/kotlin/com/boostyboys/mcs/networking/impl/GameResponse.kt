package com.boostyboys.mcs.networking.impl

import com.boostyboys.mcs.data.api.models.Game
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameResponse(
    @SerialName("_id") val gameId: String,
    @SerialName("game_number") val gameNumber: Int?,
    @SerialName("winning_team_id") val winningTeamId: String?,
) {
    fun toLocalModel(): Game {
        return Game(
            gameId = gameId,
            gameNumber = gameNumber,
            winningTeamId = winningTeamId,
        )
    }
}
