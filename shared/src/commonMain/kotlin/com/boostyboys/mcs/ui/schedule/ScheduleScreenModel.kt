package com.boostyboys.mcs.ui.schedule

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.boostyboys.mcs.data.api.LocalRepository
import com.boostyboys.mcs.data.api.McsRepository
import com.boostyboys.mcs.data.api.models.league.LeagueWithSeasons
import com.boostyboys.mcs.data.api.models.season.Season
import com.boostyboys.mcs.data.api.models.season.Week
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
    by StateHandlerDelegate(ScheduleState(null), ScheduleViewState.Loading) {

    override fun handleAction(action: ScheduleAction) {
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
                is UpdateSelectedWeek -> {
                    updateSelectedWeek(action.week)
                }
                is HandleMatchClicked -> {
                    emitEffect(
                        NavigateToMatchDetails(
                            action.match,
                            action.teamOne,
                            action.teamTwo,
                        ),
                    )
                }
            }
        }
    }

    private suspend fun collectLeagueSeasonConfig() {
        mcsRepository.leagueSeasonConfigFlow.collect {
            if (it == null) {
                updateViewState { ScheduleViewState.Loading }
            } else {
                updateState { copy(leagueSeasonConfig = it) }
                updateViewWithSchedule()
            }
        }
    }

    private suspend fun updateViewWithSchedule(week: Week? = null) {
        state.value.leagueSeasonConfig?.let { config ->
            val selectedWeek = week ?: localRepository.selectedWeek ?: Week(config.selectedLeague.currentWeek.toInt())
            localRepository.selectedWeek = selectedWeek

            val matches = config.schedule[selectedWeek]

            if (matches != null) {
                updateViewState {
                    ScheduleViewState.Content(
                        selectedLeague = config.selectedLeague,
                        selectedSeason = config.selectedSeason,
                        selectedWeek = selectedWeek,
                        matchesForWeek = matches,
                        leagues = config.leagues,
                        teams = config.teams,
                    )
                }
            } else {
                updateViewState { ScheduleViewState.Error() }
            }
        } ?: updateViewState { ScheduleViewState.Error() }
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

    private fun updateSelectedWeek(week: Week) {
        localRepository.selectedWeek = week

        coroutineScope.launch(dispatcher) {
            updateViewWithSchedule(week)
        }
    }
}
