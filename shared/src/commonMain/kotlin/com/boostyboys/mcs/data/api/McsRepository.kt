package com.boostyboys.mcs.data.api

import com.boostyboys.mcs.data.api.either.Either
import com.boostyboys.mcs.data.api.models.LeagueSeasonConfig
import com.boostyboys.mcs.data.api.models.league.LeagueWithSeasons
import com.boostyboys.mcs.data.api.models.player.Player
import com.boostyboys.mcs.data.api.models.season.SeasonDataResponse
import kotlinx.coroutines.flow.StateFlow

interface McsRepository {
    val leagueSeasonConfigFlow: StateFlow<LeagueSeasonConfig?>

    suspend fun reloadLeagueSeasonConfig()

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
