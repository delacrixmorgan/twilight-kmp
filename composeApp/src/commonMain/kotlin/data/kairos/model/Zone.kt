package data.kairos.model

import data.utils.DateFormat
import data.utils.now
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import randomUUID

data class Zone(
    val zoneIdString: String,
    val region: Region,
    val id: String = randomUUID(),
    private val country: List<String> = listOf(),
    private val keywords: List<String> = listOf(),
) {
    val timeZone get() = TimeZone.of(zoneIdString)
    val zone get() = zoneIdString.split("/").first()
    val city get() = zoneIdString.split("/").last().replace(Regex("[_-]"), " ")
    private val tags: List<String> get() = (listOf(region.name, zone, city) + country + keywords)

    fun doesMatchSearchQuery(query: String): Boolean {
        return tags.any { it.contains(query, ignoreCase = true) }
    }

    fun localTime(): String {
        val now: LocalDateTime = LocalDateTime.now(timeZone)
        return now.format(DateFormat.twelveHour)
    }
}