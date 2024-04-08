package data.model

import data.utils.now
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format

data class Timezone(
    private val zoneIdString: String,
    val region: Region,
    val country: List<String> = listOf(),
    val states: List<String> = listOf(),
    val cities: List<String> = listOf()
) {
    val kotlinTimeZone get() = TimeZone.of(zoneIdString)
    val zone get() = zoneIdString.split("/").first()
    val city get() = zoneIdString.split("/").last().replace(Regex("[_-]"), " ")
    val keywords: List<String> get() = (country + states + cities)
}

fun Timezone.localTime(): String {
    val now: LocalDateTime = LocalDateTime.now(kotlinTimeZone)
    return now.format(DateFormat.twelfthHour)
}
