package com.boostyboys.mcs.ui.schedule

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.boostyboys.mcs.data.models.Team

@Composable
fun ScheduleCell(
    modifier: Modifier = Modifier,
    homeTeam: Team,
    awayTeam: Team,
    onClick: (Pair<Team, Team>) -> Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick(Pair(homeTeam, awayTeam))
            },
        color = Color.LightGray,
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                TeamRow(homeTeam)

                Spacer(modifier = Modifier.height(8.dp))

                TeamRow(awayTeam)
            }
        }
    }
}

@Composable
private fun TeamRow(
    team: Team,
) {
    val logo = rememberVectorPainter(team.logo)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Icon(
            modifier = Modifier.size(32.dp),
            painter = logo,
            contentDescription = null,
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(text = team.name)
    }
}
