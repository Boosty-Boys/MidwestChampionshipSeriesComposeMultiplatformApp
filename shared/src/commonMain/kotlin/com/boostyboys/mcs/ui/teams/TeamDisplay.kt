package com.boostyboys.mcs.ui.teams

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.boostyboys.mcs.data.models.Team

@Composable
fun TeamDisplay(
    team: Team,
) {
    val navigator = LocalNavigator.currentOrThrow
    val logo = rememberVectorPainter(team.logo)

    Column(
        modifier = Modifier.clickable {
            navigator.push(TeamDetailsScreen(team))
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            modifier = Modifier.size(32.dp),
            painter = logo,
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = team.name)
    }
}
