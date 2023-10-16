package com.boostyboys.mcs.data.impl

import com.boostyboys.mcs.data.api.McsRepository
import com.boostyboys.mcs.data.api.either.Either
import com.boostyboys.mcs.data.api.models.league.LeagueWithSeasons
import com.boostyboys.mcs.data.api.models.player.Player
import com.boostyboys.mcs.data.api.models.season.SeasonDataRequest
import com.boostyboys.mcs.data.api.models.season.SeasonDataResponse
import com.boostyboys.mcs.networking.api.McsManager

class McsRepositoryImpl(
    private val mcsManager: McsManager,
) : McsRepository {

    override suspend fun getLeagues(): Either<List<LeagueWithSeasons>, Throwable> {
        return mcsManager.getLeagues()
    }

    override suspend fun getSeasonData(
        seasonId: String,
        teamIds: List<String>,
    ): Either<SeasonDataResponse, Throwable> {
        return mcsManager.getSeasonData(SeasonDataRequest(seasonId, teamIds))
    }

    override suspend fun getPlayers(teamId: String, date: String): Either<List<Player>, Throwable> {
        return mcsManager.getPlayers(teamId, date)
    }
}
