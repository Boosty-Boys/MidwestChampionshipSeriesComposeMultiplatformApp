package com.boostyboys.mcs.data.api.models.season

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamWithResults(
    @SerialName("_id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("avatar") val avatar: String,
    @SerialName("matches_won") var matchesWon: Int,
    @SerialName("matches_played") var matchesPlayed: Int,
    @SerialName("games_won") var gamesWon: Int,
    @SerialName("games_played") var gamesPlayed: Int,
)
