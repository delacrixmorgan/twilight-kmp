package data.model

import data.utils.now
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format

data class TimeRegion(
    private val zoneIdString: String,
    val region: Region,
    val country: List<String> = listOf(),
    val states: List<String> = listOf(),
    val cities: List<String> = listOf()
) {
    val timeZone get() = TimeZone.of(zoneIdString)
    val zone get() = zoneIdString.split("/").first()
    val city get() = zoneIdString.split("/").last().replace(Regex("[_-]"), " ")
    val keywords: List<String> get() = (country + states + cities)
}

fun TimeRegion.localTime(): String {
    val now: LocalDateTime = LocalDateTime.now(timeZone)
    return now.format(DateFormat.twelfthHour)
}
