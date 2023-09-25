package com.boostyboys.mcs.data.impl

import com.boostyboys.mcs.data.api.models.Match
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.Month

object McsTestSchedule {
    val season1Week1PremierMatches = listOf(
        Match(
            week = 1,
            teamOne = McsTestTeams.s1PremierHibbing,
            teamTwo = McsTestTeams.s1PremierRochester,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 18,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s1PremierHibbing.id,
        ),
        Match(
            week = 1,
            teamOne = McsTestTeams.s1PremierMinneapolis,
            teamTwo = McsTestTeams.s1PremierBloomington,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 19,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s1PremierMinneapolis.id,
        ),
        Match(
            week = 1,
            teamOne = McsTestTeams.s1PremierMankato,
            teamTwo = McsTestTeams.s1PremierStCloud,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 20,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s1PremierStCloud.id,
        ),
        Match(
            week = 1,
            teamOne = McsTestTeams.s1PremierDuluth,
            teamTwo = McsTestTeams.s1PremierBemidji,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 20,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s1PremierDuluth.id,
        ),
    )

    val season1Week2PremierMatches = listOf(
        Match(
            week = 2,
            teamOne = McsTestTeams.s1PremierMinneapolis,
            teamTwo = McsTestTeams.s1PremierRochester,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 18,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s1PremierRochester.id,
        ),
        Match(
            week = 2,
            teamOne = McsTestTeams.s1PremierHibbing,
            teamTwo = McsTestTeams.s1PremierBloomington,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 19,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s1PremierHibbing.id,
        ),
        Match(
            week = 2,
            teamOne = McsTestTeams.s1PremierMankato,
            teamTwo = McsTestTeams.s1PremierDuluth,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 20,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s1PremierDuluth.id,
        ),
        Match(
            week = 2,
            teamOne = McsTestTeams.s1PremierStCloud,
            teamTwo = McsTestTeams.s1PremierBemidji,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 20,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s1PremierBemidji.id,
        ),
    )

    val season2Week1PremierMatches = listOf(
        Match(
            week = 1,
            teamOne = McsTestTeams.s1PremierHibbing,
            teamTwo = McsTestTeams.s1PremierRochester,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 18,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s1PremierHibbing.id,
        ),
        Match(
            week = 1,
            teamOne = McsTestTeams.s1PremierMinneapolis,
            teamTwo = McsTestTeams.s1PremierBloomington,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 19,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s1PremierMinneapolis.id,
        ),
        Match(
            week = 1,
            teamOne = McsTestTeams.s1PremierMankato,
            teamTwo = McsTestTeams.s1PremierStCloud,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 20,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s1PremierStCloud.id,
        ),
        Match(
            week = 1,
            teamOne = McsTestTeams.s1PremierDuluth,
            teamTwo = McsTestTeams.s1PremierBemidji,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 20,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s1PremierDuluth.id,
        ),
    )

    val season2Week2PremierMatches = listOf(
        Match(
            week = 2,
            teamOne = McsTestTeams.s2PremierMinneapolis,
            teamTwo = McsTestTeams.s2PremierRochester,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 18,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s2PremierRochester.id,
        ),
        Match(
            week = 2,
            teamOne = McsTestTeams.s2PremierHibbing,
            teamTwo = McsTestTeams.s2PremierBloomington,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 19,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s2PremierHibbing.id,
        ),
        Match(
            week = 2,
            teamOne = McsTestTeams.s2PremierMankato,
            teamTwo = McsTestTeams.s2PremierDuluth,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 20,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s2PremierDuluth.id,
        ),
        Match(
            week = 2,
            teamOne = McsTestTeams.s2PremierStCloud,
            teamTwo = McsTestTeams.s2PremierBemidji,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 20,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s2PremierBemidji.id,
        ),
    )

    val season1Week1ChallengerMatches = listOf(
        Match(
            week = 1,
            teamOne = McsTestTeams.s1ChallengerSavage,
            teamTwo = McsTestTeams.s1ChallengerWhiteBear,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 18,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s1ChallengerSavage.id,
        ),
        Match(
            week = 1,
            teamOne = McsTestTeams.s1ChallengerBlackCats,
            teamTwo = McsTestTeams.s1ChallengerBlueSharks,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 19,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s1ChallengerBlackCats.id,
        ),
        Match(
            week = 1,
            teamOne = McsTestTeams.s1ChallengerGoldenEagles,
            teamTwo = McsTestTeams.s1ChallengerGreenCobras,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 20,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s1ChallengerGoldenEagles.id,
        ),
        Match(
            week = 1,
            teamOne = McsTestTeams.s1ChallengerSilverWolves,
            teamTwo = McsTestTeams.s1ChallengerRedFoxes,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 20,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s1ChallengerRedFoxes.id,
        ),
    )

