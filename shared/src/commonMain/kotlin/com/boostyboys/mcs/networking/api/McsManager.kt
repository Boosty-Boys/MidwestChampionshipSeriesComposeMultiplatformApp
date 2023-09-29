package com.boostyboys.mcs.networking.api

import com.boostyboys.mcs.data.api.either.Either
import com.boostyboys.mcs.data.api.models.LeagueSeason

interface McsManager {
    suspend fun getSeasons(): Either<List<LeagueSeason>, Throwable>

    companion object {
        const val BASE_URL = "https://mcs-app-backend-c57cd7cec173.herokuapp.com"
    }
}
