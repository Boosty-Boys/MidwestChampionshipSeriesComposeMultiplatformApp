package com.boostyboys.mcs.ui.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.boostyboys.mcs.data.api.models.Match
import com.boostyboys.mcs.designsystem.components.ActionIconOptions
import com.boostyboys.mcs.designsystem.components.McsToolbar
import com.boostyboys.mcs.ui.McsStrings
import com.boostyboys.mcs.ui.MenuDialog
import com.boostyboys.mcs.ui.match.MatchDetailsScreen
import kotlinx.datetime.LocalDate

class ScheduleScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<ScheduleScreenModel>()
        val viewState = screenModel.viewState.collectAsState().value

        val dialogState = remember { mutableStateOf(false) }

        LifecycleEffect(
            onStarted = {
                screenModel.loadData()
            },
        )

        MenuDialog(
            dialogShowingState = dialogState,
            seasons = (viewState as? ScheduleViewState.Content)?.seasons ?: emptyList(),
            leagues = (viewState as? ScheduleViewState.Content)?.leagues ?: emptyList(),
            weeks = (viewState as? ScheduleViewState.Content)?.weeks,
            onSeasonClicked = {
                screenModel.updateSelectedSeason(it)
            },
            onLeagueClicked = {
                screenModel.updateSelectedLeague(it)
            },
            onWeekClicked = {
                screenModel.updateSelectedWeek(it)
            },
        )

        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Scaffold(
                topBar = {
                    McsToolbar(
                        title = McsStrings.SCHEDULE,
                        subtitle = (viewState as? ScheduleViewState.Content)?.let {
                            "Season ${viewState.selectedSeason.name} | ${viewState.selectedLeague.name} | " +
                                "${McsStrings.WEEK} ${viewState.selectedWeek}"
                        },
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
                    when (viewState) {
                        is ScheduleViewState.Loading -> {
                            Text("loading...")
                        }
                        is ScheduleViewState.Content -> {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(it)
                                    .padding(horizontal = 16.dp),
                            ) {
                                val matchesByDay = viewState.matches.groupBy { match ->
                                    match.dateTime.date
                                }

                                items(matchesByDay.toList()) { (date, matches) ->
                                    MatchDayBlock(
                                        date = date,
                                        matches = matches,
                                        onMatchClicked = { match ->
                                            navigator.push(
                                                MatchDetailsScreen(match),
                                            )
                                        },
                                    )
                                }

                                item {
                                    Spacer(modifier = Modifier.height(24.dp))
                                }
                            }
                        }
                        is ScheduleViewState.Error -> {
                            Column {
                                Text("error :(")
                                viewState.errorMessage?.let { error ->
                                    Text(error)
                                }
                            }
                        }
                    }
                },
            )
        }
    }

    @Composable
    private fun MatchDayBlock(
        date: LocalDate,
        matches: List<Match>,
        onMatchClicked: (Match) -> Unit,
    ) {
        val dateText = "${date.monthNumber}/${date.dayOfMonth}/${date.year}"

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
