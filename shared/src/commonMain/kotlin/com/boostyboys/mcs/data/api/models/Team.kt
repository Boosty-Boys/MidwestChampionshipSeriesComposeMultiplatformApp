package com.boostyboys.mcs.data.api.models

data class Team(
    val id: String,
    val name: String,
    val avatar: String,
    val wins: Int,
    val losses: Int,
    val players: List<Player>,
)
