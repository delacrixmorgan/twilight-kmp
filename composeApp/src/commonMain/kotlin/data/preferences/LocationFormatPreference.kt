package data.preferences

enum class LocationFormatPreference {
    Place,
    Person;

    companion object {
        val Default = Place
    }

    val label: String
        get() = when (this) {
            Place -> "Place"
            Person -> "Person"
        }

    val description: String
        get() = when (this) {
            Place -> "Melbourne"
            Person -> "Aerith Gainsborough"
        }
}