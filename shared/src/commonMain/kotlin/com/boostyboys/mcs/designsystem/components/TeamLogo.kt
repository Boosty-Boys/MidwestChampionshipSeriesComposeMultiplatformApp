package com.boostyboys.mcs.designsystem.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun TeamLogo(
    modifier: Modifier = Modifier,
    logoUrl: String,
    forceFallback: Boolean = false,
) {
    if (forceFallback) {
        FallbackIcon(modifier = modifier)
    } else {
        KamelImage(
            modifier = modifier,
            resource = asyncPainterResource(data = logoUrl),
            contentDescription = null,
            onLoading = {
                FallbackIcon()
            },
            onFailure = {
                FallbackIcon()
            },
        )
    }
}

@Composable
private fun FallbackIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = rememberVectorPainter(Icons.Default.Person),
        contentDescription = null,
    )
}
