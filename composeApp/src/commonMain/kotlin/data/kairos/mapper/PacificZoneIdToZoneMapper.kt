package data.kairos.mapper

import data.kairos.model.Region
import data.kairos.model.Zone
import data.utils.Mapper

class PacificZoneIdToZoneMapper : Mapper<List<String>, List<Zone>> {
    companion object {
        private val region = Region.Pacific
    }

    override fun invoke(input: List<String>): List<Zone> = input.map { zoneIdString ->
        when (zoneIdString) {
            "$region/Auckland" -> Zone(
                zoneIdString, region,
                country = listOf("New Zealand", "NZ", "\uD83C\uDDF3\uD83C\uDDFF"),
                keywords = listOf("Auckland", "Taranaki", "Hawke's Bay", "Wellington", "Nelson", "Marlborough", "Westland", "Canterbury", "Otago", "Christchurch"),
            )
            else -> Zone(zoneIdString, region)
        }
    }
}