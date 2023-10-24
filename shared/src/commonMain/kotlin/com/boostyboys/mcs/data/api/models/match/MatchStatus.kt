package com.boostyboys.mcs.data.api.models.match

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.serializer

@Serializable(with = TestEnumSerializer::class)
enum class MatchStatus {

    @SerialName("closed")
    CLOSED,

    @SerialName("open")
    OPEN,

    @SerialName("missing")
    MISSING,

    @SerialName("unknown")
    UNKNOWN,
}

object TestEnumSerializer : EnumIgnoreUnknownSerializer<MatchStatus>(
    MatchStatus.values(),
    MatchStatus.UNKNOWN,
)

abstract class EnumIgnoreUnknownSerializer<T : Enum<T>>(values: Array<out T>, private val defaultValue: T) :
    KSerializer<T> {
    // Alternative to taking values in param, take clazz: Class<T>
    // - private val values = clazz.enumConstants
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(values.first()::class.qualifiedName!!, PrimitiveKind.STRING)

    // Build maps for faster parsing, used @SerialName annotation if present, fall back to name
    private val lookup = values.associateBy({ it }, { it.serialName })
    private val revLookup = values.associateBy { it.serialName }

    @OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
    private val T.serialName: String
        get() {
            // Get the descriptor for this enum value
            val elementDescriptor = this::class.serializer().descriptor.getElementDescriptor(this.ordinal)
            // Return the serial name if found, or the enum value's name as a fallback
            return elementDescriptor.serialName
        }

    override fun serialize(encoder: Encoder, value: T) {
        encoder.encodeString(lookup.getValue(value))
    }

    override fun deserialize(decoder: Decoder): T {
        // only run 'decoder.decodeString()' once
        return revLookup[decoder.decodeString()] ?: defaultValue // map.getOrDefault is not available < API-24
    }
}
