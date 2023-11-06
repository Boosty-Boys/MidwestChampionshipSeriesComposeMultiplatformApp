package com.boostyboys.mcs.designsystem.api.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.boostyboys.mcs.util.darken

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
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val clickableModifier = if (onClick != null) {
        modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            role = Role.Button,
            onClick = onClick,
        )
    } else {
        modifier
    }

    Card(
        modifier = clickableModifier,
        shape = shape,
        backgroundColor = if (onClick != null && isPressed) {
            backgroundColor.darken(0.1f)
        } else {
            backgroundColor
        },
        contentColor = contentColor,
        border = border,
        elevation = elevation ?: 0.dp,
        content = content,
    )
}
