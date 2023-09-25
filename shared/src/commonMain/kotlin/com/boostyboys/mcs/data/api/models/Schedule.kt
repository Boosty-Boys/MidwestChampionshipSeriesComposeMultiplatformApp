package com.boostyboys.mcs.data.api.models

data class Schedule(
    val leagueId: String,
    val seasonId: String,
    val week: Int,
    val matches: List<Match>,
)
