package com.boostyboys.mcs.data.api.models.season

import cafe.adriel.voyager.core.lifecycle.JavaSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Season(
    @SerialName("_id") val id: String,
    @SerialName("name") val name: String, // e.g. "3"
    @SerialName("regular_season_weeks") val regularSeasonWeeks: Int, // e.g. 12
    @SerialName("match_ids") val matchIds: List<String>,
    @SerialName("team_ids") val teamIds: List<String>,
    @SerialName("player_ids") val playerIds: List<String>,
    @SerialName("start_date") val startDate: String,
    @SerialName("end_date") val endDate: String,
) : JavaSerializable
