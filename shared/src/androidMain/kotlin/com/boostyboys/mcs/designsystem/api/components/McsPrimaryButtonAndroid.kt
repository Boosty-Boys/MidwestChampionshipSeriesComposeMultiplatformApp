package com.boostyboys.mcs.designsystem.api.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun McsPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
) {
    McsPrimaryButtonAndroidDesktop(
        text = text,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
    )
}
