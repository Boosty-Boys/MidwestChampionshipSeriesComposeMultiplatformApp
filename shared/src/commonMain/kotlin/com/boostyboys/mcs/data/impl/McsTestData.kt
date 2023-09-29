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
            leagueIds = listOf(PREMIER_ID, CHALLENGER_ID),
        ),
        Season(
            id = SEASON_TWO_ID,
            name = "Season 2",
            leagueIds = listOf(PREMIER_ID, CHALLENGER_ID, RISING_STAR_ID),
        ),
    )

    val leagues = listOf(
        League(
            id = PREMIER_ID,
            name = "Premier",
            seasonIds = listOf(SEASON_ONE_ID, SEASON_TWO_ID),
            teamsIdsBySeason = mapOf(),
        ),
        League(
            id = CHALLENGER_ID,
            name = "Challenger",
            seasonIds = listOf(SEASON_ONE_ID, SEASON_TWO_ID),
            teamsIdsBySeason = mapOf(),
        ),
        League(
            id = RISING_STAR_ID,
            name = "Rising Star",
            seasonIds = listOf(SEASON_TWO_ID),
            teamsIdsBySeason = mapOf(),
        ),
    )
}
