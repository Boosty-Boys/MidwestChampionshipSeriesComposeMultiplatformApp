package com.boostyboys.mcs.ui.teams

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen

class TeamsScreen : Screen {

    @Composable
    override fun Content() {
        LifecycleEffect(
            onStarted = {
                // todo?
            },
            onDisposed = {
                // todo?
            }
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text("Teams")
        }
    }
}