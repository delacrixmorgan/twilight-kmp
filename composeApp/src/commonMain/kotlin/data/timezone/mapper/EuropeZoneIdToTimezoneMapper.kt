package data.timezone.mapper

import data.model.Region
import data.model.TimeRegion
import data.utils.Mapper

class EuropeZoneIdToTimezoneMapper : Mapper<List<String>, List<TimeRegion>> {
    companion object {
        private val region = Region.Europe
    }

    override fun invoke(input: List<String>): List<TimeRegion> = input.map {
        when (it) {
            "$region/Amsterdam" -> TimeRegion(
                it, region,
                country = listOf("Netherlands", "NL"),
                states = listOf("Drenthe", "Flevoland", "Friesland", "Gelderland", "Groningen", "Limburg", "North Brabant", "North Holland", "Overijssel", "Utrecht", "Zeeland", "South Holland"),
                cities = listOf("Amsterdam", "Rotterdam", "The Hague", "Utrecht", "Eindhoven", "Tilburg", "Groningen", "Almere", "Breda", "Nijmegen", "Enschede", "Haarlem", "Arnhem", "Zaanstad", "Amersfoort", "Apeldoorn", "Zwolle", "Maastricht", "Dordrecht", "Leiden")
            )
            else -> TimeRegion(it, region)
        }
    }
}