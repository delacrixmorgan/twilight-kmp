package data.preferences

enum class LocationFormatPreference {
    Place,
    PlaceWithGMT,
    Person,
    PersonWithGMT;

    companion object {
        val Default = Place
    }

    val label: String
        get() = when (this) {
            Place -> "Place"
            PlaceWithGMT -> "Place GMT Offset"
            Person -> "Person"
            PersonWithGMT -> "Person GMT Offset"
        }

    val description: String
        get() = when (this) {
            Place -> "Melbourne"
            PlaceWithGMT -> "Melbourne GMT +10:00"
            Person -> "Aerith Gainsborough"
            PersonWithGMT -> "Aerith Gainsborough GMT +10:00"
        }
}