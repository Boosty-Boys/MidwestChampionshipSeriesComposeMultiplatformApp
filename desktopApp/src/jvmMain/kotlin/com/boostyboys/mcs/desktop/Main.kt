package com.boostyboys.mcs.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.boostyboys.mcs.Main
import com.boostyboys.mcs.designsystem.theme.AppTheme
import com.boostyboys.mcs.di.initKoin
import java.awt.Dimension

private const val WINDOW_MIN_WIDTH = 700
private const val WINDOW_MIN_HEIGHT = 1000

fun main() = application {
    initKoin()

    AppTheme {
        Window(
            onCloseRequest = {
                exitApplication()
            },
        ) {
            window.minimumSize = Dimension(
                WINDOW_MIN_WIDTH,
                WINDOW_MIN_HEIGHT,
            )
            Main()
        }
    }
}
