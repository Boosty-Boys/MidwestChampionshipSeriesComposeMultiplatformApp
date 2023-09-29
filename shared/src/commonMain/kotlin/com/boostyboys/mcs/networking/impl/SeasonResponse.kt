package com.boostyboys.mcs.networking.impl

import com.boostyboys.mcs.data.api.models.LeagueSeason
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeasonResponse(
    @SerialName("_id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("league") val league: LeagueResponse,
    @SerialName("teams") val teams: List<TeamResponse>,
    @SerialName("matches") val matches: List<MatchResponse>,
) {
    fun toLocalModel(): LeagueSeason {
        return LeagueSeason(
            id = id,
            name = name,
            league = league.toLocalModel(),
            teams = teams.map { it.toLocalModel() },
            matches = matches.map { it.toLocalModel() },
        )
    }
}
