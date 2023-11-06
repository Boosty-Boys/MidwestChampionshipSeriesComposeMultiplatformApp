package com.boostyboys.mcs.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun Color.darken(factor: Float): Color {
    return Color(
        red = red * (1.0f - factor),
        green = green * (1.0f - factor),
        blue = blue * (1.0f - factor),
        alpha = alpha,
    )
}
