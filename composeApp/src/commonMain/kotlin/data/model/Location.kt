package data.model

import kotlinx.datetime.TimeZone

data class Location(
    val id: String,
    val label: String,
    val customRegionName: String,
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