package com.boostyboys.mcs.data.api.models.player

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Account(
    @SerialName("_id")
    val id: String,
    @SerialName("platform")
    val platform: String,
    @SerialName("platform_id")
    val platformId: String,
    @SerialName("screen_name")
    val screenName: String?,
)
