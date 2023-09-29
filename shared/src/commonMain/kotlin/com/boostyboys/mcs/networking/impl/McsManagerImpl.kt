package com.boostyboys.mcs.networking.impl

import com.boostyboys.mcs.data.api.either.Either
import com.boostyboys.mcs.data.api.models.LeagueSeason
import com.boostyboys.mcs.networking.api.McsManager
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
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

    override suspend fun getSeasons(): Either<List<LeagueSeason>, Throwable> {
        return try {
            val response: List<SeasonResponse> = client.get("/seasons").body()
            Either.success(response.map(SeasonResponse::toLocalModel))
        } catch (e: Throwable) {
            Either.failure(e)
        }
    }
}
