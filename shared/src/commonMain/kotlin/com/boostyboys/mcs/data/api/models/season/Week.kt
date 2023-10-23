package com.boostyboys.mcs.data.api.models.season

import kotlin.jvm.JvmInline

@JvmInline
value class Week(val value: Int) {
    init {
        require(value >= 0) {
            "Negative weeks do not make sense"
        }
    }
}
