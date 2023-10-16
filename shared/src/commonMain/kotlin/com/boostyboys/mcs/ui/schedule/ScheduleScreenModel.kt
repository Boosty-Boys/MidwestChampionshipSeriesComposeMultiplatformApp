package com.boostyboys.mcs.ui.schedule

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.boostyboys.mcs.data.api.LocalRepository
import com.boostyboys.mcs.data.api.McsRepository
import com.boostyboys.mcs.data.api.either.Either
import com.boostyboys.mcs.data.api.models.league.LeagueWithSeasons
import com.boostyboys.mcs.data.api.models.season.Season
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
                    getLeagues()
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
                    updateViewState { ScheduleViewState.Error("Error finding leagues") }
                    return
                }

                val selectedSeason = selectedLeague.seasons.find {
                    it.name == localRepository.selectedSeasonNumber
                } ?: selectedLeague.seasons.firstOrNull()

                selectedSeason?.let { localRepository.selectedSeasonNumber = it.name }

                if (selectedSeason != null) {
                    localRepository.selectedSeasonNumber = selectedSeason.name
                    getSeasonData(selectedSeason)
                } else {
                    updateViewState { ScheduleViewState.Error("Error finding season") }
                }
            }
            is Either.Failure -> {
                updateViewState { ScheduleViewState.Error(leaguesResult.error.message) }
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
                val matches = seasonDataResult.value.matches
                val groupedMatches = matches.groupBy { it.week }
                val sortedWeeks = groupedMatches.keys.sorted()
                val sortedMatches = sortedWeeks.associateWith { week ->
                    groupedMatches[week]?.sortedBy { it.scheduledDateTime } ?: emptyList()
                }

                val selectedWeek = localRepository.selectedWeek ?: sortedWeeks.firstOrNull()
                localRepository.selectedWeek = selectedWeek

                updateState {
                    copy(
                        teams = seasonDataResult.value.teams,
                        matchesByWeek = sortedMatches,
                    )
                }

                if (selectedWeek != null) {
                    updateViewWithMatchesForWeek(selectedWeek)
                } else {
                    updateViewState { ScheduleViewState.Error("Error finding weeks") }
                }
            }
            is Either.Failure -> {
                updateViewState { ScheduleViewState.Error(seasonDataResult.error.message) }
            }
        }
    }

    private suspend fun updateViewWithMatchesForWeek(selectedWeek: Int) {
        with(state.value) {
            val matches = matchesByWeek[selectedWeek]
            val selectedLeague = leagues.find { it.id == localRepository.selectedLeagueId }
            val selectedSeason = selectedLeague?.seasons?.find {
                it.name == localRepository.selectedSeasonNumber
            }

            if (matches != null && selectedLeague != null && selectedSeason != null) {
                updateViewState {
                    ScheduleViewState.Content(
                        selectedLeague = selectedLeague,
                        selectedSeason = selectedSeason,
                        selectedWeek = selectedWeek,
                        matches = matches,
                        leagues = leagues,
                    )
                }
            } else {
                updateViewState { ScheduleViewState.Error() }
            }
        }
    }

    private fun updateSelectedSeason(season: Season) {
        localRepository.selectedSeasonNumber = season.name
        handleAction(Initialize)
    }

    private fun updateSelectedLeague(league: LeagueWithSeasons) {
        localRepository.selectedLeagueId = league.id
        handleAction(Initialize)
    }

    private fun updateSelectedWeek(week: Int) {
        localRepository.selectedWeek = week
        handleAction(Initialize)
    }
}
