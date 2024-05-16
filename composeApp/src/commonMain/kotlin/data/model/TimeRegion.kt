package data.model

import data.utils.now
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format

data class TimeRegion(
    val zoneIdString: String,
    val region: Region,
    private val country: List<String> = listOf(),
    private val states: List<String> = listOf(),
    private val cities: List<String> = listOf()
) {
    val timeZone get() = TimeZone.of(zoneIdString)
    val zone get() = zoneIdString.split("/").first()
    val city get() = zoneIdString.split("/").last().replace(Regex("[_-]"), " ")
    private val keywords: List<String> get() = (listOf(region.name, zone, city) + country + states + cities)

    fun doesMatchSearchQuery(query: String): Boolean {
        return keywords.any { it.contains(query, ignoreCase = true) }
    }
}

fun TimeRegion.localTime(): String {
    val now: LocalDateTime = LocalDateTime.now(timeZone)
    return now.format(DateFormat.twelfthHour)
}