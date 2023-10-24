package com.boostyboys.mcs.data.impl

import com.boostyboys.mcs.data.api.McsRepository
import com.boostyboys.mcs.data.api.either.Either
import com.boostyboys.mcs.data.api.models.LeagueSeasonConfig
import com.boostyboys.mcs.data.api.models.league.LeagueWithSeasons
import com.boostyboys.mcs.data.api.models.player.Player
import com.boostyboys.mcs.data.api.models.season.SeasonDataResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeMcsRepository : McsRepository {

    override val leagueSeasonConfigFlow: StateFlow<LeagueSeasonConfig?>
        get() = MutableStateFlow(null)

    override suspend fun reloadLeagueSeasonConfig() {
        // no-op
    }

    override suspend fun getLeagues(): Either<List<LeagueWithSeasons>, Throwable> {
        return Either.success(emptyList())
    }

    override suspend fun getSeasonData(
        seasonId: String,
        teamIds: List<String>,
    ): Either<SeasonDataResponse, Throwable> {
        return Either.success(
            SeasonDataResponse(
                seasonId = "1",
                matches = emptyList(),
                teams = emptyList(),
            ),
        )
    }

    override suspend fun getPlayers(teamId: String, date: String): Either<List<Player>, Throwable> {
        return Either.success(emptyList())
    }
}
