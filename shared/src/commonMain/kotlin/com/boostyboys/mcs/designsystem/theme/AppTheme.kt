package com.boostyboys.mcs.designsystem.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val appColors = if (useDarkTheme) {
        DarkColors
    } else {
        LightColors
    }

    CompositionLocalProvider(
        LocalIndication provides rememberRipple(),
    ) {
        MaterialTheme(
            colorScheme = appColors,
            shapes = AppShapes,
            typography = AppTypography,
            content = content,
        )
    }
}
