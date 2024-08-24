package data.location.model

import kotlinx.datetime.TimeZone
import randomUUID

data class Location(
    val id: String = randomUUID(),
    val name: String,
    val regionName: String,
    val zoneId: String,
) {
    val zone get() = TimeZone.of(zoneId)
}