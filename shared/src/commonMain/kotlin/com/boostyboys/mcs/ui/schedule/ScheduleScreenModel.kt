package com.boostyboys.mcs.ui.schedule

import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.boostyboys.mcs.data.api.LocalRepository
import com.boostyboys.mcs.data.api.McsRepository
import com.boostyboys.mcs.data.api.either.Either
import com.boostyboys.mcs.data.api.models.League
import com.boostyboys.mcs.data.api.models.Match
import com.boostyboys.mcs.data.api.models.Season
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ScheduleScreenModel(
    private val localRepository: LocalRepository,
    private val mcsRepository: McsRepository,
) : ScreenModel {

    private val _state: MutableStateFlow<ScheduleState> = MutableStateFlow(ScheduleState())
    private val state: StateFlow<ScheduleState> get() = _state

    private val _viewState: MutableStateFlow<ScheduleViewState> = MutableStateFlow(ScheduleViewState.Loading)
    val viewState: StateFlow<ScheduleViewState> get() = _viewState

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
                    _viewState.emit(ScheduleViewState.Error())
                }
            }
            is Either.Failure -> {
                _viewState.emit(ScheduleViewState.Error(seasonsResult.error.message))
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
                    getTeamsAndMatches(season = season, league = selectedLeague)
                } else {
                    _viewState.emit(ScheduleViewState.Error())
                }
            }
            is Either.Failure -> {
                _viewState.emit(ScheduleViewState.Error(leaguesResult.error.message))
            }
        }
    }

    private suspend fun getTeamsAndMatches(season: Season, league: League) {
        when (
            val matchesResult = mcsRepository.getMatches(
                seasonId = season.id,
                leagueId = league.id,
            )
        ) {
            is Either.Success -> {
                // sort the matches into a map grouped by week and sorted by datetime
                val matches = matchesResult.value
                val groupedMatches = matches.groupBy { it.week }
                val sortedWeeks = groupedMatches.keys.sorted()
                val sortedMatches = sortedWeeks.associateWith { week ->
                    groupedMatches[week]?.sortedBy { it.dateTime } ?: emptyList()
                }

                _state.value = state.value.copy(matchesByWeek = sortedMatches)

                val defaultWeek = sortedMatches.keys.firstOrNull()

                if (defaultWeek != null) {
                    updateViewWithMatchesForWeek(defaultWeek)
                } else {
                    _viewState.emit(ScheduleViewState.Error())
                }
            }
            is Either.Failure -> {
                _viewState.emit(ScheduleViewState.Error(matchesResult.error.message))
            }
        }
    }

    private suspend fun updateViewWithMatchesForWeek(week: Int) {
        with(state.value) {
            val matches = matchesByWeek[week]

            if (matches != null && season != null && league != null) {
                _viewState.emit(
                    ScheduleViewState.Content(
                        season = season,
                        league = league,
                        week = week,
                        matches = matches,
                    ),
                )
            } else {
                _viewState.emit(ScheduleViewState.Error())
            }
        }
    }
}

data class ScheduleState(
    val season: Season? = null,
    val league: League? = null,
    val matchesByWeek: Map<Int, List<Match>> = emptyMap(),
)

sealed interface ScheduleViewState {
    @Immutable
    data object Loading : ScheduleViewState

    @Immutable
    data class Content(
        val season: Season,
        val league: League,
        val week: Int,
        val matches: List<Match>,
    ) : ScheduleViewState

    @Immutable
    data class Error(val errorMessage: String? = null) : ScheduleViewState
}
