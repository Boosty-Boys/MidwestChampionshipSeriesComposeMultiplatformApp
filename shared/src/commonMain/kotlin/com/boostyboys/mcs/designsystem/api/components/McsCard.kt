package com.boostyboys.mcs.designsystem.api.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.boostyboys.mcs.designsystem.api.theme.McsTheme
import com.boostyboys.mcs.designsystem.api.theme.contentColorFor

@Composable
expect fun McsCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    backgroundColor: Color = McsTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    border: BorderStroke? = null,
    elevation: Dp? = null,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit,
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun McsCardAndroidDesktop(
    modifier: Modifier,
    shape: Shape,
    backgroundColor: Color,
    contentColor: Color,
    border: BorderStroke?,
    elevation: Dp?,
    onClick: (() -> Unit)?,
    content: @Composable () -> Unit,
) {
    if (onClick != null) {
        Card(
            modifier = modifier,
            shape = shape,
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            border = border,
            elevation = elevation ?: 1.dp,
            onClick = onClick,
            content = content,
        )
    } else {
        Card(
            modifier = modifier,
            shape = shape,
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            border = border,
            elevation = elevation ?: 1.dp,
            content = content,
        )
    }
}
