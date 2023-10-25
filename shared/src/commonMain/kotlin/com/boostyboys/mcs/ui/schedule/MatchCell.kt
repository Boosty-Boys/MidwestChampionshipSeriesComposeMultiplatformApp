package com.boostyboys.mcs.ui.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.boostyboys.mcs.data.api.models.match.Match
import com.boostyboys.mcs.data.api.models.team.TeamWithResults
import com.boostyboys.mcs.designsystem.components.TeamLogo
import com.boostyboys.mcs.util.format

private const val TEAM_COLUMN_WEIGHT = 6f

@Composable
fun MatchCell(
    modifier: Modifier = Modifier,
    match: Match,
    teamOne: TeamWithResults?,
    teamTwo: TeamWithResults?,
    onClick: (Match) -> Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(88.dp)
            .clickable {
                onClick(match)
            },
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.large,
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(TEAM_COLUMN_WEIGHT),
            ) {
                teamOne?.let { team ->
                    TeamRow(team)
                }

                Spacer(modifier = Modifier.height(8.dp))

                teamTwo?.let { team ->
                    TeamRow(team)
                }
            }

            // divider
            Box(
                modifier = Modifier.padding(horizontal = 4.dp),
                contentAlignment = Alignment.Center,
            ) {
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .fillMaxHeight()
                        .padding(vertical = 4.dp)
                        .background(color = MaterialTheme.colorScheme.onSurfaceVariant),
                )
            }

            Column(
                modifier = Modifier.weight(2f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                val matchDayTime = match.scheduledDateTime

                val time = matchDayTime?.format("h:mm a")

                Text(
                    text = time ?: "",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun TeamRow(
    team: TeamWithResults,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        TeamLogo(
            modifier = Modifier.size(32.dp),
            logoUrl = team.avatar,
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            modifier = Modifier.weight(1f),
            text = team.name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Text(
            text = "${team.matchesWon}-${team.matchesPlayed - team.matchesWon}",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(modifier = Modifier.width(4.dp))
    }
}
