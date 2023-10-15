package com.boostyboys.mcs.ui.schedule

import androidx.compose.runtime.Immutable
import com.boostyboys.mcs.data.api.models.League
import com.boostyboys.mcs.data.api.models.Match
import com.boostyboys.mcs.data.api.models.Season
import com.boostyboys.mcs.data.api.models.Week

data class ScheduleState(
    val selectedSeason: Season? = null,
    val selectedLeague: League? = null,
    val selectedWeek: Week? = null,
    val seasons: List<Season> = emptyList(),
    val leagues: List<League> = emptyList(),
    val matchesByWeek: Map<Week, List<Match>> = emptyMap(),
)

sealed interface ScheduleViewState {
    @Immutable
    data object Loading : ScheduleViewState

    @Immutable
    data class Content(
        val selectedSeason: Season,
        val selectedLeague: League,
        val selectedWeek: Week,
        val seasons: List<Season>,
        val leagues: List<League>,
        val weeks: List<Week>,
        val matches: List<Match>,
    ) : ScheduleViewState

    @Immutable
    data class Error(val errorMessage: String? = null) : ScheduleViewState
}

sealed interface ScheduleAction {
    data object Initialize : ScheduleAction
    data class UpdateSelectedSeason(val season: Season) : ScheduleAction
    data class UpdateSelectedLeague(val league: League) : ScheduleAction
    data class UpdateSelectedWeek(val week: Week) : ScheduleAction
    data class HandleMatchClicked(val match: Match) : ScheduleAction
}

sealed interface ScheduleEffect {
    data class NavigateToMatchDetails(val match: Match) : ScheduleEffect
}
