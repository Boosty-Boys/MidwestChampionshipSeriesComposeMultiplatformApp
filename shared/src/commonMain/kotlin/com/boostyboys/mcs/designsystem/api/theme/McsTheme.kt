package com.boostyboys.mcs.designsystem.api.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

object McsTheme {
    fun getBackgroundColorHex(): String {
        return LightColors.background.toHex()
    }

    private fun Color.toHex(): String {
        val alpha = (this.alpha * 255).toInt()
        val red = (this.red * 255).toInt()
        val green = (this.green * 255).toInt()
        val blue = (this.blue * 255).toInt()

        return "#" +
            alpha.toString(16).padStart(2, '0').toUpperCase() +
            red.toString(16).padStart(2, '0').toUpperCase() +
            green.toString(16).padStart(2, '0').toUpperCase() +
            blue.toString(16).padStart(2, '0').toUpperCase()
    }

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
