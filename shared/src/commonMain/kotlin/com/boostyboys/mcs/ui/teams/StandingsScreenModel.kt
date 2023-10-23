package com.boostyboys.mcs.ui.teams

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.boostyboys.mcs.data.api.LocalRepository
import com.boostyboys.mcs.data.api.McsRepository
import com.boostyboys.mcs.data.api.either.Either
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
    by StateHandlerDelegate(StandingsState(), StandingsViewState.Loading) {

    override fun handleAction(action: StandingsAction) {
        super.handleAction(action)
        coroutineScope.launch(dispatcher) {
            when (action) {
                is Initialize -> {
                    getLeagues()
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

    private suspend fun getLeagues() {
        when (val leaguesResult = mcsRepository.getLeagues()) {
            is Either.Success -> {
                val selectedLeague = leaguesResult.value.find {
                    it.id == localRepository.selectedLeagueId
                } ?: leaguesResult.value.firstOrNull()

                if (selectedLeague != null) {
                    localRepository.selectedLeagueId = selectedLeague.id
                    updateState {
                        copy(
                            leagues = leaguesResult.value,
                        )
                    }
                } else {
                    updateViewState { StandingsViewState.Error("Error finding leagues") }
                    return
                }

                val selectedSeason = selectedLeague.seasons.find {
                    it.name == localRepository.selectedSeasonId
                } ?: selectedLeague.seasons.firstOrNull()

                selectedSeason?.let { localRepository.selectedSeasonId = it.name }

                if (selectedSeason != null) {
                    localRepository.selectedSeasonId = selectedSeason.name
                    getSeasonData(selectedSeason)
                } else {
                    updateViewState { StandingsViewState.Error("Error finding season") }
                }
            }
            is Either.Failure -> {
                updateViewState { StandingsViewState.Error(leaguesResult.error.message) }
            }
        }
    }

    private suspend fun getSeasonData(season: Season) {
        when (
            val seasonDataResult = mcsRepository.getSeasonData(
                seasonId = season.id,
                teamIds = season.teamIds,
            )
        ) {
            is Either.Success -> {
                updateState { copy(teams = seasonDataResult.value.teams) }

                with(state.value) {
                    val selectedLeague = leagues.find { it.id == localRepository.selectedLeagueId }
                    val selectedSeason = selectedLeague?.seasons?.find {
                        it.name == localRepository.selectedSeasonId
                    }

                    if (selectedLeague != null && selectedSeason != null) {
                        updateViewState {
                            StandingsViewState.Content(
                                selectedLeague = selectedLeague,
                                selectedSeason = selectedSeason,
                                teams = teams,
                                leagues = leagues,
                            )
                        }
                    } else {
                        updateViewState { StandingsViewState.Error() }
                    }
                }
            }
            is Either.Failure -> {
                updateViewState { StandingsViewState.Error(seasonDataResult.error.message) }
            }
        }
    }

    private fun updateSelectedSeason(season: Season) {
        localRepository.selectedSeasonId = season.name
        handleAction(Initialize)
    }

    private fun updateSelectedLeague(league: LeagueWithSeasons) {
        localRepository.selectedLeagueId = league.id
        handleAction(Initialize)
    }
}
