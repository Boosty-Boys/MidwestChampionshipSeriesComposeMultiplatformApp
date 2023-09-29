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
import com.boostyboys.mcs.networking.api.McsManager

class McsRepositoryImpl(
    private val mcsManager: McsManager,
) : McsRepository {

    override suspend fun getSeasons(): Either<List<Season>, Throwable> {
        return when (val leagueSeasonsResponse = mcsManager.getSeasons()) {
            is Either.Success -> {
                val leagueSeasons = leagueSeasonsResponse.value

                return Either.success(
                    leagueSeasons.groupBy { it.name }
                        .map { (name, group) ->
                            Season(
                                ids = group.map { it.id },
                                name = name,
                                leagueIds = group.map { it.league.id },
                            )
                        },
                )
            }
            is Either.Failure -> {
                leagueSeasonsResponse
            }
        }
    }

    override suspend fun getLeagues(seasonId: String): Either<List<League>, Throwable> {
        return Either.success(
            McsTestData.leagues,
        )
    }

    override suspend fun getTeams(
        seasonId: String,
        leagueId: String,
    ): Either<List<Team>, Throwable> {
        return when (leagueId) {
            PREMIER_ID -> Either.success(McsTestTeams.s2PremierTeams)
            CHALLENGER_ID -> Either.success(McsTestTeams.s2ChallengerTeams)
            RISING_STAR_ID -> Either.success(McsTestTeams.s2RisingStarTeams)
            else -> Either.failure(Exception("Unknown leagueId: $seasonId"))
        }
    }

    override suspend fun getMatches(
        seasonId: String,
        leagueId: String,
    ): Either<List<Match>, Throwable> {
        return when (leagueId) {
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
}
