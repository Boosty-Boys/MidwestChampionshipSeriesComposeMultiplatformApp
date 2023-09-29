package com.boostyboys.mcs.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.boostyboys.mcs.ui.McsStrings

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
            .height(56.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        content = {
            // back button if it exists, otherwise use a spacer
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
                Spacer(modifier = Modifier.size(64.dp))
            }

            // toolbar title and subtitle
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                )

                subtitle?.let {
                    Text(
                        text = it,
                        fontSize = 12.sp,
                    )
                }
            }

            // action button if it exists, otherwise use a spacer
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
                Spacer(modifier = Modifier.size(64.dp))
            }
        },
    )
}

data class ActionIconOptions(
    val icon: ImageVector,
    val contentDescription: String? = null,
    val onClick: () -> Unit,
)
