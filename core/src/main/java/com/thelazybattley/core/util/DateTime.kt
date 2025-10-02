package com.thelazybattley.core.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

const val DAY_MONTH_YEAR_TIME = "dd MMMM, yyyy hh:mm a"

fun String.convertDateTime(pattern: String = DAY_MONTH_YEAR_TIME) :String {
    return try {
        val zoneId = ZoneId.systemDefault()
        val instant = Instant.parse(this)
        val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
            .withZone(zoneId)
        formatter.format(instant)
    } catch (_: Exception) {
        this
    }

}
