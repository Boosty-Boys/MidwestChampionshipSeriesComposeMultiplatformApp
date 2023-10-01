package com.boostyboys.mcs.data.impl

import com.boostyboys.mcs.data.api.McsRepository
import com.boostyboys.mcs.data.api.either.Either
import com.boostyboys.mcs.data.api.models.League
import com.boostyboys.mcs.data.api.models.Match
import com.boostyboys.mcs.data.api.models.Season
import com.boostyboys.mcs.data.api.models.Team
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class FakeMcsRepository(
    configuration: FakeMcsRepositoryOptions.() -> Unit = {},
) : McsRepository {
    var options: FakeMcsRepositoryOptions = FakeMcsRepositoryOptions().apply(configuration)

    override suspend fun getSeasons(): Either<List<Season>, Throwable> {
        return with(options) {
            if (returnDefaultError) {
                defaultError
            } else {
                getSeasonsResponse
            }
        }
    }

    override suspend fun getLeagues(seasonNumber: String): Either<List<League>, Throwable> {
        return with(options) {
            if (returnDefaultError) {
                defaultError
            } else {
                getLeaguesResponse
            }
        }
    }

    override suspend fun getTeams(
        seasonNumber: String,
        leagueId: String
    ): Either<List<Team>, Throwable> {
        return with(options) {
            if (returnDefaultError) {
                defaultError
            } else {
                getTeamsResponse
            }
        }
    }

    override suspend fun getMatches(
        seasonNumber: String,
        leagueId: String
    ): Either<List<Match>, Throwable> {
        return with(options) {
            if (returnDefaultError) {
                defaultError
            } else {
                getMatchesResponse
            }
        }
    }
    
    companion object {
        val defaultError = Either.failure(Throwable("Error loading data"))

        val seasonOne = Season(
            ids = listOf("a", "b"),
            name = "1",
            leagueIds = listOf("premier", "challenger"),
        )

        val seasonTwo = Season(
            ids = listOf("c", "d"),
            name = "2",
            leagueIds = listOf("premier", "challenger", "rising_star"),
        )

        val defaultSeasonsList = listOf(seasonOne, seasonTwo)

        val premierLeague = League(
            id = "premier",
            name = "Premier",
            seasonIds = listOf("1", "2"),
        )

        val challengerLeague = League(
            id = "challenger",
            name = "Challenger",
            seasonIds = listOf("1", "2"),
        )

        val risingStarLeague = League(
            id = "rising_star",
            name = "Rising Star",
            seasonIds = listOf("2"),
        )

        val defaultLeaguesList = listOf(premierLeague, challengerLeague, risingStarLeague)

        val teamOne = Team(
            id = "1",
            name = "Team 1",
            avatar = "",
            wins = 4,
            losses = 1,
            players = listOf(),
        )

        val teamTwo = Team(
            id = "2",
            name = "Team 2",
            avatar = "",
            wins = 2,
            losses = 3,
            players = listOf(),
        )

        val defaultTeamsList = listOf(teamOne, teamTwo)

        val localDateTime = Instant.parse("2023-04-20T14:39:20.866Z").toLocalDateTime(TimeZone.currentSystemDefault())

        val matchOne = Match(
            week = 1,
            teamOne = teamOne,
            teamTwo = teamTwo,
            winningTeamId = null,
            dateTime = localDateTime,
            games = emptyList(),
        )

        val matchTwo = Match(
            week = 2,
            teamOne = teamOne,
            teamTwo = teamTwo,
            winningTeamId = null,
            dateTime = localDateTime,
            games = emptyList(),
        )

        val defaultMatchesList = listOf(matchOne, matchTwo)

        val defaultWeeksList = listOf(1, 2)

        data class FakeMcsRepositoryOptions(
            var returnDefaultError: Boolean = false,
            var getSeasonsResponse: Either<List<Season>, Throwable> = Either.success(
                defaultSeasonsList,
            ),
            var getLeaguesResponse: Either<List<League>, Throwable> = Either.success(
                defaultLeaguesList,
            ),
            var getMatchesResponse: Either<List<Match>, Throwable> = Either.success(
                defaultMatchesList,
            ),
            var getTeamsResponse: Either<List<Team>, Throwable> = Either.success(
                defaultTeamsList,
            ),
        ) {
            fun configureGetSeasonsResponse(configuration: () -> Either<List<Season>, Throwable>) {
                getSeasonsResponse = configuration()
            }

            fun configureGetLeaguesResponse(configuration: () -> Either<List<League>, Throwable>) {
                getLeaguesResponse = configuration()
            }

            fun configureGetMatchesResponse(configuration: () -> Either<List<Match>, Throwable>) {
                getMatchesResponse = configuration()
            }

            fun configureGetTeamsResponse(configuration: () -> Either<List<Team>, Throwable>) {
                getTeamsResponse = configuration()
            }
        }
    }
}
