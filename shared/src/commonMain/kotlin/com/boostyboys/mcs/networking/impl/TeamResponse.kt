package com.boostyboys.mcs.networking.impl

import com.boostyboys.mcs.data.api.models.Team
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamResponse(
    @SerialName("_id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("avatar") val avatar: String,
    @SerialName("wins") val wins: Int,
    @SerialName("losses") val losses: Int,
    @SerialName("players") val players: List<PlayerResponse>,
) {
    fun toLocalModel(): Team {
        return Team(
            id = id,
            name = name,
            avatar = avatar,
            wins = wins,
            losses = losses,
            players = players.map { it.toLocalModel() },
        )
    }
}
