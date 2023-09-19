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
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp

@Composable
fun McsToolbar(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    onBackClicked: (() -> Unit)? = null,
    // assumed to be 64.dp, TODO figure out how to enforce, or if we want multiple action buttons
    actionIcon: @Composable (() -> Unit)? = null,
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        contentPadding = PaddingValues(16.dp),
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
                        contentDescription = "Go Back",
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
                Text(text = title)

                subtitle?.let {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = it)
                }
            }

            // action button if it exists, otherwise use a spacer
            if (actionIcon != null) {
                actionIcon()
            } else {
                Spacer(modifier = Modifier.size(64.dp))
            }
        },
    )
}
