package com.boostyboys.mcs.data.api.models.match

import cafe.adriel.voyager.core.lifecycle.JavaSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReplayOrigin(
    @SerialName("key")
    val key: String,
    @SerialName("source")
    val source: String,
) : JavaSerializable
