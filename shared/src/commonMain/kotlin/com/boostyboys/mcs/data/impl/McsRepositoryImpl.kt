package com.boostyboys.mcs.data.impl

import com.boostyboys.mcs.data.api.LocalRepository
import com.boostyboys.mcs.data.api.McsRepository
import com.boostyboys.mcs.data.api.either.Either
import com.boostyboys.mcs.data.api.models.LeagueSeasonConfig
import com.boostyboys.mcs.data.api.models.league.LeagueWithSeasons
import com.boostyboys.mcs.data.api.models.player.Player
import com.boostyboys.mcs.data.api.models.season.SeasonDataRequest
import com.boostyboys.mcs.data.api.models.season.SeasonDataResponse
import com.boostyboys.mcs.data.api.models.season.Week
import com.boostyboys.mcs.networking.api.McsManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class McsRepositoryImpl(
    private val mcsManager: McsManager,
    private val localRepository: LocalRepository,
) : McsRepository {
    private val _leagueSeasonsConfigFlow: MutableStateFlow<LeagueSeasonConfig?> = MutableStateFlow(null)

    override val leagueSeasonConfigFlow: StateFlow<LeagueSeasonConfig?>
        get() = _leagueSeasonsConfigFlow

    override suspend fun reloadLeagueSeasonConfig() {
        _leagueSeasonsConfigFlow.emit(null)

        val leagues: List<LeagueWithSeasons> = when (val leaguesResponse = mcsManager.getLeagues()) {
            is Either.Success -> {
                leaguesResponse.value.ifEmpty {
                    throw IllegalStateException("No leagues found")
                }
            }
            is Either.Failure -> {
                throw leaguesResponse.error
            }
        }

        val leagueToSelect = leagues.find { it.id == localRepository.selectedLeagueId } ?: leagues.first()
        val seasonToSelect = leagueToSelect.seasons.find { it.id == localRepository.selectedSeasonId }
            ?: leagueToSelect.seasons.find { it.id == leagueToSelect.currentSeasonId }
            ?: leagueToSelect.seasons.first()

        val seasonData: SeasonDataResponse = when (
            val seasonDataResponse = mcsManager.getSeasonData(
                SeasonDataRequest(
                    seasonId = seasonToSelect.id,
                    teamIds = seasonToSelect.teamIds,
                ),
            )
        ) {
            is Either.Success -> {
                seasonDataResponse.value
            }
            is Either.Failure -> {
                throw seasonDataResponse.error
            }
        }

        val matches = seasonData.matches
        val groupedMatches = matches.groupBy { Week(it.week) }
        val sortedWeeks = groupedMatches.keys.sortedBy { it.value }
        val sortedMatches = sortedWeeks.associateWith { week ->
            groupedMatches[week]?.sortedBy { it.scheduledDateTime } ?: emptyList()
        }

        val leagueSeasonConfig = LeagueSeasonConfig(
            selectedLeague = leagueToSelect,
            selectedSeason = seasonToSelect,
            leagues = leagues,
            teams = seasonData.teams,
            schedule = sortedMatches,
        )

        localRepository.selectedLeagueId = leagueToSelect.id
        localRepository.selectedSeasonId = seasonToSelect.id
        _leagueSeasonsConfigFlow.emit(leagueSeasonConfig)
    }

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
