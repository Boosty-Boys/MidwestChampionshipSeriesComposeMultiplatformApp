package com.boostyboys.mcs.data.api.models

import com.boostyboys.mcs.data.api.models.league.LeagueWithSeasons
import com.boostyboys.mcs.data.api.models.match.Match
import com.boostyboys.mcs.data.api.models.season.Season
import com.boostyboys.mcs.data.api.models.season.Week
import com.boostyboys.mcs.data.api.models.team.TeamWithResults

data class LeagueSeasonConfig(
    val selectedLeague: LeagueWithSeasons,
    val selectedSeason: Season,
    val leagues: List<LeagueWithSeasons>,
    val teams: List<TeamWithResults>,
    val schedule: Map<Week, List<Match>>,
)
