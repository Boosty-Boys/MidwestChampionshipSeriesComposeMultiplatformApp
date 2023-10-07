package com.boostyboys.mcs.ui.schedule

import androidx.compose.runtime.Immutable
import com.boostyboys.mcs.data.api.models.League
import com.boostyboys.mcs.data.api.models.Match
import com.boostyboys.mcs.data.api.models.Season

data class ScheduleState(
    val selectedSeason: Season? = null,
    val selectedLeague: League? = null,
    val selectedWeek: Int? = null,
    val seasons: List<Season> = emptyList(),
    val leagues: List<League> = emptyList(),
    val matchesByWeek: Map<Int, List<Match>> = emptyMap(),
)

sealed interface ScheduleViewState {
    @Immutable
    data object Loading : ScheduleViewState

    @Immutable
    data class Content(
        val selectedSeason: Season,
        val selectedLeague: League,
        val selectedWeek: Int,
        val seasons: List<Season>,
        val leagues: List<League>,
        val weeks: List<Int>,
        val matches: List<Match>,
    ) : ScheduleViewState

    @Immutable
    data class Error(val errorMessage: String? = null) : ScheduleViewState
}

sealed interface ScheduleAction {
    data object Initialize : ScheduleAction
    data class UpdateSelectedSeason(val season: Season) : ScheduleAction
    data class UpdateSelectedLeague(val league: League) : ScheduleAction
    data class UpdateSelectedWeek(val week: Int) : ScheduleAction
    data class HandleMatchClicked(val match: Match) : ScheduleAction
}

sealed interface ScheduleEffect {
    data class NavigateToMatchDetails(val match: Match) : ScheduleEffect
}
