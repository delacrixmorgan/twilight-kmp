package data.utils

import data.timescape.model.TimeRegion
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

fun LocalDateTime.Companion.now(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime {
    return Clock.System.now().toLocalDateTime(timeZone)
}

fun LocalDateTime.convert(
    from: TimeRegion,
    to: TimeRegion
): LocalDateTime {
    return this.toInstant(from.timeZone)
        .toLocalDateTime(to.timeZone)
}

fun LocalDate.Companion.now(): LocalDate {
    return LocalDateTime.now().date
}

fun LocalTime.Companion.now(): LocalTime {
    return LocalDateTime.now().time
}

fun TimeZone.localTime(): String {
    return LocalDateTime.now(this).format(DateFormat.twelveHour)
}