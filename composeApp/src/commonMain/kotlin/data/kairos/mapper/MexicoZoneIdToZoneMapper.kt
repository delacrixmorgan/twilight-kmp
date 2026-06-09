package data.kairos.mapper

import data.kairos.model.Region
import data.kairos.model.Zone
import data.utils.Mapper

class MexicoZoneIdToZoneMapper : Mapper<List<String>, List<Zone>> {
    companion object {
        private val region = Region.Mexico
    }

    override fun invoke(input: List<String>): List<Zone> = input.map { zoneIdString ->
        when (zoneIdString) {
            "$region/BajaNorte" -> Zone(
                zoneIdString, region,
                country = listOf("Mexico", "MX", "🇲🇽"),
                keywords = listOf("Baja California", "Tijuana", "Mexicali", "Ensenada"),
            )
            "$region/BajaSur" -> Zone(
                zoneIdString, region,
                country = listOf("Mexico", "MX", "🇲🇽"),
                keywords = listOf("Baja California Sur", "La Paz", "Cabo San Lucas", "San José del Cabo"),
            )
            "$region/General" -> Zone(
                zoneIdString, region,
                country = listOf("Mexico", "MX", "🇲🇽"),
                keywords = listOf("Mexico City", "Guadalajara", "Puebla", "Monterrey", "León", "Toluca"),
            )
            else -> Zone(zoneIdString, region)
        }
    }
}
