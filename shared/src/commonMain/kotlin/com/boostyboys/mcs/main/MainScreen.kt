package com.boostyboys.mcs.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.boostyboys.mcs.ui.bottomnavigation.McsBottomNavigation
import com.boostyboys.mcs.ui.bottomnavigation.ScheduleTab

class MainScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<MainScreenModel>()
        val viewState = screenModel.viewState.collectAsState().value

        LifecycleEffect(
            onStarted = {
                screenModel.handleAction(MainAction.Initialize)
            },
        )

        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            when (viewState) {
                is MainViewState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is MainViewState.Content -> {
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
            }
        }
    }
}
