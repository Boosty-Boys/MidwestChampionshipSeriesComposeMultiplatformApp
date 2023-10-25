package com.boostyboys.mcs.util

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toNSDateComponents
import platform.Foundation.NSCalendar
import platform.Foundation.NSCalendarIdentifierGregorian
import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter

actual fun LocalDateTime.format(format: String): String {
    val components = toNSDateComponents()
    val calendar = NSCalendar(NSCalendarIdentifierGregorian)
    val nsDate: NSDate = calendar.dateFromComponents(components)
        ?: throw IllegalStateException("Could not convert Kotlin LocalDateTime to NSDate $this")

    val dateFormatter = NSDateFormatter()
    dateFormatter.dateFormat = format

    return dateFormatter.stringFromDate(nsDate)
}
