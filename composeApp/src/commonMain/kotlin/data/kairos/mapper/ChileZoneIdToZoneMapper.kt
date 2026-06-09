package data.kairos.mapper

import data.kairos.model.Region
import data.kairos.model.Zone
import data.utils.Mapper

class ChileZoneIdToZoneMapper : Mapper<List<String>, List<Zone>> {
    companion object {
        private val region = Region.Chile
    }

    override fun invoke(input: List<String>): List<Zone> = input.map { zoneIdString ->
        when (zoneIdString) {
            "$region/Continental" -> Zone(
                zoneIdString, region,
                country = listOf("Chile", "CL", "🇨🇱"),
                keywords = listOf("Santiago", "Valparaíso", "Concepción", "Antofagasta", "Viña del Mar", "Temuco"),
            )
            "$region/EasterIsland" -> Zone(
                zoneIdString, region,
                country = listOf("Chile", "CL", "🇨🇱"),
                keywords = listOf("Easter Island", "Hanga Roa", "Rapa Nui"),
            )
            else -> Zone(zoneIdString, region)
        }
    }
}
