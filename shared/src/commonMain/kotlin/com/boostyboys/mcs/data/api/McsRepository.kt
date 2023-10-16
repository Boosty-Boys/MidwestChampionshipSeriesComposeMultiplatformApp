package com.boostyboys.mcs.data.api

import com.boostyboys.mcs.data.api.either.Either
import com.boostyboys.mcs.data.api.models.league.LeagueWithSeasons
import com.boostyboys.mcs.data.api.models.player.Player
import com.boostyboys.mcs.data.api.models.season.SeasonDataResponse

interface McsRepository {

    suspend fun getLeagues(): Either<List<LeagueWithSeasons>, Throwable>

    suspend fun getSeasonData(
        seasonId: String,
        teamIds: List<String>,
    ): Either<SeasonDataResponse, Throwable>

    suspend fun getPlayers(
        teamId: String,
        date: String,
    ): Either<List<Player>, Throwable>
}
