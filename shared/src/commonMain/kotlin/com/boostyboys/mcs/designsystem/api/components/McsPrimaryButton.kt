package com.boostyboys.mcs.designsystem.api.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.boostyboys.mcs.designsystem.api.theme.McsTheme

@Composable expect fun McsPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
)

@Composable
fun McsPrimaryButtonAndroidDesktop(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Button(
        modifier = modifier.clickable(
            interactionSource = interactionSource,
            indication = rememberRipple(),
            role = Role.Button,
            onClick = onClick,
        ),
        onClick = {},
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = McsTheme.colors.primary,
            disabledBackgroundColor = McsTheme.colors.surface.copy(alpha = 0.5f),
            contentColor = McsTheme.colors.onPrimary,
            disabledContentColor = McsTheme.colors.onSurface.copy(alpha = 0.5f),
        ),
        shape = RoundedCornerShape(12.dp),
        border = null,
        interactionSource = interactionSource,
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 12.dp,
        ),
        content = {
            ButtonText(
                text = text,
                color = if (enabled) {
                    McsTheme.colors.onPrimary
                } else {
                    McsTheme.colors.onSurface.copy(alpha = 0.5f)
                },
            )
        },
    )
}