    val season1Week2ChallengerMatches = listOf(
        Match(
            week = 2,
            teamOne = McsTestTeams.s1ChallengerGreenCobras,
            teamTwo = McsTestTeams.s1ChallengerSavage,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 18,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s1ChallengerSavage.id,
        ),
        Match(
            week = 2,
            teamOne = McsTestTeams.s1ChallengerBlueSharks,
            teamTwo = McsTestTeams.s1ChallengerSilverWolves,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 19,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s1ChallengerSilverWolves.id,
        ),
        Match(
            week = 2,
            teamOne = McsTestTeams.s1ChallengerRedFoxes,
            teamTwo = McsTestTeams.s1ChallengerGoldenEagles,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 20,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s1ChallengerGoldenEagles.id,
        ),
        Match(
            week = 2,
            teamOne = McsTestTeams.s1ChallengerWhiteBear,
            teamTwo = McsTestTeams.s1ChallengerBlueSharks,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 20,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s1ChallengerBlueSharks.id,
        ),
    )

    val season2Week1ChallengerMatches = listOf(
        Match(
            week = 1,
            teamOne = McsTestTeams.s2ChallengerHibbing,
            teamTwo = McsTestTeams.s2ChallengerRochester,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 18,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s2ChallengerHibbing.id,
        ),
        Match(
            week = 1,
            teamOne = McsTestTeams.s2ChallengerMinneapolis,
            teamTwo = McsTestTeams.s2ChallengerBloomington,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 19,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s2ChallengerMinneapolis.id,
        ),
        Match(
            week = 1,
            teamOne = McsTestTeams.s2ChallengerMankato,
            teamTwo = McsTestTeams.s2ChallengerStCloud,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 20,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s2ChallengerStCloud.id,
        ),
        Match(
            week = 1,
            teamOne = McsTestTeams.s2ChallengerDuluth,
            teamTwo = McsTestTeams.s2ChallengerBemidji,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 20,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s2ChallengerDuluth.id,
        ),
    )

    val season2Week2ChallengerMatches = listOf(
        Match(
            week = 1,
            teamOne = McsTestTeams.s2ChallengerMinneapolis,
            teamTwo = McsTestTeams.s2ChallengerRochester,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 18,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s2ChallengerRochester.id,
        ),
        Match(
            week = 1,
            teamOne = McsTestTeams.s2ChallengerHibbing,
            teamTwo = McsTestTeams.s2ChallengerBloomington,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 19,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s2ChallengerHibbing.id,
        ),
        Match(
            week = 1,
            teamOne = McsTestTeams.s2ChallengerMankato,
            teamTwo = McsTestTeams.s2ChallengerDuluth,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 20,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s2ChallengerDuluth.id,
        ),
        Match(
            week = 1,
            teamOne = McsTestTeams.s2ChallengerStCloud,
            teamTwo = McsTestTeams.s2ChallengerBemidji,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 20,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s2ChallengerBemidji.id,
        ),
    )

    val season2Week1RisingStarMatches = listOf(
        Match(
            week = 1,
            teamOne = McsTestTeams.s2RisingStarHibbing,
            teamTwo = McsTestTeams.s2RisingStarRochester,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 18,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s2RisingStarHibbing.id,
        ),
        Match(
            week = 1,
            teamOne = McsTestTeams.s2RisingStarMinneapolis,
            teamTwo = McsTestTeams.s2RisingStarBloomington,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 19,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s2RisingStarMinneapolis.id,
        ),
        Match(
            week = 1,
            teamOne = McsTestTeams.s2RisingStarMankato,
            teamTwo = McsTestTeams.s2RisingStarStCloud,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 20,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s2RisingStarStCloud.id,
        ),
        Match(
            week = 1,
            teamOne = McsTestTeams.s2RisingStarDuluth,
            teamTwo = McsTestTeams.s2RisingStarBemidji,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 20,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s2RisingStarDuluth.id,
        ),
    )

    val season2Week2RisingStarMatches = listOf(
        Match(
            week = 1,
            teamOne = McsTestTeams.s2RisingStarMinneapolis,
            teamTwo = McsTestTeams.s2RisingStarRochester,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 18,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s2RisingStarRochester.id,
        ),
        Match(
            week = 1,
            teamOne = McsTestTeams.s2RisingStarHibbing,
            teamTwo = McsTestTeams.s2RisingStarBloomington,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 19,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s2RisingStarHibbing.id,
        ),
        Match(
            week = 1,
            teamOne = McsTestTeams.s2RisingStarMankato,
            teamTwo = McsTestTeams.s2RisingStarDuluth,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 20,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s2RisingStarDuluth.id,
        ),
        Match(
            week = 1,
            teamOne = McsTestTeams.s2RisingStarStCloud,
            teamTwo = McsTestTeams.s2RisingStarBemidji,
            dateTime = LocalDateTime(
                date = LocalDate(
                    year = 2023,
                    month = Month.OCTOBER,
                    dayOfMonth = 22,
                ),
                time = LocalTime(
                    hour = 20,
                    minute = 0,
                ),
            ),
            games = emptyList(),
            winningTeamId = McsTestTeams.s2RisingStarBemidji.id,
        ),
    )
}
