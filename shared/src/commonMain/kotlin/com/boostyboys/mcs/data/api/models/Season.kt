package com.boostyboys.mcs.data.api.models

data class Season(
    val ids: List<String>,
    val name: String, // season number (i.e. "1")
    val leagueIds: List<String>,
)
