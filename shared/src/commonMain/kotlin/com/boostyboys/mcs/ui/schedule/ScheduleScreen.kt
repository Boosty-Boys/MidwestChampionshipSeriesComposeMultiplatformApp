package com.boostyboys.mcs.ui.schedule

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.boostyboys.mcs.data.models.risingStarSeasonEightWeekNineSchedule
import com.boostyboys.mcs.designsystem.components.McsToolbar
import com.boostyboys.mcs.ui.match.MatchDetailsScreen

class ScheduleScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Scaffold(
                topBar = {
                    McsToolbar(
                        title = "Schedule",
                        subtitle = "Rising Star | Week 8",
                    )
                },
                content = {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                            .padding(horizontal = 16.dp),
                    ) {
                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(risingStarSeasonEightWeekNineSchedule.matches.first().date)
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        items(risingStarSeasonEightWeekNineSchedule.matches) { match ->
                            MatchCell(
                                modifier = Modifier.padding(vertical = 8.dp),
                                match = match,
                                onClick = {
                                    navigator.push(
                                        MatchDetailsScreen(match),
                                    )
                                },
                            )
                        }
                    }
                },
            )
        }
    }
}
