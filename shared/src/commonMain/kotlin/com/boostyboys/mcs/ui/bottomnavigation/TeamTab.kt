package com.boostyboys.mcs.ui.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import com.boostyboys.mcs.ui.teams.StandingsScreen

object TeamTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = "Teams"
            val icon = rememberVectorPainter(Icons.Default.Person)

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon,
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(StandingsScreen()) { navigator ->
            SlideTransition(navigator) { screen ->
                screen.Content()
            }
        }
    }
}
