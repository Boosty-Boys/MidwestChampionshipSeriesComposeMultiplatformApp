package com.boostyboys.mcs.designsystem.api.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.boostyboys.mcs.designsystem.api.theme.McsTheme

@Composable
actual fun McsPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Box(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                role = Role.Button,
                onClick = onClick,
                enabled = enabled,
            )
            .background(
                color = if (enabled) {
                    if (isPressed) {
                        McsTheme.colors.primary.darken(0.1f)
                    } else {
                        McsTheme.colors.primary
                    }
                } else {
                    McsTheme.colors.surface.copy(alpha = 0.5f)
                },
                shape = RoundedCornerShape(12.dp),
            )
            .defaultMinSize(
                minWidth = 64.dp,
                minHeight = 36.dp,
            ),
        contentAlignment = Alignment.Center,
    ) {
        ButtonText(
            text = text,
            color = if (enabled) {
                McsTheme.colors.onPrimary
            } else {
                McsTheme.colors.onSurface.copy(alpha = 0.5f)
            },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        )
    }
}
