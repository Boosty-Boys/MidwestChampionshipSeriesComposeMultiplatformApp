package com.boostyboys.mcs.data.api.models.match

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object MatchStatusSerializer : KSerializer<MatchStatus> {

    override fun serialize(encoder: Encoder, value: MatchStatus) {
        encoder.encodeString(value.jsonValue)
    }

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("matchstatus", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): MatchStatus {
        val decodeString = decoder.decodeString()
        return MatchStatus.values().firstOrNull { it.jsonValue == decodeString } ?: MatchStatus.UNKNOWN
    }
}
