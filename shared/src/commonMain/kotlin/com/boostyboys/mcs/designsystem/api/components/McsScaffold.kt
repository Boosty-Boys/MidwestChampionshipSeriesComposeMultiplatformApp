package com.boostyboys.mcs.designsystem.api.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.boostyboys.mcs.designsystem.api.theme.McsTheme
import com.boostyboys.mcs.designsystem.api.theme.contentColorFor

@Composable
fun McsScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    backgroundColor: Color = McsTheme.colors.background,
    contentColor: Color = contentColorFor(backgroundColor),
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        content = content,
    )
}
