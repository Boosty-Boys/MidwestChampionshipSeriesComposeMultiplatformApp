package com.boostyboys.mcs.data.impl

import com.boostyboys.mcs.data.api.McsRepository
import com.boostyboys.mcs.data.api.either.Either
import com.boostyboys.mcs.data.api.models.League
import com.boostyboys.mcs.data.api.models.Match
import com.boostyboys.mcs.data.api.models.Season
import com.boostyboys.mcs.data.api.models.Team
import com.boostyboys.mcs.data.impl.McsTestData.CHALLENGER_ID
import com.boostyboys.mcs.data.impl.McsTestData.PREMIER_ID
import com.boostyboys.mcs.data.impl.McsTestData.RISING_STAR_ID
import com.boostyboys.mcs.data.impl.McsTestData.SEASON_ONE_ID
import com.boostyboys.mcs.data.impl.McsTestData.SEASON_TWO_ID

class McsRepositoryImpl : McsRepository {

    override suspend fun getSeasons(): Either<List<Season>, Exception> {
        return Either.success(McsTestData.seasons)
    }

    override suspend fun getLeagues(seasonId: String): Either<List<League>, Exception> {
        return Either.success(
            McsTestData.leagues.filter {
                it.seasonIds.contains(seasonId)
            },
        )
    }

    override suspend fun getTeams(
        seasonId: String,
        leagueId: String,
    ): Either<List<Team>, Exception> {
        return when (seasonId) {
            SEASON_ONE_ID -> {
                when (leagueId) {
                    PREMIER_ID -> Either.success(McsTestTeams.s1PremierTeams)
                    CHALLENGER_ID -> Either.success(McsTestTeams.s1ChallengerTeams)
                    else -> Either.failure(Exception("Unknown leagueId: $seasonId"))
                }
            }
            SEASON_TWO_ID -> {
                when (leagueId) {
                    PREMIER_ID -> Either.success(McsTestTeams.s2PremierTeams)
                    CHALLENGER_ID -> Either.success(McsTestTeams.s2ChallengerTeams)
                    RISING_STAR_ID -> Either.success(McsTestTeams.s2RisingStarTeams)
                    else -> Either.failure(Exception("Unknown leagueId: $seasonId"))
                }
            }
            else -> {
                Either.failure(Exception("Unknown seasonId: $seasonId"))
            }
        }
    }

    override suspend fun getMatches(
        seasonId: String,
        leagueId: String,
    ): Either<List<Match>, Exception> {
        return when (seasonId) {
            SEASON_ONE_ID -> {
                when (leagueId) {
                    PREMIER_ID -> Either.success(
                        McsTestSchedule.season1Week1PremierMatches +
                            McsTestSchedule.season1Week2PremierMatches,
                    )
                    CHALLENGER_ID -> Either.success(
                        McsTestSchedule.season1Week1ChallengerMatches +
                            McsTestSchedule.season1Week2ChallengerMatches,
                    )
                    else -> Either.failure(Exception("Unknown leagueId: $seasonId"))
                }
            }
            SEASON_TWO_ID -> {
                when (leagueId) {
                    PREMIER_ID -> Either.success(
                        McsTestSchedule.season2Week1PremierMatches +
                            McsTestSchedule.season2Week2PremierMatches,
                    )
                    CHALLENGER_ID -> Either.success(
                        McsTestSchedule.season2Week1ChallengerMatches +
                            McsTestSchedule.season2Week2ChallengerMatches,
                    )
                    RISING_STAR_ID -> Either.success(
                        McsTestSchedule.season2Week1RisingStarMatches +
                            McsTestSchedule.season2Week2RisingStarMatches,
                    )
                    else -> Either.failure(Exception("Unknown leagueId: $seasonId"))
                }
            }
            else -> {
                Either.failure(Exception("Unknown seasonId: $seasonId"))
            }
        }
    }
}
