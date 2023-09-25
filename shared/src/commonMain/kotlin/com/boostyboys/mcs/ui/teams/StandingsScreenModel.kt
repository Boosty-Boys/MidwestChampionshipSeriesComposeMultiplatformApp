package com.boostyboys.mcs.ui.teams

import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.boostyboys.mcs.data.api.LocalRepository
import com.boostyboys.mcs.data.api.McsRepository
import com.boostyboys.mcs.data.api.either.Either
import com.boostyboys.mcs.data.api.models.League
import com.boostyboys.mcs.data.api.models.Season
import com.boostyboys.mcs.data.api.models.Team
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StandingsScreenModel(
    private val localRepository: LocalRepository,
    private val mcsRepository: McsRepository,
) : ScreenModel {

    private val _state: MutableStateFlow<StandingsState> = MutableStateFlow(StandingsState())
    private val state: StateFlow<StandingsState> get() = _state

    private val _viewState: MutableStateFlow<StandingsViewState> = MutableStateFlow(StandingsViewState.Loading)
    val viewState: StateFlow<StandingsViewState> get() = _viewState

    init {
        coroutineScope.launch {
            getSeasons()
        }
    }

    private suspend fun getSeasons() {
        when (val seasonsResult = mcsRepository.getSeasons()) {
            is Either.Success -> {
                val selectedSeason = seasonsResult.value.find {
                    it.id == localRepository.selectedSeasonId
                } ?: seasonsResult.value.firstOrNull()

                if (selectedSeason != null) {
                    _state.value = state.value.copy(season = selectedSeason)
                    getLeagues(selectedSeason)
                } else {
                    _viewState.emit(StandingsViewState.Error())
                }
            }
            is Either.Failure -> {
                _viewState.emit(StandingsViewState.Error(seasonsResult.error.message))
            }
        }
    }

    private suspend fun getLeagues(season: Season) {
        when (val leaguesResult = mcsRepository.getLeagues(season.id)) {
            is Either.Success -> {
                val selectedLeague = leaguesResult.value.find {
                    it.id == localRepository.selectedLeagueId
                } ?: leaguesResult.value.firstOrNull()

                if (selectedLeague != null) {
                    _state.value = state.value.copy(league = selectedLeague)
                    getTeams(season = season, league = selectedLeague)
                } else {
                    _viewState.emit(StandingsViewState.Error())
                }
            }
            is Either.Failure -> {
                _viewState.emit(StandingsViewState.Error(leaguesResult.error.message))
            }
        }
    }

    private suspend fun getTeams(season: Season, league: League) {
        when (
            val teamsResult = mcsRepository.getTeams(
                seasonId = season.id,
                leagueId = league.id,
            )
        ) {
            is Either.Success -> {
                _viewState.emit(
                    StandingsViewState.Content(
                        season = season,
                        league = league,
                        teams = teamsResult.value,
                    ),
                )
            }
            is Either.Failure -> {
                _viewState.emit(StandingsViewState.Error(teamsResult.error.message))
            }
        }
    }
}

data class StandingsState(
    val season: Season? = null,
    val league: League? = null,
    val teams: List<Team> = emptyList(),
)

sealed interface StandingsViewState {
    @Immutable
    data object Loading : StandingsViewState

    @Immutable
    data class Content(
        val season: Season,
        val league: League,
        val teams: List<Team>,
    ) : StandingsViewState

    @Immutable
    data class Error(val errorMessage: String? = null) : StandingsViewState
}
