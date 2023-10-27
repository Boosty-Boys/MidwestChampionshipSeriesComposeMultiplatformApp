package com.boostyboys.mcs.designsystem.api.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun McsFullScreenError(
    errorTitle: String = "error :(",
    errorMessage: String? = null,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        H1Text(text = errorTitle)
        errorMessage?.let {
            BodyText(text = it)
        }
    }
}
