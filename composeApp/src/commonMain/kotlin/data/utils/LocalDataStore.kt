package data.utils

enum class LocalDataStore(private val url: String) {
    CreateNewLocation("twilight.create_new_location_data_store");

    fun path() = "$url.preferences_pb"
}