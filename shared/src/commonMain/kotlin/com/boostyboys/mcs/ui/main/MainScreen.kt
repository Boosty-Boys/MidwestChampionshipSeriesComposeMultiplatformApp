package com.boostyboys.mcs.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.boostyboys.mcs.designsystem.api.components.McsBottomNavigation
import com.boostyboys.mcs.designsystem.api.components.McsFullScreenLoader
import com.boostyboys.mcs.designsystem.api.components.McsScaffold
import com.boostyboys.mcs.designsystem.api.theme.McsTheme
import com.boostyboys.mcs.navigation.ScheduleTab

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
            color = McsTheme.colors.background,
            contentColor = McsTheme.colors.onBackground,
            content = {
                when (viewState) {
                    is MainViewState.Loading -> McsFullScreenLoader()
                    is MainViewState.Content -> TabNavigator(
                        tab = ScheduleTab, // initial tab to show
                    ) {
                        McsScaffold(
                            modifier = Modifier.fillMaxSize(),
                            content = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(it),
                                ) {
                                    CurrentTab()
                                }
                            },
                            bottomBar = {
                                McsBottomNavigation()
                            },
                        )
                    }
                }
            },
        )
    }
}
