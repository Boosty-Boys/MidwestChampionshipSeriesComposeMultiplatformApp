package com.boostyboys.mcs.data.models

data class Standings(
    val league: String,
    val season: String,
    val teams: List<Team>,
)

val risingStarSeasonEightStandings = Standings(
    league = "Rising Star",
    season = "8",
    teams = risingStarSeasonEightTeams,
)
