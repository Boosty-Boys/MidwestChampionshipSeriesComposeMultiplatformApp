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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.boostyboys.mcs.data.api.models.Match
import com.boostyboys.mcs.designsystem.components.ActionIconOptions
import com.boostyboys.mcs.designsystem.components.McsToolbar
import com.boostyboys.mcs.ui.McsStrings
import com.boostyboys.mcs.ui.match.MatchDetailsScreen
import kotlinx.datetime.LocalDate

class ScheduleScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<ScheduleScreenModel>()
        val viewState = screenModel.viewState.collectAsState().value

        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Scaffold(
                topBar = {
                    McsToolbar(
                        title = McsStrings.SCHEDULE,
                        subtitle = (viewState as? ScheduleViewState.Content)?.let {
                            "${viewState.league.name} | ${McsStrings.WEEK} ${viewState.week}"
                        },
                        actionIconOptions = ActionIconOptions(
                            icon = Icons.Default.Menu,
                            contentDescription = McsStrings.MENU,
                            onClick = {
                                // TODO show menu to change league or season
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

                                items(matchesByDay.toList()) { (localDate, matches) ->
                                    MatchDayBlock(
                                        date = localDate,
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
        Column {
            Spacer(modifier = Modifier.height(16.dp))
            Text(date.toString())
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
