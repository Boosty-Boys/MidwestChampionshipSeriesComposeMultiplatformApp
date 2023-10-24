package com.boostyboys.mcs.data.api.models.team

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Var(
    @SerialName("_id")
    val id: String,
    @SerialName("key")
    val key: String,
    @SerialName("value")
    val value: String,
)
