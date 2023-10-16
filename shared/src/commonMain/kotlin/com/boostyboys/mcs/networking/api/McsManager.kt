package com.boostyboys.mcs.networking.api

import com.boostyboys.mcs.data.api.either.Either
import com.boostyboys.mcs.data.api.models.league.LeagueWithSeasons
import com.boostyboys.mcs.data.api.models.player.Player
import com.boostyboys.mcs.data.api.models.season.SeasonDataRequest
import com.boostyboys.mcs.data.api.models.season.SeasonDataResponse

interface McsManager {

    suspend fun getLeagues(): Either<List<LeagueWithSeasons>, Throwable>

    suspend fun getSeasonData(request: SeasonDataRequest): Either<SeasonDataResponse, Throwable>

    suspend fun getPlayers(
        teamId: String,
        date: String,
    ): Either<List<Player>, Throwable>

    companion object {
        const val BASE_URL = "https://mcs-app-backend-c57cd7cec173.herokuapp.com"
    }
}
