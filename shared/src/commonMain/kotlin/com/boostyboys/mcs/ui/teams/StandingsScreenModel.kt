package com.boostyboys.mcs.ui.teams

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.boostyboys.mcs.data.api.LocalRepository
import com.boostyboys.mcs.data.api.McsRepository
import com.boostyboys.mcs.data.api.models.league.LeagueWithSeasons
import com.boostyboys.mcs.data.api.models.season.Season
import com.boostyboys.mcs.state.StateHandler
import com.boostyboys.mcs.state.StateHandlerDelegate
import com.boostyboys.mcs.ui.teams.StandingsAction.HandleTeamClicked
import com.boostyboys.mcs.ui.teams.StandingsAction.Initialize
import com.boostyboys.mcs.ui.teams.StandingsAction.UpdateSelectedLeague
import com.boostyboys.mcs.ui.teams.StandingsAction.UpdateSelectedSeason
import com.boostyboys.mcs.ui.teams.StandingsEffect.NavigateToTeamDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class StandingsScreenModel(
    private val localRepository: LocalRepository,
    private val mcsRepository: McsRepository,
    private val dispatcher: CoroutineDispatcher,
) : ScreenModel,
    StateHandler<StandingsState, StandingsViewState, StandingsAction, StandingsEffect>
    by StateHandlerDelegate(StandingsState(null), StandingsViewState.Loading) {

    override fun handleAction(action: StandingsAction) {
        super.handleAction(action)
        coroutineScope.launch(dispatcher) {
            when (action) {
                is Initialize -> {
                    collectLeagueSeasonConfig()
                }
                is UpdateSelectedSeason -> {
                    updateSelectedSeason(action.season)
                }
                is UpdateSelectedLeague -> {
                    updateSelectedLeague(action.league)
                }
                is HandleTeamClicked -> {
                    emitEffect(NavigateToTeamDetails(action.team))
                }
            }
        }
    }

    private suspend fun collectLeagueSeasonConfig() {
        mcsRepository.leagueSeasonConfigFlow.collect {
            if (it == null) {
                updateViewState { StandingsViewState.Loading }
            } else {
                updateState { copy(leagueSeasonConfig = it) }
                updateViewWithStandings()
            }
        }
    }

    private suspend fun updateViewWithStandings() {
        state.value.leagueSeasonConfig?.let { config ->
            updateViewState {
                StandingsViewState.Content(
                    selectedLeague = config.selectedLeague,
                    selectedSeason = config.selectedSeason,
                    leagues = config.leagues,
                    teams = config.teams,
                )
            }
        } ?: updateViewState { StandingsViewState.Error() }
    }

    private fun updateSelectedSeason(season: Season) {
        localRepository.selectedSeasonId = season.id

        coroutineScope.launch(dispatcher) {
            mcsRepository.reloadLeagueSeasonConfig()
        }
    }

    private fun updateSelectedLeague(league: LeagueWithSeasons) {
        localRepository.selectedLeagueId = league.id

        coroutineScope.launch(dispatcher) {
            mcsRepository.reloadLeagueSeasonConfig()
        }
    }
}
