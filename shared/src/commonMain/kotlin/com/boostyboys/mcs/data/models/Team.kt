package com.boostyboys.mcs.data.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.ui.graphics.vector.ImageVector

data class Team(
    val name: String,
    val logo: ImageVector = Icons.Default.AccountCircle,
    val wins: Int,
    val losses: Int,
    val players: List<Player>,
    val league: String = "Rising Star",
    val season: String = "8",
)

val hibbingRangers = Team(
    name = "Hibbing Rangers",
    wins = 8,
    losses = 0,
    players = hibbingRangersPlayers,
)

val rochesterRhythm = Team(
    name = "Rochester Rhythm",
    wins = 7,
    losses = 1,
    players = rochesterRhythmPlayers,
)

val minneapolisMiracles = Team(
    name = "Minneapolis Miracles",
    wins = 6,
    losses = 2,
    players = minneapolisMiraclesPlayers,
)

val bloomingtonMaulers = Team(
    name = "Bloomington Maulers",
    wins = 5,
    losses = 3,
    players = bloomingtonMaulersPlayers,
)

val mankatoAtlas = Team(
    name = "Mankato Atlas",
    wins = 4,
    losses = 4,
    players = mankatoAtlasPlayers,
)

val stCloudFlyers = Team(
    name = "St. Cloud Flyers",
    wins = 3,
    losses = 5,
    players = stCloudFlyersPlayers,
)

val duluthSuperiors = Team(
    name = "Duluth Superiors",
    wins = 2,
    losses = 6,
    players = duluthSuperiorPlayers,
)

val bemidjiLumberjacks = Team(
    name = "Bemidji Lumberjacks",
    wins = 1,
    losses = 7,
    players = bemidjiLumberjacksPlayers,
)

val risingStarSeasonEightTeams = listOf(
    hibbingRangers,
    rochesterRhythm,
    minneapolisMiracles,
    bloomingtonMaulers,
    mankatoAtlas,
    stCloudFlyers,
    duluthSuperiors,
    bemidjiLumberjacks,
)
