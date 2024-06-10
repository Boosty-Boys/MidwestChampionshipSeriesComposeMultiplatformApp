package com.boostyboys.mcs.data.api.models.season

import cafe.adriel.voyager.core.lifecycle.JavaSerializable
import kotlin.jvm.JvmInline

@JvmInline
value class Week(val value: Int) : JavaSerializable {
    init {
        require(value >= 0) {
            "Negative weeks do not make sense"
        }
    }
}
