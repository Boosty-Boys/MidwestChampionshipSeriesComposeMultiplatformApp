package com.boostyboys.mcs.designsystem.api.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
actual fun McsCircularLoader(
    modifier: Modifier,
    color: Color,
) {
    McsCircularLoaderAndroidDesktop(
        modifier = modifier,
        color = color,
    )
}
