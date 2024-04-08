package data.timezone.mapper

import data.model.Region
import data.model.Timezone
import data.utils.Mapper

class EuropeZoneIdToTimezoneMapper : Mapper<List<String>, List<Timezone>> {
    companion object {
        private val region = Region.Europe
    }

    override fun invoke(input: List<String>): List<Timezone> = input.map {
        when (it) {
            "$region/Amsterdam" -> Timezone(
                it, region,
                country = listOf("Netherlands", "NL"),
                states = listOf("Drenthe", "Flevoland", "Friesland", "Gelderland", "Groningen", "Limburg", "North Brabant", "North Holland", "Overijssel", "Utrecht", "Zeeland", "South Holland"),
                cities = listOf("Amsterdam", "Rotterdam", "The Hague", "Utrecht", "Eindhoven", "Tilburg", "Groningen", "Almere", "Breda", "Nijmegen", "Enschede", "Haarlem", "Arnhem", "Zaanstad", "Amersfoort", "Apeldoorn", "Zwolle", "Maastricht", "Dordrecht", "Leiden")
            )
            else -> Timezone(it, region)
        }
    }
}