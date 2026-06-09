package data.kairos.mapper

import data.kairos.model.Region
import data.kairos.model.Zone
import data.utils.Mapper

class IndianZoneIdToZoneMapper : Mapper<List<String>, List<Zone>> {
    companion object {
        private val region = Region.Indian
    }

    override fun invoke(input: List<String>): List<Zone> = input.map { zoneIdString ->
        when (zoneIdString) {
            "$region/Antananarivo" -> Zone(
                zoneIdString, region,
                country = listOf("Madagascar", "MG", "🇲🇬"),
                keywords = listOf("Antananarivo", "Toamasina", "Antsirabe", "Fianarantsoa", "Mahajanga", "Toliara"),
            )
            "$region/Chagos" -> Zone(
                zoneIdString, region,
                country = listOf("British Indian Ocean Territory", "IO", "🇮🇴"),
                keywords = listOf("Diego Garcia", "Chagos Archipelago"),
            )
            "$region/Christmas" -> Zone(
                zoneIdString, region,
                country = listOf("Christmas Island", "CX", "🇨🇽"),
                keywords = listOf("Flying Fish Cove", "Christmas Island"),
            )
            "$region/Cocos" -> Zone(
                zoneIdString, region,
                country = listOf("Cocos (Keeling) Islands", "CC", "🇨🇨"),
                keywords = listOf("West Island", "Bantam", "Cocos Islands", "Keeling Islands"),
            )
            "$region/Comoro" -> Zone(
                zoneIdString, region,
                country = listOf("Comoros", "KM", "🇰🇲"),
                keywords = listOf("Moroni", "Mutsamudu", "Fomboni", "Domoni", "Comoros"),
            )
            "$region/Kerguelen" -> Zone(
                zoneIdString, region,
                country = listOf("French Southern Territories", "TF", "🇹🇫"),
                keywords = listOf("Port-aux-Français", "Kerguelen", "Crozet", "Amsterdam Island"),
            )
            "$region/Mahe" -> Zone(
                zoneIdString, region,
                country = listOf("Seychelles", "SC", "🇸🇨"),
                keywords = listOf("Victoria", "Anse Boileau", "Beau Vallon", "Mahé", "Praslin", "La Digue"),
            )
            "$region/Maldives" -> Zone(
                zoneIdString, region,
                country = listOf("Maldives", "MV", "🇲🇻"),
                keywords = listOf("Malé", "Addu City", "Fuvahmulah", "Kulhudhuffushi", "Maldives"),
            )
            "$region/Mauritius" -> Zone(
                zoneIdString, region,
                country = listOf("Mauritius", "MU", "🇲🇺"),
                keywords = listOf("Port Louis", "Beau Bassin-Rose Hill", "Vacoas-Phoenix", "Curepipe", "Quatre Bornes"),
            )
            "$region/Mayotte" -> Zone(
                zoneIdString, region,
                country = listOf("Mayotte", "YT", "🇾🇹"),
                keywords = listOf("Mamoudzou", "Koungou", "Dzaoudzi", "Dembeni", "Mayotte"),
            )
            "$region/Reunion" -> Zone(
                zoneIdString, region,
                country = listOf("Réunion", "RE", "🇷🇪"),
                keywords = listOf("Saint-Denis", "Saint-Paul", "Saint-Pierre", "Le Tampon", "Saint-André", "Réunion"),
            )
            else -> Zone(zoneIdString, region)
        }
    }
}
