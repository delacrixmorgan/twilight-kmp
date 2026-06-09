package data.kairos.mapper

import data.kairos.model.Region
import data.kairos.model.Zone
import data.utils.Mapper

class ArcticZoneIdToZoneMapper : Mapper<List<String>, List<Zone>> {
    companion object {
        private val region = Region.Arctic
    }

    override fun invoke(input: List<String>): List<Zone> = input.map { zoneIdString ->
        when (zoneIdString) {
            "$region/Longyearbyen" -> Zone(
                zoneIdString, region,
                country = listOf("Svalbard and Jan Mayen", "Norway", "SJ", "🇸🇯"),
                keywords = listOf("Longyearbyen", "Svalbard", "Barentsburg", "Ny-Ålesund", "Pyramiden"),
            )
            else -> Zone(zoneIdString, region)
        }
    }
}
