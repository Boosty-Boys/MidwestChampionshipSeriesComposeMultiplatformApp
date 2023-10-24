package com.boostyboys.mcs.ui.teams

import androidx.compose.runtime.Immutable
import com.boostyboys.mcs.data.api.models.LeagueSeasonConfig
import com.boostyboys.mcs.data.api.models.league.LeagueWithSeasons
import com.boostyboys.mcs.data.api.models.season.Season
import com.boostyboys.mcs.data.api.models.team.TeamWithResults

data class StandingsState(
    val leagueSeasonConfig: LeagueSeasonConfig?,
)

sealed interface StandingsViewState {
    @Immutable
    data object Loading : StandingsViewState

    @Immutable
    data class Content(
        val selectedLeague: LeagueWithSeasons,
        val selectedSeason: Season,
        val leagues: List<LeagueWithSeasons>,
        val teams: List<TeamWithResults>,
    ) : StandingsViewState

    @Immutable
    data class Error(val errorMessage: String? = null) : StandingsViewState
}

sealed interface StandingsAction {
    data object Initialize : StandingsAction
    data class UpdateSelectedSeason(val season: Season) : StandingsAction
    data class UpdateSelectedLeague(val league: LeagueWithSeasons) : StandingsAction
    data class HandleTeamClicked(val team: TeamWithResults) : StandingsAction
}

sealed interface StandingsEffect {
    data class NavigateToTeamDetails(val team: TeamWithResults) : StandingsEffect
}
