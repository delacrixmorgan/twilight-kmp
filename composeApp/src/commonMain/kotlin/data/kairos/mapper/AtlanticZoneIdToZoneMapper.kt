package data.kairos.mapper

import data.kairos.model.Region
import data.kairos.model.Zone
import data.utils.Mapper

class AtlanticZoneIdToZoneMapper : Mapper<List<String>, List<Zone>> {
    companion object {
        private val region = Region.Atlantic
    }

    override fun invoke(input: List<String>): List<Zone> = input.map { zoneIdString ->
        when (zoneIdString) {
            "$region/Azores",
            "$region/Madeira" -> Zone(
                zoneIdString, region,
                country = listOf("Portugal", "PT", "🇵🇹"),
                keywords = listOf("Ponta Delgada", "Angra do Heroísmo", "Horta", "Funchal", "Azores", "Madeira"),
            )
            "$region/Bermuda" -> Zone(
                zoneIdString, region,
                country = listOf("Bermuda", "BM", "🇧🇲"),
                keywords = listOf("Hamilton", "St. George's", "Somerset", "Bermuda"),
            )
            "$region/Canary" -> Zone(
                zoneIdString, region,
                country = listOf("Spain", "ES", "🇪🇸"),
                keywords = listOf("Las Palmas", "Santa Cruz de Tenerife", "Canary Islands", "Tenerife", "Gran Canaria", "Lanzarote"),
            )
            "$region/Cape_Verde" -> Zone(
                zoneIdString, region,
                country = listOf("Cape Verde", "Cabo Verde", "CV", "🇨🇻"),
                keywords = listOf("Praia", "Mindelo", "Santa Maria", "Assomada", "São Filipe"),
            )
            "$region/Faeroe",
            "$region/Faroe" -> Zone(
                zoneIdString, region,
                country = listOf("Faroe Islands", "FO", "🇫🇴"),
                keywords = listOf("Tórshavn", "Klaksvík", "Runavík", "Tvøroyri", "Faroe Islands"),
            )
            "$region/Jan_Mayen" -> Zone(
                zoneIdString, region,
                country = listOf("Norway", "NO", "🇳🇴"),
                keywords = listOf("Jan Mayen", "Olonkinbyen"),
            )
            "$region/Reykjavik" -> Zone(
                zoneIdString, region,
                country = listOf("Iceland", "IS", "🇮🇸"),
                keywords = listOf("Reykjavík", "Kópavogur", "Hafnarfjörður", "Akureyri", "Reykjanesbær", "Garðabær"),
            )
            "$region/South_Georgia" -> Zone(
                zoneIdString, region,
                country = listOf("South Georgia and the South Sandwich Islands", "GS", "🇬🇸"),
                keywords = listOf("King Edward Point", "Grytviken", "South Georgia"),
            )
            "$region/St_Helena" -> Zone(
                zoneIdString, region,
                country = listOf("Saint Helena", "SH", "🇸🇭"),
                keywords = listOf("Jamestown", "Half Tree Hollow", "Georgetown", "Saint Helena", "Ascension", "Tristan da Cunha"),
            )
            "$region/Stanley" -> Zone(
                zoneIdString, region,
                country = listOf("Falkland Islands", "FK", "🇫🇰"),
                keywords = listOf("Stanley", "Mount Pleasant", "Falkland Islands"),
            )
            else -> Zone(zoneIdString, region)
        }
    }
}
