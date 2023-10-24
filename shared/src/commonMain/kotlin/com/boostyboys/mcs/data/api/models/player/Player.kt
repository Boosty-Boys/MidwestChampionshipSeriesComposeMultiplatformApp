package com.boostyboys.mcs.data.api.models.player

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Player(
    @SerialName("accounts")
    val accounts: List<Account>,
    @SerialName("avatar")
    val avatar: String?,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("discord_id")
    val discordId: String?,
    @SerialName("discord_linked")
    val discordLinked: String?,
    @SerialName("_id")
    val id: String,
    @SerialName("permissions")
    val permissions: List<String>,
    @SerialName("screen_name")
    val screenName: String,
    @SerialName("team_history")
    val teamHistory: List<TeamHistory>,
    @SerialName("updated_at")
    val updatedAt: String,
)
