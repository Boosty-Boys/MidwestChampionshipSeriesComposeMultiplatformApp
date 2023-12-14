package com.boostyboys.mcs.data.api.models.league

import cafe.adriel.voyager.core.lifecycle.JavaSerializable
import com.boostyboys.mcs.data.api.models.season.Season
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeagueWithSeasons(
    @SerialName("_id") val id: String,
    @SerialName("name") val name: String, // e.g. "Rising Star"
    @SerialName("current_week") val currentWeek: String, // e.g. "1"
    @SerialName("current_season_id") val currentSeasonId: String,
    @SerialName("seasons") val seasons: List<Season>,
) : JavaSerializable
