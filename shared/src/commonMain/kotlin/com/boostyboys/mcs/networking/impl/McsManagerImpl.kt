package com.boostyboys.mcs.networking.impl

import com.boostyboys.mcs.data.api.either.Either
import com.boostyboys.mcs.data.api.models.league.LeagueWithSeasons
import com.boostyboys.mcs.data.api.models.player.Player
import com.boostyboys.mcs.data.api.models.season.SeasonDataRequest
import com.boostyboys.mcs.data.api.models.season.SeasonDataResponse
import com.boostyboys.mcs.networking.api.McsManager
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class McsManagerImpl : McsManager {
    private val client by lazy {
        HttpClient {
            defaultRequest {
                url(McsManager.BASE_URL)
                contentType(ContentType.Application.Json)
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    },
                )
            }
        }
    }

    override suspend fun getLeagues(): Either<List<LeagueWithSeasons>, Throwable> {
        return try {
            val response: List<LeagueWithSeasons> = client.get("/leagues").body()
            Either.success(response)
        } catch (e: Throwable) {
            Either.failure(e)
        }
    }

    override suspend fun getSeasonData(request: SeasonDataRequest): Either<SeasonDataResponse, Throwable> {
        return try {
            val response = client.get("/season_data") {
                setBody(request)
            }

            val body = response.body<SeasonDataResponse>()

            Either.success(body)
        } catch (e: Throwable) {
            Either.failure(e)
        }
    }

    override suspend fun getPlayers(
        teamId: String,
        date: String,
    ): Either<List<Player>, Throwable> {
        return try {
            val response: List<Player> = client.get("/players") {
                parameter("team_id", teamId)
                parameter("date", date)
            }.body()

            Either.success(response)
        } catch (e: Throwable) {
            Either.failure(e)
        }
    }
}
