package data.model

data class Location(
    val id: String,
    val name: String,
    val type: LocationType,
    val zoneIdString: String,
)

enum class LocationType {
    Person,
    Country,
    Custom
}