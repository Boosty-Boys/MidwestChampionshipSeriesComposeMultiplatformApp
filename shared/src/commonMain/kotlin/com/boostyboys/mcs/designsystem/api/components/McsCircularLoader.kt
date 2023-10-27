package com.boostyboys.mcs.designsystem.api.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.boostyboys.mcs.designsystem.api.theme.McsTheme

// @Composable expect fun McsCircularLoader() TODO
@Composable
fun McsCircularLoader(
    modifier: Modifier = Modifier,
    color: Color = McsTheme.colors.primary,
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = color,
    )
}
