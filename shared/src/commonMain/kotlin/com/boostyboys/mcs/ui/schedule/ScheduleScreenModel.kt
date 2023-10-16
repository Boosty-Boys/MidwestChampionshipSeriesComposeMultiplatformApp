package com.boostyboys.mcs.ui.schedule

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.boostyboys.mcs.data.api.LocalRepository
import com.boostyboys.mcs.data.api.McsRepository
import com.boostyboys.mcs.data.api.either.Either
import com.boostyboys.mcs.data.api.models.League
import com.boostyboys.mcs.data.api.models.Season
import com.boostyboys.mcs.data.api.models.Week
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
    by StateHandlerDelegate(ScheduleState(), ScheduleViewState.Loading) {

    override fun handleAction(action: ScheduleAction) {
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
                is UpdateSelectedWeek -> {
                    updateSelectedWeek(action.week)
                }
                is HandleMatchClicked -> {
                    emitEffect(NavigateToMatchDetails(action.match))
                }
            }
        }
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
                val sortedWeeks = groupedMatches.keys.sortedBy {
                    it.value
                }
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

    private suspend fun updateViewWithMatchesForWeek(selectedWeek: Week, weeks: List<Week>) {
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

    private suspend fun updateSelectedWeek(week: Week) {
        localRepository.selectedWeek = week
        updateState { copy(selectedWeek = week) }
        handleAction(Initialize)
    }
}
