package com.boostyboys.mcs.data.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.ui.graphics.vector.ImageVector

data class Team(
    val name: String,
    val logo: ImageVector = Icons.Default.AccountCircle,
)
