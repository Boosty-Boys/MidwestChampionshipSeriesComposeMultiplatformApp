package com.boostyboys.mcs.data.api.models

import kotlinx.datetime.LocalDateTime

data class Match(
    val week: Int,
    val teamOne: Team,
    val teamTwo: Team,
    val winningTeamId: String,
    val dateTime: LocalDateTime,
    val games: List<Game>,
)
