package com.boostyboys.mcs.ui.schedule

import androidx.compose.runtime.Immutable
import com.boostyboys.mcs.data.api.models.league.LeagueWithSeasons
import com.boostyboys.mcs.data.api.models.match.Match
import com.boostyboys.mcs.data.api.models.season.Season
import com.boostyboys.mcs.data.api.models.season.TeamWithResults

data class ScheduleState(
    val leagues: List<LeagueWithSeasons> = emptyList(),
    val teams: List<TeamWithResults> = emptyList(),
    val matchesByWeek: Map<Int, List<Match>> = emptyMap(),
)

sealed interface ScheduleViewState {
    @Immutable
    data object Loading : ScheduleViewState

    @Immutable
    data class Content(
        val selectedLeague: LeagueWithSeasons,
        val selectedSeason: Season,
        val selectedWeek: Int,
        val matches: List<Match>,
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
    data class UpdateSelectedWeek(val week: Int) : ScheduleAction
    data class HandleMatchClicked(val match: Match) : ScheduleAction
}

sealed interface ScheduleEffect {
    data class NavigateToMatchDetails(val match: Match) : ScheduleEffect
}
