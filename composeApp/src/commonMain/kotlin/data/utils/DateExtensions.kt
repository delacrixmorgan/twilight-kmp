package data.utils

import data.kairos.model.Zone
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun LocalDateTime.Companion.now(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime {
    return Clock.System.now().toLocalDateTime(timeZone)
}

@OptIn(ExperimentalTime::class)
fun LocalDateTime.convert(
    from: Zone,
    to: Zone
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