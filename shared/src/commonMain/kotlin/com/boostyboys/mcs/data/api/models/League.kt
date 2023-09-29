package com.boostyboys.mcs.data.api.models

data class League(
    val id: String,
    val name: String,
    val order: Int,
    val seasonIds: List<String>,
    val alternateNameBySeason: Map<String, String?> = mapOf(),
    val teamsIdsBySeason: Map<String, List<String>>,
)
