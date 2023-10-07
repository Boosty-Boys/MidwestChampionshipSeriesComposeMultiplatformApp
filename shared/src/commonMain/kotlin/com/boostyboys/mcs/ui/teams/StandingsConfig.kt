package com.boostyboys.mcs.ui.teams

import androidx.compose.runtime.Immutable
import com.boostyboys.mcs.data.api.models.League
import com.boostyboys.mcs.data.api.models.Season
import com.boostyboys.mcs.data.api.models.Team

data class StandingsState(
    val selectedSeason: Season? = null,
    val selectedLeague: League? = null,
    val teams: List<Team> = emptyList(),
    val seasons: List<Season> = emptyList(),
    val leagues: List<League> = emptyList(),
)

sealed interface StandingsViewState {
    @Immutable
    data object Loading : StandingsViewState

    @Immutable
    data class Content(
        val selectedSeason: Season,
        val selectedLeague: League,
        val teams: List<Team>,
        val seasons: List<Season>,
        val leagues: List<League>,
    ) : StandingsViewState

    @Immutable
    data class Error(val errorMessage: String? = null) : StandingsViewState
}

sealed interface StandingsAction {
    data object Initialize : StandingsAction
    data class UpdateSelectedSeason(val season: Season) : StandingsAction
    data class UpdateSelectedLeague(val league: League) : StandingsAction
    data class HandleTeamClicked(val team: Team) : StandingsAction
}

sealed interface StandingsEffect {
    data class NavigateToTeamDetails(val team: Team) : StandingsEffect
}
