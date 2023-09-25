package com.boostyboys.mcs.data.api

import com.boostyboys.mcs.data.api.either.Either
import com.boostyboys.mcs.data.api.models.League
import com.boostyboys.mcs.data.api.models.Match
import com.boostyboys.mcs.data.api.models.Season
import com.boostyboys.mcs.data.api.models.Team

interface McsRepository {

    suspend fun getSeasons(): Either<List<Season>, Exception>

    suspend fun getLeagues(seasonId: String): Either<List<League>, Exception>

    suspend fun getTeams(
        seasonId: String,
        leagueId: String,
    ): Either<List<Team>, Exception>

    suspend fun getMatches(
        seasonId: String,
        leagueId: String,
    ): Either<List<Match>, Exception>
}
