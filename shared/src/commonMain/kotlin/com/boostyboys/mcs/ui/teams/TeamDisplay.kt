package com.boostyboys.mcs.ui.teams

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.boostyboys.mcs.data.api.models.team.TeamWithResults
import com.boostyboys.mcs.designsystem.api.components.H1Text

@Composable
fun TeamDisplay(
    team: TeamWithResults,
    modifier: Modifier = Modifier,
    canNavigateToDetails: Boolean = false,
) {
    val navigator = LocalNavigator.currentOrThrow
    val localModifier = if (canNavigateToDetails) {
        modifier.clickable {
            navigator.push(TeamDetailsScreen(team))
        }
    } else {
        modifier
    }

    Column(
        modifier = localModifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        TeamLogo(
            modifier = Modifier.size(96.dp),
            logoUrl = team.avatar,
        )

        Spacer(modifier = Modifier.height(4.dp))

        H1Text(
            text = team.name,
            textAlign = TextAlign.Center,
        )
    }
}
