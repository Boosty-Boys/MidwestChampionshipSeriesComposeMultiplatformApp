package com.boostyboys.mcs.data.api.models

data class Season(
    val id: String,
    val name: String,
    val order: Int,
    val leagueIds: List<String>,
    val weeks: Int,
)
