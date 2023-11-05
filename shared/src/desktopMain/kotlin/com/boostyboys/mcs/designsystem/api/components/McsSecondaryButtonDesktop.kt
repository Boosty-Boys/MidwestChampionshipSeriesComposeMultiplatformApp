package com.boostyboys.mcs.designsystem.api.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun McsSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
) {
    McsSecondaryButtonAndroidDesktop(
        text = text,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
    )
}
