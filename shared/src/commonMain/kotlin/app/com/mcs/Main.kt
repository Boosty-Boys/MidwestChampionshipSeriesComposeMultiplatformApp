package app.com.mcs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.com.mcs.ui.SampleAppToDelete
import app.com.mcs.ui.ScheduleTab
import app.com.mcs.ui.TeamTab
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.transitions.SlideTransition

@Composable fun Main() {
    TabNavigator(ScheduleTab) {
        Scaffold(
            content = {
                Box(modifier = Modifier.padding(it)) {
                    CurrentTab()
                }
            },
            bottomBar = {
                BottomNavigation {
                    TabNavigationItem(ScheduleTab)
                    TabNavigationItem(TeamTab)
                }
            },
        )
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        label = { Text(tab.options.title) },
        icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) },
    )
}

@Composable
fun Tab.TabContent() {
    val tabTitle = options.title

    Navigator(SampleAppToDelete(tabTitle = tabTitle)) { navigator ->
        SlideTransition(navigator) { screen ->
            Column {
                screen.Content()
            }
        }
    }
}

@Composable
fun CurrentTab() {
    val tabNavigator = LocalTabNavigator.current
    val currentTab = tabNavigator.current

    tabNavigator.saveableState("currentTab") {
        currentTab.Content()
    }
}
