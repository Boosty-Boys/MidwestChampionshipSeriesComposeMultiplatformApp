package com.boostyboys.mcs.data.api.models.match

enum class MatchStatus(val jsonValue: String) {

    CLOSED("closed"),

    OPEN("open"),

    MISSING("missing"),

    UNKNOWN("unknown"),
}
