package com.boostyboys.mcs.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.boostyboys.mcs.data.api.models.league.LeagueWithSeasons
import com.boostyboys.mcs.data.api.models.season.Season
import com.boostyboys.mcs.data.api.models.season.Week
import com.boostyboys.mcs.designsystem.api.components.ButtonText
import com.boostyboys.mcs.designsystem.api.components.McsCard
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
                McsCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    elevation = 8.dp,
                ) {
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        ButtonText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable {
                    configState.value = SEASON_SELECTION
                },
            text = "Select Season",
        )

        ButtonText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable {
                    configState.value = LEAGUE_SELECTION
                },
            text = "Select League",
        )

        if (showWeeks) {
            ButtonText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        configState.value = WEEK_SELECTION
                    },
                text = "Select Week",
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        seasons.forEach {
            ButtonText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        onSeasonClicked(it)
                        dialogShowingState.value = false
                        configState.value = MENU_SELECTION
                    },
                text = "Season ${it.name}",
            )
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        leagues.forEach {
            ButtonText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        onLeagueClicked(it)
                        dialogShowingState.value = false
                        configState.value = MENU_SELECTION
                    },
                text = it.name,
            )
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        for (week in 1..weeks) {
            ButtonText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        onWeekClicked(Week(week))
                        dialogShowingState.value = false
                        configState.value = MENU_SELECTION
                    },
                text = "${McsStrings.WEEK} $week",
            )
        }
    }
}

private enum class MenuDialogConfig {
    MENU_SELECTION, SEASON_SELECTION, LEAGUE_SELECTION, WEEK_SELECTION
}
