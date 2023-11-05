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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.boostyboys.mcs.data.api.models.match.Match
import com.boostyboys.mcs.data.api.models.match.MatchStatus
import com.boostyboys.mcs.data.api.models.team.TeamWithResults
import com.boostyboys.mcs.designsystem.api.components.BodyText
import com.boostyboys.mcs.designsystem.api.components.CaptionText
import com.boostyboys.mcs.designsystem.api.components.McsCard
import com.boostyboys.mcs.designsystem.api.components.SubtitleText
import com.boostyboys.mcs.designsystem.api.theme.McsTheme
import com.boostyboys.mcs.ui.teams.TeamLogo
import com.boostyboys.mcs.util.format

private const val TEAM_COLUMN_WEIGHT = 6f
private const val INFO_COLUMN_WEIGHT = 1.5f

@Composable
fun MatchCell(
    modifier: Modifier = Modifier,
    match: Match,
    teamOne: TeamWithResults?,
    teamTwo: TeamWithResults?,
    onClick: (Match) -> Unit,
) {
    McsCard(
        modifier = modifier
            .fillMaxWidth()
            .height(88.dp)
            .clickable {
                onClick(match)
            },
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(TEAM_COLUMN_WEIGHT),
            ) {
                teamOne?.let { TeamRow(it, match) }
                Spacer(modifier = Modifier.height(2.dp))
                teamTwo?.let { TeamRow(it, match) }
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
                        .background(
                            color = McsTheme.colors.onSurface,
                            shape = RoundedCornerShape(percent = 50),
                        ),
                )
            }

            Column(
                modifier = Modifier.weight(INFO_COLUMN_WEIGHT),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                val timeOrFinalText = if (match.status == MatchStatus.CLOSED) {
                    "Final"
                } else {
                    match.scheduledDateTime?.format("h:mm a") ?: "TBD"
                }

                CaptionText(
                    text = timeOrFinalText,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Composable
private fun TeamRow(
    team: TeamWithResults,
    match: Match,
) {
    val textColor = when {
        match.status == MatchStatus.CLOSED -> {
            if (team.id == match.winningTeamId) {
                McsTheme.colors.onSurface
            } else {
                McsTheme.colors.onSurface.copy(alpha = 0.5f)
            }
        }
        else -> McsTheme.colors.onSurface
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        TeamLogo(
            modifier = Modifier.size(32.dp),
            logoUrl = team.avatar,
        )

        Spacer(modifier = Modifier.width(16.dp))

        BodyText(
            modifier = Modifier.weight(1f),
            text = team.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = textColor,
        )

        Spacer(modifier = Modifier.width(4.dp))

        val gameWinsOrRecordText = if (match.status == MatchStatus.CLOSED) {
            match.games.count {
                it.winningTeamId == team.id
            }.toString()
        } else {
            "${team.matchesWon}-${team.matchesPlayed - team.matchesWon}"
        }

        // W/L record if the match is not closed, or the game wins if it is
        SubtitleText(
            text = gameWinsOrRecordText,
            maxLines = 1,
            color = textColor,
        )

        Spacer(modifier = Modifier.width(6.dp))
    }
}
