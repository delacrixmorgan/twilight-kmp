package data.timescape.mapper

import data.timescape.model.Region
import data.timescape.model.TimeRegion
import data.utils.Mapper

class PacificZoneIdToTimeRegionMapper : Mapper<List<String>, List<TimeRegion>> {
    companion object {
        private val region = Region.Pacific
    }

    override fun invoke(input: List<String>): List<TimeRegion> = input.map { zoneIdString ->
        when (zoneIdString) {
            "$region/Auckland" -> TimeRegion(
                zoneIdString, region,
                country = listOf("New Zealand", "NZ", "\uD83C\uDDF3\uD83C\uDDFF"),
                states = listOf("Auckland", "Taranaki", "Hawke's Bay", "Wellington", "Nelson", "Marlborough", "Westland", "Canterbury", "Otago"),
                cities = listOf("Auckland", "Christchurch", "Wellington")
            )
            else -> TimeRegion(zoneIdString, region)
        }
    }
}