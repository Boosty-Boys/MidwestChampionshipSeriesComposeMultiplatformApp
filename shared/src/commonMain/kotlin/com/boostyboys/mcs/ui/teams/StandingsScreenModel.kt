package com.boostyboys.mcs.ui.teams

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.boostyboys.mcs.data.api.LocalRepository
import com.boostyboys.mcs.data.api.McsRepository
import com.boostyboys.mcs.data.api.either.Either
import com.boostyboys.mcs.data.api.models.League
import com.boostyboys.mcs.data.api.models.Season
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
    by StateHandlerDelegate(StandingsState(), StandingsViewState.Loading) {

    override fun handleAction(action: StandingsAction) {
        super.handleAction(action)
        coroutineScope.launch(dispatcher) {
            when (action) {
                is Initialize -> {
                    getSeasons()
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

    private suspend fun updateSelectedSeason(season: Season) {
        localRepository.selectedSeasonNumber = season.name
        updateState { copy(selectedSeason = season) }
        handleAction(Initialize)
    }

    private suspend fun updateSelectedLeague(league: League) {
        localRepository.selectedLeagueId = league.id
        updateState { copy(selectedLeague = league) }
        handleAction(Initialize)
    }

    private suspend fun getSeasons() {
        when (val seasonsResult = mcsRepository.getSeasons()) {
            is Either.Success -> {
                val selectedSeason = seasonsResult.value.find {
                    it.name == localRepository.selectedSeasonNumber
                } ?: seasonsResult.value.firstOrNull()

                if (selectedSeason != null) {
                    updateState {
                        copy(
                            selectedSeason = selectedSeason,
                            seasons = seasonsResult.value,
                        )
                    }

                    getLeagues(selectedSeason)
                } else {
                    updateViewState { StandingsViewState.Error() }
                }
            }
            is Either.Failure -> {
                updateViewState { StandingsViewState.Error(seasonsResult.error.message) }
            }
        }
    }

    private suspend fun getLeagues(season: Season) {
        when (val leaguesResult = mcsRepository.getLeagues(season.name)) {
            is Either.Success -> {
                val selectedLeague = leaguesResult.value.find {
                    it.id == localRepository.selectedLeagueId
                } ?: leaguesResult.value.firstOrNull()

                if (selectedLeague != null) {
                    updateState {
                        copy(
                            selectedLeague = selectedLeague,
                            leagues = leaguesResult.value,
                        )
                    }

                    getTeams(season = season, league = selectedLeague)
                } else {
                    updateViewState { StandingsViewState.Error() }
                }
            }
            is Either.Failure -> {
                updateViewState { StandingsViewState.Error(leaguesResult.error.message) }
            }
        }
    }

    private suspend fun getTeams(season: Season, league: League) {
        when (
            val teamsResult = mcsRepository.getTeams(
                seasonNumber = season.name,
                leagueId = league.id,
            )
        ) {
            is Either.Success -> {
                updateViewState {
                    StandingsViewState.Content(
                        selectedSeason = season,
                        selectedLeague = league,
                        teams = teamsResult.value,
                        seasons = state.value.seasons,
                        leagues = state.value.leagues,
                    )
                }
            }
            is Either.Failure -> {
                updateViewState { StandingsViewState.Error(teamsResult.error.message) }
            }
        }
    }
}
