package com.boostyboys.mcs.data.api.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.ui.graphics.vector.ImageVector

data class Team(
    val id: String,
    val name: String,
    val logo: ImageVector = Icons.Default.AccountCircle,
    val wins: Int,
    val losses: Int,
    val players: List<Player>,
    val leagueId: String,
    val seasonId: String,
)
