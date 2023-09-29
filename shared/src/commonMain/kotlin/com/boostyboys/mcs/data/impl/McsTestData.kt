package com.boostyboys.mcs.data.impl

import com.boostyboys.mcs.data.api.models.League
import com.boostyboys.mcs.data.api.models.Season

object McsTestData {
    const val SEASON_ONE_ID = "season_1"
    const val SEASON_TWO_ID = "season_2"

    const val PREMIER_ID = "premier"
    const val CHALLENGER_ID = "challenger"
    const val RISING_STAR_ID = "rising_star"

    val seasons = listOf(
        Season(
            id = SEASON_ONE_ID,
            name = "Season 1",
            order = 1,
            leagueIds = listOf(PREMIER_ID, CHALLENGER_ID),
            weeks = 2,
        ),
        Season(
            id = SEASON_TWO_ID,
            name = "Season 2",
            order = 2,
            leagueIds = listOf(PREMIER_ID, CHALLENGER_ID, RISING_STAR_ID),
            weeks = 2,
        ),
    )

    val leagues = listOf(
        League(
            id = PREMIER_ID,
            name = "Premier",
            order = 1,
            seasonIds = listOf(SEASON_ONE_ID, SEASON_TWO_ID),
            teamsIdsBySeason = mapOf(),
        ),
        League(
            id = CHALLENGER_ID,
            name = "Challenger",
            order = 2,
            seasonIds = listOf(SEASON_ONE_ID, SEASON_TWO_ID),
            teamsIdsBySeason = mapOf(),
        ),
        League(
            id = RISING_STAR_ID,
            name = "Rising Star",
            order = 3,
            seasonIds = listOf(SEASON_TWO_ID),
            teamsIdsBySeason = mapOf(),
        ),
    )
}
