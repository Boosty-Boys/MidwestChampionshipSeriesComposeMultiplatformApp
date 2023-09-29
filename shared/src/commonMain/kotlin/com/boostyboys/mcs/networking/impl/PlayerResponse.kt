package com.boostyboys.mcs.networking.impl

import com.boostyboys.mcs.data.api.models.Player
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerResponse(
    @SerialName("name") val name: String,
) {
    fun toLocalModel(): Player {
        return Player(
            name = name,
        )
    }
}
