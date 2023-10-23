package com.boostyboys.mcs.ui.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.boostyboys.mcs.data.api.models.league.LeagueWithSeasons
import com.boostyboys.mcs.data.api.models.match.Match
import com.boostyboys.mcs.data.api.models.season.Season
import com.boostyboys.mcs.data.api.models.season.Week
import com.boostyboys.mcs.designsystem.components.ActionIconOptions
import com.boostyboys.mcs.designsystem.components.McsToolbar
import com.boostyboys.mcs.ui.McsStrings
import com.boostyboys.mcs.ui.MenuDialog
import com.boostyboys.mcs.ui.match.MatchDetailsScreen
import com.boostyboys.mcs.ui.schedule.ScheduleAction.Initialize
import com.boostyboys.mcs.ui.schedule.ScheduleAction.UpdateSelectedLeague
import com.boostyboys.mcs.ui.schedule.ScheduleAction.UpdateSelectedSeason
import com.boostyboys.mcs.ui.schedule.ScheduleAction.UpdateSelectedWeek
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class ScheduleScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<ScheduleScreenModel>()
        val viewState = screenModel.viewState.collectAsState().value

        val dialogState = remember { mutableStateOf(false) }

        LifecycleEffect(
            onStarted = {
                screenModel.handleAction(Initialize)
            },
        )

        LaunchedEffect(screenModel) {
            screenModel.effect.collect { effect ->
                when (effect) {
                    is ScheduleEffect.NavigateToMatchDetails -> {
                        navigator.push(MatchDetailsScreen(effect.match))
                    }
                }
            }
        }

        when (viewState) {
            is ScheduleViewState.Content -> ScheduleContent(
                viewState = viewState,
                dialogState = dialogState,
                onSeasonClicked = {
                    screenModel.handleAction(UpdateSelectedSeason(it))
                },
                onLeagueClicked = {
                    screenModel.handleAction(UpdateSelectedLeague(it))
                },
                onWeekClicked = {
                    screenModel.handleAction(UpdateSelectedWeek(it))
                },
                onMatchClicked = {
                    screenModel.handleAction(
                        ScheduleAction.HandleMatchClicked(it),
                    )
                },
            )

            is ScheduleViewState.Error -> ScheduleError(errorMessage = viewState.errorMessage)
            is ScheduleViewState.Loading -> ScheduleLoading()
        }
    }

    @Composable
    private fun ScheduleContent(
        viewState: ScheduleViewState.Content,
        dialogState: MutableState<Boolean>,
        onSeasonClicked: (Season) -> Unit,
        onLeagueClicked: (LeagueWithSeasons) -> Unit,
        onWeekClicked: (Week) -> Unit,
        onMatchClicked: (Match) -> Unit,
    ) {
        MenuDialog(
            dialogShowingState = dialogState,
            selectedLeagueId = (viewState as? ScheduleViewState.Content)?.selectedLeague?.id ?: "",
            leagues = (viewState as? ScheduleViewState.Content)?.leagues ?: emptyList(),
            weeks = (viewState as? ScheduleViewState.Content)?.selectedSeason?.regularSeasonWeeks,
            onSeasonClicked = onSeasonClicked,
            onLeagueClicked = onLeagueClicked,
            onWeekClicked = onWeekClicked,
        )

        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Scaffold(
                topBar = {
                    McsToolbar(
                        title = McsStrings.SCHEDULE,
                        subtitle = "Season ${viewState.selectedSeason.name} | ${viewState.selectedLeague.name} | " +
                            "${McsStrings.WEEK} ${viewState.selectedWeek.value}",
                        actionIconOptions = ActionIconOptions(
                            icon = Icons.Default.Menu,
                            contentDescription = McsStrings.MENU,
                            onClick = {
                                dialogState.value = true
                            },
                        ),
                    )
                },
                content = {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                            .padding(horizontal = 16.dp),
                    ) {
                        val matchesByDay = viewState.matches.groupBy { match ->
                            match.scheduledDateTime?.let { dateTime ->
                                Instant.parse(dateTime).toLocalDateTime(
                                    TimeZone.currentSystemDefault(),
                                ).date
                            }
                        }

                        items(matchesByDay.toList()) { (date, matches) ->
                            MatchDayBlock(
                                date = date,
                                matches = matches,
                                onMatchClicked = onMatchClicked,
                            )
                        }

                        item {
                            Spacer(modifier = Modifier.height(24.dp))
                        }
                    }
                },
            )
        }
    }

    @Composable
    private fun ScheduleLoading() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    }

    @Composable
    private fun ScheduleError(errorMessage: String?) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text("error :(")
            errorMessage?.let { error ->
                Text(error)
            }
        }
    }

    @Composable
    private fun MatchDayBlock(
        date: LocalDate?,
        matches: List<Match>,
        onMatchClicked: (Match) -> Unit,
    ) {
        val dateText = "${date?.monthNumber}/${date?.dayOfMonth}/${date?.year}"

        Column {
            Spacer(modifier = Modifier.height(16.dp))
            Text(dateText)
            Spacer(modifier = Modifier.height(8.dp))

            matches.forEach { match ->
                MatchCell(
                    modifier = Modifier.padding(vertical = 8.dp),
                    match = match,
                    onClick = onMatchClicked,
                )
            }
        }
    }
}
