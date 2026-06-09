package data.kairos.mapper

import data.kairos.model.Region
import data.kairos.model.Zone
import data.utils.Mapper

class CanadaZoneIdToZoneMapper : Mapper<List<String>, List<Zone>> {
    companion object {
        private val region = Region.Canada
    }

    override fun invoke(input: List<String>): List<Zone> = input.map { zoneIdString ->
        when (zoneIdString) {
            "$region/Atlantic" -> Zone(
                zoneIdString, region,
                country = listOf("Canada", "CA", "🇨🇦"),
                keywords = listOf("Atlantic Time", "Halifax", "Nova Scotia", "New Brunswick", "Moncton", "Charlottetown"),
            )
            "$region/Central" -> Zone(
                zoneIdString, region,
                country = listOf("Canada", "CA", "🇨🇦"),
                keywords = listOf("Central Time", "Winnipeg", "Manitoba"),
            )
            "$region/Eastern" -> Zone(
                zoneIdString, region,
                country = listOf("Canada", "CA", "🇨🇦"),
                keywords = listOf("Eastern Time", "Toronto", "Ottawa", "Montreal", "Ontario", "Quebec"),
            )
            "$region/Mountain" -> Zone(
                zoneIdString, region,
                country = listOf("Canada", "CA", "🇨🇦"),
                keywords = listOf("Mountain Time", "Calgary", "Edmonton", "Alberta"),
            )
            "$region/Newfoundland" -> Zone(
                zoneIdString, region,
                country = listOf("Canada", "CA", "🇨🇦"),
                keywords = listOf("Newfoundland", "St. John's", "Labrador"),
            )
            "$region/Pacific" -> Zone(
                zoneIdString, region,
                country = listOf("Canada", "CA", "🇨🇦"),
                keywords = listOf("Pacific Time", "Vancouver", "British Columbia", "Victoria"),
            )
            "$region/Saskatchewan" -> Zone(
                zoneIdString, region,
                country = listOf("Canada", "CA", "🇨🇦"),
                keywords = listOf("Saskatchewan", "Regina", "Saskatoon"),
            )
            "$region/Yukon" -> Zone(
                zoneIdString, region,
                country = listOf("Canada", "CA", "🇨🇦"),
                keywords = listOf("Yukon", "Whitehorse", "Dawson City"),
            )
            else -> Zone(zoneIdString, region)
        }
    }
}
