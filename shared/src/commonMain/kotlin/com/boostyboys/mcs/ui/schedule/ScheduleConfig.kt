package com.boostyboys.mcs.ui.schedule

import androidx.compose.runtime.Immutable
import com.boostyboys.mcs.data.api.models.LeagueSeasonConfig
import com.boostyboys.mcs.data.api.models.league.LeagueWithSeasons
import com.boostyboys.mcs.data.api.models.match.Match
import com.boostyboys.mcs.data.api.models.season.Season
import com.boostyboys.mcs.data.api.models.season.Week
import com.boostyboys.mcs.data.api.models.team.TeamWithResults

data class ScheduleState(
    val leagueSeasonConfig: LeagueSeasonConfig?,
)

sealed interface ScheduleViewState {
    @Immutable
    data object Loading : ScheduleViewState

    @Immutable
    data class Content(
        val selectedLeague: LeagueWithSeasons,
        val selectedSeason: Season,
        val selectedWeek: Week,
        val matchesForWeek: List<Match>,
        val leagues: List<LeagueWithSeasons>,
        val teams: List<TeamWithResults>,
    ) : ScheduleViewState

    @Immutable
    data class Error(val errorMessage: String? = null) : ScheduleViewState
}

sealed interface ScheduleAction {
    data object Initialize : ScheduleAction
    data class UpdateSelectedSeason(val season: Season) : ScheduleAction
    data class UpdateSelectedLeague(val league: LeagueWithSeasons) : ScheduleAction
    data class UpdateSelectedWeek(val week: Week) : ScheduleAction
    data class HandleMatchClicked(val match: Match) : ScheduleAction
}

sealed interface ScheduleEffect {
    data class NavigateToMatchDetails(val match: Match) : ScheduleEffect
}
