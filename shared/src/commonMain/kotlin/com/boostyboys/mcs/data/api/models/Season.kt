package com.boostyboys.mcs.data.api.models

data class Season(
    val id: String,
    val name: String,
    val order: Int,
    val leagueIds: List<String>,
    val weeks: Int,
)

// Season 1: MNRS(10 teams), CLMN(10 teams), MNCS(10 teams)
// Season 2: Prospect(12 teams), Rising Star(12 teams), Challenger(12 teams), Premier(12 teams)
// Season 3: Rising Star(16 teams), Challenger(16 teams), Premier(16 teams)

// potential way to store historical team data?
// data class Franchise(
//    val teams: List<Team>,
// )
