package data.model

import kotlinx.datetime.TimeZone
import randomUUID

data class Location(
    val id: String = randomUUID(),
    val label: String,
    val regionName: String,
    val type: LocationType,
    val zoneId: String,
) {
    val timeRegion get() = TimeZone.of(zoneId)
}

enum class LocationType {
    Person,
    Country,
    Custom
}