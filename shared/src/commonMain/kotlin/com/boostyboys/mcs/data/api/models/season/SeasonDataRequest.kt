package com.boostyboys.mcs.data.api.models.season

import cafe.adriel.voyager.core.lifecycle.JavaSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeasonDataRequest(
    @SerialName("season_id") val seasonId: String,
    @SerialName("team_ids") val teamIds: List<String>,
) : JavaSerializable
