package data.kairos.mapper

import data.kairos.model.Region
import data.kairos.model.Zone
import data.utils.Mapper

class USZoneIdToZoneMapper : Mapper<List<String>, List<Zone>> {
    companion object {
        private val region = Region.US
    }

    override fun invoke(input: List<String>): List<Zone> = input.map { zoneIdString ->
        when (zoneIdString) {
            "$region/Alaska" -> Zone(
                zoneIdString, region,
                country = listOf("United States", "US", "🇺🇸"),
                keywords = listOf("Alaska", "Anchorage", "Fairbanks", "Juneau"),
            )
            "$region/Aleutian" -> Zone(
                zoneIdString, region,
                country = listOf("United States", "US", "🇺🇸"),
                keywords = listOf("Aleutian Islands", "Adak", "Unalaska"),
            )
            "$region/Arizona" -> Zone(
                zoneIdString, region,
                country = listOf("United States", "US", "🇺🇸"),
                keywords = listOf("Arizona", "Phoenix", "Tucson", "Mesa"),
            )
            "$region/Central" -> Zone(
                zoneIdString, region,
                country = listOf("United States", "US", "🇺🇸"),
                keywords = listOf("Central Time", "Chicago", "Houston", "Dallas", "San Antonio", "Austin"),
            )
            "$region/Eastern" -> Zone(
                zoneIdString, region,
                country = listOf("United States", "US", "🇺🇸"),
                keywords = listOf("Eastern Time", "New York", "Washington", "Atlanta", "Boston", "Miami"),
            )
            "$region/East-Indiana",
            "$region/Indiana-Starke" -> Zone(
                zoneIdString, region,
                country = listOf("United States", "US", "🇺🇸"),
                keywords = listOf("Indiana", "Indianapolis", "Knox", "Starke County"),
            )
            "$region/Hawaii" -> Zone(
                zoneIdString, region,
                country = listOf("United States", "US", "🇺🇸"),
                keywords = listOf("Hawaii", "Honolulu", "Hilo", "Kailua"),
            )
            "$region/Michigan" -> Zone(
                zoneIdString, region,
                country = listOf("United States", "US", "🇺🇸"),
                keywords = listOf("Michigan", "Detroit", "Grand Rapids"),
            )
            "$region/Mountain" -> Zone(
                zoneIdString, region,
                country = listOf("United States", "US", "🇺🇸"),
                keywords = listOf("Mountain Time", "Denver", "Salt Lake City", "Albuquerque"),
            )
            "$region/Pacific" -> Zone(
                zoneIdString, region,
                country = listOf("United States", "US", "🇺🇸"),
                keywords = listOf("Pacific Time", "Los Angeles", "San Francisco", "Seattle", "San Diego", "Portland"),
            )
            "$region/Samoa" -> Zone(
                zoneIdString, region,
                country = listOf("United States", "US", "🇺🇸"),
                keywords = listOf("American Samoa", "Pago Pago"),
            )
            else -> Zone(zoneIdString, region)
        }
    }
}
