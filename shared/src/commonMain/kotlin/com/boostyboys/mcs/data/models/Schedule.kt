package com.boostyboys.mcs.data.models

data class Schedule(
    val league: String,
    val season: String,
    val week: Int,
    val matches: List<Match>,
)

val risingStarSeasonEightWeekNineSchedule = Schedule(
    league = "Rising Star",
    season = "8",
    week = 9,
    matches = risingStarSeasonEightWeekNineMatches,
)
