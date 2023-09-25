package com.boostyboys.mcs.ui.teams

import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.boostyboys.mcs.data.api.models.Team
import com.boostyboys.mcs.designsystem.components.McsToolbar

class StandingsScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<StandingsScreenModel>()
        val viewState = screenModel.viewState.collectAsState().value

        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Scaffold(
                topBar = {
                    McsToolbar(
                        title = "Standings",
                        subtitle = "Rising Star",
                    )
                },
                content = { paddingValues ->
                    when (viewState) {
                        is StandingsViewState.Loading -> {
                            Text("loading...")
                        }
                        is StandingsViewState.Content -> {
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
                                        (it.wins.toDouble() / (it.wins + it.losses).toDouble())
                                    },
                                ) { team ->
                                    TeamCell(
                                        team = team,
                                        onTeamClicked = {
                                            navigator.push(TeamDetailsScreen(team))
                                        },
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                }
                            }
                        }
                        is StandingsViewState.Error -> {
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
    private fun TeamCell(
        team: Team,
        onTeamClicked: (Team) -> Unit,
    ) {
        val logo = rememberVectorPainter(team.logo)

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clickable {
                    onTeamClicked(team)
                },
            color = Color.LightGray.copy(alpha = .5f),
            shape = RoundedCornerShape(8.dp),
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = logo,
                    contentDescription = null,
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    modifier = Modifier.weight(1f),
                    text = team.name,
                )

                Text(text = "${team.wins}-${team.losses}")
            }
        }
    }
}
