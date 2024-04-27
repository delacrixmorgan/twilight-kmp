package data.timezone.mapper

import data.model.Region
import data.model.TimeRegion
import data.utils.Mapper

class EuropeZoneIdToTimezoneMapper : Mapper<List<String>, List<TimeRegion>> {
    companion object {
        private val region = Region.Europe
    }

    override fun invoke(input: List<String>): List<TimeRegion> = input.map { zoneIdString ->
        when (zoneIdString) {
            "$region/Amsterdam" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Netherlands", "NL", "\uD83C\uDDF3\uD83C\uDDF1"),
                states = listOf("Drenthe", "Flevoland", "Friesland", "Gelderland", "Groningen", "Limburg", "North Brabant", "North Holland", "Overijssel", "Utrecht", "Zeeland", "South Holland"),
                cities = listOf("Amsterdam", "Rotterdam", "The Hague", "Utrecht", "Eindhoven", "Tilburg", "Groningen", "Almere", "Breda", "Nijmegen", "Enschede", "Haarlem", "Arnhem", "Zaanstad", "Amersfoort", "Apeldoorn", "Zwolle", "Maastricht", "Dordrecht", "Leiden")
            )
            "$region/Berlin" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Germany", "DE", "\uD83C\uDDE9\uD83C\uDDEA"),
                states = listOf("Brandenburg", "Berlin", "Bavaria", "Saxony Anhalt", "Mecklenburg Vorpommern", "Hamburg", "Saxony", "North Rhine Westphalia", "Schleswig Holstein", "Bremen", "Baden Württemberg", "Hesse", "Lower Saxony", "Rhineland Palatinate", "Saarland", "Thuringia"),
                cities = listOf("Berlin", "Hamburg", "Munich", "Cologne", "Frankfurt", "Stuttgart", "Düsseldorf", "Dortmund", "Essen", "Leipzig")
            )
            else -> TimeRegion(zoneIdString, region)
        }
    }
}