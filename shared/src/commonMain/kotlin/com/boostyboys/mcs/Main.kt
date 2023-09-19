package com.boostyboys.mcs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.boostyboys.mcs.ui.bottomnavigation.McsBottomNavigation
import com.boostyboys.mcs.ui.bottomnavigation.ScheduleTab

@Composable fun Main() {
    TabNavigator(
        tab = ScheduleTab, // initial tab to show
    ) {
        Scaffold(
            content = {
                Box(modifier = Modifier.padding(it)) {
                    CurrentTab()
                }
            },
            bottomBar = {
                McsBottomNavigation()
            },
        )
    }
}
