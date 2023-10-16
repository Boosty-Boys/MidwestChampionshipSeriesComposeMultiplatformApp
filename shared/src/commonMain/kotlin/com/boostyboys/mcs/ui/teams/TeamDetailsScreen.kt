package com.boostyboys.mcs.ui.teams

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.boostyboys.mcs.data.api.models.season.TeamWithResults
import com.boostyboys.mcs.designsystem.components.McsToolbar

data class TeamDetailsScreen(val team: TeamWithResults) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Scaffold(
                topBar = {
                    McsToolbar(
                        title = team.name,
                        onBackClicked = {
                            navigator.pop()
                        },
                    )
                },
                content = {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Spacer(modifier = Modifier.height(64.dp))

                        TeamDisplay(team)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "${team.matchesWon}-${team.matchesPlayed - team.matchesWon}")

                        Spacer(modifier = Modifier.height(40.dp))

//                        team.players.forEach {
//                            Text(
//                                modifier = Modifier.align(Alignment.Start),
//                                text = it.name,
//                            )
//
//                            Spacer(modifier = Modifier.height(16.dp))
//                        }
                    }
                },
            )
        }
    }
}
