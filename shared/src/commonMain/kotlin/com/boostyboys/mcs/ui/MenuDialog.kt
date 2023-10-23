package com.boostyboys.mcs.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.boostyboys.mcs.data.api.models.league.LeagueWithSeasons
import com.boostyboys.mcs.data.api.models.season.Season
import com.boostyboys.mcs.data.api.models.season.Week
import com.boostyboys.mcs.ui.MenuDialogConfig.LEAGUE_SELECTION
import com.boostyboys.mcs.ui.MenuDialogConfig.MENU_SELECTION
import com.boostyboys.mcs.ui.MenuDialogConfig.SEASON_SELECTION
import com.boostyboys.mcs.ui.MenuDialogConfig.WEEK_SELECTION

@Composable
fun MenuDialog(
    dialogShowingState: MutableState<Boolean>,
    selectedLeagueId: String,
    leagues: List<LeagueWithSeasons>,
    onSeasonClicked: (Season) -> Unit,
    onLeagueClicked: (LeagueWithSeasons) -> Unit,
    weeks: Int? = null,
    onWeekClicked: ((Week) -> Unit)? = null,
) {
    val configState = remember {
        mutableStateOf(MENU_SELECTION)
    }

    if (dialogShowingState.value) {
        Dialog(
            onDismissRequest = {
                dialogShowingState.value = false
                configState.value = MENU_SELECTION
            },
            content = {
                Surface {
                    Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                        when (configState.value) {
                            MENU_SELECTION -> {
                                MenuSelection(configState, weeks != null)
                            }
                            SEASON_SELECTION -> {
                                SeasonSelection(
                                    configState = configState,
                                    dialogShowingState = dialogShowingState,
                                    seasons = leagues.find {
                                        selectedLeagueId == it.id
                                    }?.seasons ?: emptyList(),
                                    onSeasonClicked = onSeasonClicked,
                                )
                            }
                            LEAGUE_SELECTION -> {
                                LeagueSelection(configState, dialogShowingState, leagues, onLeagueClicked)
                            }
                            WEEK_SELECTION -> {
                                if (weeks != null && onWeekClicked != null) {
                                    WeekSelection(configState, dialogShowingState, weeks, onWeekClicked)
                                }
                            }
                        }
                    }
                }
            },
            properties = DialogProperties(),
        )
    }
}

@Composable
private fun MenuSelection(
    configState: MutableState<MenuDialogConfig>,
    showWeeks: Boolean,
) {
    Column {
        Text(
            modifier = Modifier.clickable {
                configState.value = SEASON_SELECTION
            },
            text = "Select Season",
            fontSize = 18.sp,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier.clickable {
                configState.value = LEAGUE_SELECTION
            },
            text = "Select League",
            fontSize = 18.sp,
        )

        if (showWeeks) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.clickable {
                    configState.value = WEEK_SELECTION
                },
                text = "Select Week",
                fontSize = 18.sp,
            )
        }
    }
}

@Composable
private fun SeasonSelection(
    configState: MutableState<MenuDialogConfig>,
    dialogShowingState: MutableState<Boolean>,
    seasons: List<Season>,
    onSeasonClicked: (Season) -> Unit,
) {
    Column {
        seasons.forEach {
            Text(
                modifier = Modifier.clickable {
                    onSeasonClicked(it)
                    dialogShowingState.value = false
                    configState.value = MENU_SELECTION
                },
                text = it.name,
                fontSize = 18.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun LeagueSelection(
    configState: MutableState<MenuDialogConfig>,
    dialogShowingState: MutableState<Boolean>,
    leagues: List<LeagueWithSeasons>,
    onLeagueClicked: (LeagueWithSeasons) -> Unit,
) {
    Column {
        leagues.forEach {
            Text(
                modifier = Modifier.clickable {
                    onLeagueClicked(it)
                    dialogShowingState.value = false
                    configState.value = MENU_SELECTION
                },
                text = it.name,
                fontSize = 18.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun WeekSelection(
    configState: MutableState<MenuDialogConfig>,
    dialogShowingState: MutableState<Boolean>,
    weeks: Int,
    onWeekClicked: (Week) -> Unit,
) {
    Column {
        (1..weeks).forEach {
            Text(
                modifier = Modifier.clickable {
                    onWeekClicked(Week(it))
                    dialogShowingState.value = false
                    configState.value = MENU_SELECTION
                },
                text = "${McsStrings.WEEK} $it",
                fontSize = 18.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

private enum class MenuDialogConfig {
    MENU_SELECTION, SEASON_SELECTION, LEAGUE_SELECTION, WEEK_SELECTION
}
