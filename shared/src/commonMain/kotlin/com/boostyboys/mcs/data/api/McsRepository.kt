package com.boostyboys.mcs.data.api

import com.boostyboys.mcs.data.api.either.Either
import com.boostyboys.mcs.data.api.models.League
import com.boostyboys.mcs.data.api.models.Match
import com.boostyboys.mcs.data.api.models.Season
import com.boostyboys.mcs.data.api.models.Team

interface McsRepository {

    suspend fun getSeasons(): Either<List<Season>, Throwable>

    suspend fun getLeagues(seasonId: String): Either<List<League>, Throwable>

    suspend fun getTeams(
        seasonId: String,
        leagueId: String,
    ): Either<List<Team>, Throwable>

    suspend fun getMatches(
        seasonId: String,
        leagueId: String,
    ): Either<List<Match>, Throwable>
}
