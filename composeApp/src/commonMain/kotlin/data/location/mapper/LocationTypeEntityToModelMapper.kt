package data.location.mapper

import data.model.LocationType
import data.utils.Mapper

class LocationTypeEntityToModelMapper : Mapper<String, LocationType> {
    override fun invoke(input: String): LocationType {
        return LocationType.entries.firstOrNull { it.name == input } ?: LocationType.Custom
    }
}