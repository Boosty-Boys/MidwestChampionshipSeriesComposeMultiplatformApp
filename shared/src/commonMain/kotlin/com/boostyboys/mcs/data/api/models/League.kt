package com.boostyboys.mcs.data.api.models

data class League(
    val id: String,
    val name: String,
    val seasonIds: List<String>,
    val teamIdsBySeason: Map<String, List<String>>,
)
