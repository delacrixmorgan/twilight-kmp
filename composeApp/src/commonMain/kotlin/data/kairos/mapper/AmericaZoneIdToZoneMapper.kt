package data.kairos.mapper

import data.kairos.model.Region
import data.kairos.model.Zone
import data.utils.Mapper

class AmericaZoneIdToZoneMapper : Mapper<List<String>, List<Zone>> {
    companion object {
        private val region = Region.America
    }

    override fun invoke(input: List<String>): List<Zone> = input.map { zoneIdString ->
        when (zoneIdString) {
            "$region/New_York" -> Zone(
                zoneIdString, region,
                country = listOf("United States", "US", "\uD83C\uDDFA\uD83C\uDDF8"),
                states = listOf("Ohio"),
                cities = listOf("Cincinnati")
            )
            else -> Zone(zoneIdString, region)
        }
    }
}