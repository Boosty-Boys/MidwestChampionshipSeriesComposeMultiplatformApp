package com.boostyboys.mcs.networking.impl

import com.boostyboys.mcs.data.api.models.League
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeagueResponse(
    @SerialName("_id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("season_id")
    val seasonIds: List<String>,
    @SerialName("team_ids_by_season")
    val teamIdsBySeason: Map<String, List<String>>,
) {
    fun toLocalModel(): League {
        return League(
            id = id,
            name = name,
            seasonIds = seasonIds,
            teamIdsBySeason = teamIdsBySeason,
        )
    }
}
