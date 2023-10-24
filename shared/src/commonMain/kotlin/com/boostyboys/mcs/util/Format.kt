package com.boostyboys.mcs.util

import kotlinx.datetime.LocalDateTime

expect fun LocalDateTime.format(format: String): String
