package com.boostyboys.mcs.data.api.models.season

import com.boostyboys.mcs.data.api.models.match.Match
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeasonDataResponse(
    @SerialName("season_id") val seasonId: String,
    @SerialName("teams") val teams: List<TeamWithResults>,
    @SerialName("matches") val matches: List<Match>,
)
