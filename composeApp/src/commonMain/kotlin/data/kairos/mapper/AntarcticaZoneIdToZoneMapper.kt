package data.kairos.mapper

import data.kairos.model.Region
import data.kairos.model.Zone
import data.utils.Mapper

class AntarcticaZoneIdToZoneMapper : Mapper<List<String>, List<Zone>> {
    companion object {
        private val region = Region.Antarctica
    }

    override fun invoke(input: List<String>): List<Zone> = input.map { zoneIdString ->
        when (zoneIdString) {
            "$region/McMurdo",
            "$region/South_Pole",
            "$region/Palmer" -> Zone(
                zoneIdString, region,
                country = listOf("Antarctica", "AQ", "🇦🇶"),
                keywords = listOf("McMurdo Station", "South Pole", "Amundsen-Scott", "Palmer Station", "United States", "Research Station"),
            )
            "$region/Casey",
            "$region/Davis",
            "$region/Mawson",
            "$region/Macquarie" -> Zone(
                zoneIdString, region,
                country = listOf("Antarctica", "AQ", "🇦🇶"),
                keywords = listOf("Casey Station", "Davis Station", "Mawson Station", "Macquarie Island", "Australia", "Research Station"),
            )
            "$region/DumontDUrville" -> Zone(
                zoneIdString, region,
                country = listOf("Antarctica", "AQ", "🇦🇶"),
                keywords = listOf("Dumont d'Urville Station", "Adélie Land", "France", "Research Station"),
            )
            "$region/Rothera" -> Zone(
                zoneIdString, region,
                country = listOf("Antarctica", "AQ", "🇦🇶"),
                keywords = listOf("Rothera Station", "Adelaide Island", "United Kingdom", "Research Station"),
            )
            "$region/Syowa" -> Zone(
                zoneIdString, region,
                country = listOf("Antarctica", "AQ", "🇦🇶"),
                keywords = listOf("Syowa Station", "East Ongul Island", "Japan", "Research Station"),
            )
            "$region/Troll" -> Zone(
                zoneIdString, region,
                country = listOf("Antarctica", "AQ", "🇦🇶"),
                keywords = listOf("Troll Station", "Queen Maud Land", "Norway", "Research Station"),
            )
            "$region/Vostok" -> Zone(
                zoneIdString, region,
                country = listOf("Antarctica", "AQ", "🇦🇶"),
                keywords = listOf("Vostok Station", "Russia", "Research Station"),
            )
            else -> Zone(zoneIdString, region)
        }
    }
}
