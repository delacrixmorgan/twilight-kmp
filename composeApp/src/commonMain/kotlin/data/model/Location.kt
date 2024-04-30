package data.model

data class Location(
    val name: String,
    val type: LocationType,
    val timeRegion: TimeRegion,
)

enum class LocationType {
    Person,
    Country,
    Custom
}