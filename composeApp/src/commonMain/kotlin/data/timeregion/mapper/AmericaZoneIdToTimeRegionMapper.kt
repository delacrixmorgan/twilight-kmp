package data.timeregion.mapper

import data.model.Region
import data.model.TimeRegion
import data.utils.Mapper

class AmericaZoneIdToTimeRegionMapper : Mapper<List<String>, List<TimeRegion>> {
    companion object {
        private val region = Region.America
    }

    override fun invoke(input: List<String>): List<TimeRegion> = input.map { zoneIdString ->
        when (zoneIdString) {
            "$region/New_York" -> TimeRegion(
                zoneIdString, region,
                country = listOf("United States", "US", "\uD83C\uDDFA\uD83C\uDDF8"),
                states = listOf("Ohio"),
                cities = listOf("Cincinnati")
            )
            else -> TimeRegion(zoneIdString, region)
        }
    }
}