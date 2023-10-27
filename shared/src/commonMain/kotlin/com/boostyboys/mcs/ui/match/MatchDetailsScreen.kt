package com.boostyboys.mcs.ui.match

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.boostyboys.mcs.data.api.models.match.Match
import com.boostyboys.mcs.data.api.models.team.TeamWithResults
import com.boostyboys.mcs.designsystem.api.components.H1Text
import com.boostyboys.mcs.designsystem.api.components.McsScaffold
import com.boostyboys.mcs.designsystem.api.components.McsToolbar
import com.boostyboys.mcs.ui.teams.TeamDisplay

data class MatchDetailsScreen(
    val match: Match,
    val teamOne: TeamWithResults?,
    val teamTwo: TeamWithResults?,
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        McsScaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                McsToolbar(
                    title = "Match Details",
                    onBackClicked = {
                        navigator.pop()
                    },
                )
            },
            content = {
                Column(
                    modifier = Modifier.fillMaxSize()
                        .padding(it),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        TeamDisplayOrSpace(teamOne)

                        H1Text(
                            modifier = Modifier.weight(VS_WEIGHT),
                            text = "vs",
                            textAlign = TextAlign.Center,
                        )

                        TeamDisplayOrSpace(teamTwo)
                    }
                }
            },
        )
    }

    @Composable
    private fun RowScope.TeamDisplayOrSpace(team: TeamWithResults?) {
        team?.let {
            TeamDisplay(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                team = it,
                canNavigateToDetails = true,
            )
        } ?: Spacer(modifier = Modifier.weight(1f))
    }

    companion object {
        private const val VS_WEIGHT = 0.3f
    }
}
