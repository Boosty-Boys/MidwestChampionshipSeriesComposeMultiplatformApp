package com.boostyboys.mcs.data.api.models.match

import cafe.adriel.voyager.core.lifecycle.JavaSerializable
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.jvm.Transient

@Serializable(with = LocalDateTimeWrapper.LocalDateTimeWrapperSerializer::class)
data class LocalDateTimeWrapper(
    private val localDateTimeString: String?,
) : JavaSerializable {
    @Transient private var _localDateTime: LocalDateTime? = null
    val localDateTime: LocalDateTime?
        get() {
            if (_localDateTime == null && !localDateTimeString.isNullOrBlank()) {
                _localDateTime = localDateTimeString.toInstant().toLocalDateTime(
                    timeZone = TimeZone.currentSystemDefault(),
                )
            }
            return _localDateTime
        }

    companion object LocalDateTimeWrapperSerializer : KSerializer<LocalDateTimeWrapper> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
            "LocalDateTimeWrapper",
            PrimitiveKind.STRING,
        )

        override fun serialize(encoder: Encoder, value: LocalDateTimeWrapper) {
            encoder.encodeString(value.localDateTimeString ?: "")
        }

        override fun deserialize(decoder: Decoder): LocalDateTimeWrapper {
            val dateTimeString = decoder.decodeString()
            return LocalDateTimeWrapper(dateTimeString)
        }
    }
}
