package data.locationform.model

data class NewLocationData(
    val id: String? = null,
    val label: String? = null,
    val regionName: String? = null,
    val zoneId: String? = null,
) {
    val isEditMode: Boolean
        get() = !id.isNullOrBlank()
}