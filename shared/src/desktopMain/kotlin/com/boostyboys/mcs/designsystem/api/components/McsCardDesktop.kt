package com.boostyboys.mcs.designsystem.api.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

@Composable
actual fun McsCard(
    modifier: Modifier,
    shape: Shape,
    backgroundColor: Color,
    contentColor: Color,
    border: BorderStroke?,
    elevation: Dp?,
    onClick: (() -> Unit)?,
    content: @Composable () -> Unit,
) {
    McsCardAndroidDesktop(
        modifier = modifier,
        shape = shape,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        border = border,
        elevation = elevation,
        onClick = onClick,
        content = content,
    )
}
