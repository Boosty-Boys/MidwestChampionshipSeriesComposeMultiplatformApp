package com.boostyboys.mcs.designsystem.api.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.boostyboys.mcs.designsystem.api.theme.McsTheme
import com.boostyboys.mcs.designsystem.api.theme.contentColorFor
import com.boostyboys.mcs.ui.McsStrings

// @Composable expect fun McsToolbar() TODO
@Composable
fun McsToolbar(
    title: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = McsTheme.colors.background,
    subtitle: String? = null,
    onBackClicked: (() -> Unit)? = null,
    actionIconOptions: ActionIconOptions? = null,
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
        backgroundColor = backgroundColor,
        contentColor = contentColorFor(backgroundColor),
        navigationIcon = {
            if (onBackClicked != null) {
                val iconBack = rememberVectorPainter(Icons.Default.ArrowBack)
                IconButton(
                    modifier = Modifier.size(64.dp),
                    onClick = {
                        onBackClicked()
                    },
                ) {
                    Icon(
                        painter = iconBack,
                        contentDescription = McsStrings.GO_BACK,
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(64.dp))
            }
        },
        actions = {
            if (actionIconOptions != null) {
                val iconAction = rememberVectorPainter(actionIconOptions.icon)
                IconButton(
                    modifier = Modifier.size(64.dp),
                    onClick = actionIconOptions.onClick,
                ) {
                    Icon(
                        painter = iconAction,
                        contentDescription = actionIconOptions.contentDescription,
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(64.dp))
            }
        },
        title = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Spacer(modifier = Modifier.height(4.dp))
                TitleText(text = title)

                subtitle?.let {
                    Spacer(modifier = Modifier.height(2.dp))
                    SubtitleText(text = it)
                }
            }
        },
    )
}

data class ActionIconOptions(
    val icon: ImageVector,
    val contentDescription: String? = null,
    val onClick: () -> Unit,
)
