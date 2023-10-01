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
import com.boostyboys.mcs.state.StateHandler
import com.boostyboys.mcs.state.StateHandlerDelegate
import com.boostyboys.mcs.ui.schedule.ScheduleAction.HandleMatchClicked
import com.boostyboys.mcs.ui.schedule.ScheduleAction.Initialize
import com.boostyboys.mcs.ui.schedule.ScheduleAction.UpdateSelectedLeague
import com.boostyboys.mcs.ui.schedule.ScheduleAction.UpdateSelectedSeason
import com.boostyboys.mcs.ui.schedule.ScheduleAction.UpdateSelectedWeek
import com.boostyboys.mcs.ui.schedule.ScheduleEffect.NavigateToMatchDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class ScheduleScreenModel(
    private val localRepository: LocalRepository,
    private val mcsRepository: McsRepository,
    private val dispatcher: CoroutineDispatcher,
) : ScreenModel,
    StateHandler<ScheduleState, ScheduleViewState, ScheduleAction, ScheduleEffect>
    by StateHandlerDelegate(ScheduleState(), ScheduleViewState.Loading)
{

    override fun handleAction(action: ScheduleAction) {
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
                is UpdateSelectedWeek -> {
                    updateSelectedWeek(action.week)
                }
                is HandleMatchClicked -> {
                    emitEffect(NavigateToMatchDetails(action.match))
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

    private suspend fun updateSelectedWeek(week: Int) {
        localRepository.selectedWeek = week
        updateState { copy(selectedWeek = week) }
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
                    updateViewState { ScheduleViewState.Error() }
                }
            }
            is Either.Failure -> {
                updateViewState { ScheduleViewState.Error(seasonsResult.error.message) }
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
                    getTeamsAndMatches(season = season, league = selectedLeague)
                } else {
                    updateViewState { ScheduleViewState.Error() }
                }
            }
            is Either.Failure -> {
                updateViewState { ScheduleViewState.Error(leaguesResult.error.message) }
            }
        }
    }

    private suspend fun getTeamsAndMatches(season: Season, league: League) {
        when (
            val matchesResult = mcsRepository.getMatches(
                seasonNumber = season.name,
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

                val selectedWeek = state.value.selectedWeek ?: sortedWeeks.firstOrNull()

                updateState {
                    copy(
                        matchesByWeek = sortedMatches,
                        selectedWeek = selectedWeek,
                    )
                }

                if (selectedWeek != null) {
                    updateViewWithMatchesForWeek(selectedWeek, sortedWeeks)
                } else {
                    updateViewState { ScheduleViewState.Error() }
                }
            }
            is Either.Failure -> {
                updateViewState { ScheduleViewState.Error(matchesResult.error.message) }
            }
        }
    }

    private suspend fun updateViewWithMatchesForWeek(selectedWeek: Int, weeks: List<Int>) {
        with(state.value) {
            val matches = matchesByWeek[selectedWeek]

            if (matches != null && selectedSeason != null && selectedLeague != null) {
                updateViewState {
                    ScheduleViewState.Content(
                        selectedSeason = selectedSeason,
                        selectedLeague = selectedLeague,
                        selectedWeek = selectedWeek,
                        matches = matches,
                        seasons = seasons,
                        leagues = leagues,
                        weeks = weeks,
                    )
                }
            } else {
                updateViewState { ScheduleViewState.Error() }
            }
        }
    }
}

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