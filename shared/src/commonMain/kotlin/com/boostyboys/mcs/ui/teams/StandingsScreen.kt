package com.boostyboys.mcs.ui.teams

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.boostyboys.mcs.data.api.models.season.Season
import com.boostyboys.mcs.data.api.models.team.TeamWithResults
import com.boostyboys.mcs.designsystem.components.ActionIconOptions
import com.boostyboys.mcs.designsystem.components.McsToolbar
import com.boostyboys.mcs.designsystem.components.TeamLogo
import com.boostyboys.mcs.designsystem.theme.AppTheme
import com.boostyboys.mcs.ui.McsStrings
import com.boostyboys.mcs.ui.MenuDialog
import com.boostyboys.mcs.ui.teams.StandingsAction.HandleTeamClicked
import com.boostyboys.mcs.ui.teams.StandingsAction.Initialize
import com.boostyboys.mcs.ui.teams.StandingsAction.UpdateSelectedLeague
import com.boostyboys.mcs.ui.teams.StandingsAction.UpdateSelectedSeason

class StandingsScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<StandingsScreenModel>()
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
                    is StandingsEffect.NavigateToTeamDetails -> {
                        navigator.push(TeamDetailsScreen(effect.team))
                    }
                }
            }
        }

        AppTheme {
            when (viewState) {
                is StandingsViewState.Loading -> StandingsLoading()
                is StandingsViewState.Content -> StandingsContent(
                    viewState = viewState,
                    dialogState = dialogState,
                    onSeasonClicked = { season ->
                        screenModel.handleAction(UpdateSelectedSeason(season))
                    },
                    onLeagueClicked = { league ->
                        screenModel.handleAction(UpdateSelectedLeague(league))
                    },
                    onTeamClicked = { team ->
                        screenModel.handleAction(HandleTeamClicked(team))
                    },
                )

                is StandingsViewState.Error -> StandingsError(errorMessage = viewState.errorMessage)
            }
        }
    }

    @Composable
    private fun StandingsContent(
        viewState: StandingsViewState.Content,
        dialogState: MutableState<Boolean>,
        onSeasonClicked: (Season) -> Unit,
        onLeagueClicked: (LeagueWithSeasons) -> Unit,
        onTeamClicked: (TeamWithResults) -> Unit,
    ) {
        MenuDialog(
            dialogShowingState = dialogState,
            selectedLeagueId = viewState.selectedLeague.id,
            leagues = viewState.leagues,
            onSeasonClicked = onSeasonClicked,
            onLeagueClicked = onLeagueClicked,
        )

        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Scaffold(
                topBar = {
                    McsToolbar(
                        title = McsStrings.STANDINGS,
                        subtitle = "Season ${viewState.selectedSeason.name} | ${viewState.selectedLeague.name}",
                        actionIconOptions = ActionIconOptions(
                            icon = Icons.Default.Menu,
                            contentDescription = McsStrings.MENU,
                            onClick = {
                                dialogState.value = true
                            },
                        ),
                    )
                },
                content = { paddingValues ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .padding(horizontal = 16.dp),
                    ) {
                        item {
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        items(
                            viewState.teams.sortedByDescending {
                                (it.matchesWon.toDouble() / (it.matchesPlayed).toDouble())
                            },
                        ) { team ->
                            TeamCell(
                                team = team,
                                onTeamClicked = onTeamClicked,
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                },
            )
        }
    }

    @Composable
    private fun TeamCell(
        team: TeamWithResults,
        onTeamClicked: (TeamWithResults) -> Unit,
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clickable {
                    onTeamClicked(team)
                },
            shape = MaterialTheme.shapes.large,
            tonalElevation = 8.dp,
            shadowElevation = 8.dp,
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TeamLogo(
                    modifier = Modifier.size(32.dp),
                    logoUrl = team.avatar,
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    modifier = Modifier.weight(1f),
                    text = team.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Text(
                    text = "${team.matchesWon}-${team.matchesPlayed - team.matchesWon}",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }

    @Composable
    private fun StandingsLoading() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    }

    @Composable
    private fun StandingsError(errorMessage: String?) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "error :(",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.error,
            )
            errorMessage?.let { error ->
                Text(
                    text = error,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error,
                )
            }
        }
    }
}
