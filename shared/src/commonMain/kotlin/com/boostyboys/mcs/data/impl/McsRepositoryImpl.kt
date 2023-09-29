package com.boostyboys.mcs.data.impl

import com.boostyboys.mcs.data.api.McsRepository
import com.boostyboys.mcs.data.api.either.Either
import com.boostyboys.mcs.data.api.models.League
import com.boostyboys.mcs.data.api.models.LeagueSeason
import com.boostyboys.mcs.data.api.models.LeagueSeason.Companion.toSeasons
import com.boostyboys.mcs.data.api.models.Match
import com.boostyboys.mcs.data.api.models.Season
import com.boostyboys.mcs.data.api.models.Team
import com.boostyboys.mcs.networking.api.McsManager
import kotlin.time.Duration
import kotlin.time.TimeMark
import kotlin.time.TimeSource

private const val LOAD_DELAY_MINUTES = 30

class McsRepositoryImpl(
    private val mcsManager: McsManager,
) : McsRepository {

    private var lastLoadTime: TimeMark? = null
    private var leagueSeasons: List<LeagueSeason>? = null

    override suspend fun getSeasons(): Either<List<Season>, Throwable> {
        val elapsedTime = lastLoadTime?.elapsedNow() ?: Duration.ZERO

        leagueSeasons?.let {
            if (elapsedTime.inWholeMinutes < LOAD_DELAY_MINUTES) {
                return Either.success(it.toSeasons())
            }
        }

        lastLoadTime = TimeSource.Monotonic.markNow()

        return when (val leagueSeasonsResponse = mcsManager.getSeasons()) {
            is Either.Success -> {
                val leagueSeasonsValue = leagueSeasonsResponse.value
                leagueSeasons = leagueSeasonsValue

                Either.success(leagueSeasonsValue.toSeasons())
            }
            is Either.Failure -> {
                leagueSeasonsResponse
            }
        }
    }

    override suspend fun getLeagues(seasonNumber: String): Either<List<League>, Throwable> {
        return Either.success(
            leagueSeasons?.mapNotNull {
                if (it.name == seasonNumber) {
                    it.league
                } else {
                    null
                }
            } ?: emptyList(),
        )
    }

    override suspend fun getTeams(
        seasonNumber: String,
        leagueId: String,
    ): Either<List<Team>, Throwable> {
        return Either.success(
            leagueSeasons?.filter {
                it.league.id == leagueId && it.name == seasonNumber
            }?.map {
                it.teams
            }?.flatten() ?: emptyList(),
        )
    }

    override suspend fun getMatches(
        seasonNumber: String,
        leagueId: String,
    ): Either<List<Match>, Throwable> {
        return Either.success(
            leagueSeasons?.filter {
                it.league.id == leagueId && it.name == seasonNumber
            }?.map {
                it.matches
            }?.flatten() ?: emptyList(),
        )
    }
}
