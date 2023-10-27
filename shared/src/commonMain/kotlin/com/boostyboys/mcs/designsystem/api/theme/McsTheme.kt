package com.boostyboys.mcs.designsystem.api.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

object McsTheme {
    /**
     * Retrieves the current [McsColors] at the call site's position in the hierarchy.
     */
    val colors: McsColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    /**
     * Retrieves the current [McsTypography] at the call site's position in the hierarchy.
     */
    val typography: McsTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

@Composable
fun McsTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val rememberedColors = remember {
        if (useDarkTheme) {
            DarkColors
        } else {
            LightColors
        }
    }
    val rememberedTypography = remember { Typography }
    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalTypography provides rememberedTypography,
        content = content,
    )
}
