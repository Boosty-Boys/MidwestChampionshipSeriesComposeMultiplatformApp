package com.boostyboys.mcs.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.boostyboys.mcs.ui.McsStrings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun McsToolbar(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    onBackClicked: (() -> Unit)? = null,
    actionIconOptions: ActionIconOptions? = null,
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
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
                        tint = MaterialTheme.colorScheme.onSurface,
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
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(64.dp))
            }
        },
        title = {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .height(56.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                subtitle?.let {
                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
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
