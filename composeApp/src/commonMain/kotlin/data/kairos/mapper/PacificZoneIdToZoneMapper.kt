package data.kairos.mapper

import data.kairos.model.Region
import data.kairos.model.Zone
import data.utils.Mapper

class PacificZoneIdToZoneMapper : Mapper<List<String>, List<Zone>> {
    companion object {
        private val region = Region.Pacific
    }

    override fun invoke(input: List<String>): List<Zone> = input.map { zoneIdString ->
        when (zoneIdString) {
            "$region/Auckland" -> Zone(
                zoneIdString, region,
                country = listOf("New Zealand", "NZ", "\uD83C\uDDF3\uD83C\uDDFF"),
                keywords = listOf("Auckland", "Taranaki", "Hawke's Bay", "Wellington", "Nelson", "Marlborough", "Westland", "Canterbury", "Otago", "Christchurch"),
            )
            "$region/Chatham" -> Zone(
                zoneIdString, region,
                country = listOf("New Zealand", "NZ", "\uD83C\uDDF3\uD83C\uDDFF"),
                keywords = listOf("Chatham Islands", "Waitangi", "R\u0113kohu"),
            )
            "$region/Honolulu" -> Zone(
                zoneIdString, region,
                country = listOf("United States", "US", "\uD83C\uDDFA\uD83C\uDDF8"),
                keywords = listOf("Hawaii", "Honolulu", "Hilo", "Kailua", "Kapolei", "Pearl City", "Waipahu", "Maui"),
            )
            "$region/Pago_Pago" -> Zone(
                zoneIdString, region,
                country = listOf("American Samoa", "AS", "\uD83C\uDDE6\uD83C\uDDF8"),
                keywords = listOf("Pago Pago", "Tafuna", "Nu'uuli", "Faleniu", "Leone"),
            )
            "$region/Apia" -> Zone(
                zoneIdString, region,
                country = listOf("Samoa", "WS", "\uD83C\uDDFC\uD83C\uDDF8"),
                keywords = listOf("Apia", "Vaitele", "Faleula", "Siusega", "Malie"),
            )
            "$region/Fiji" -> Zone(
                zoneIdString, region,
                country = listOf("Fiji", "FJ", "\uD83C\uDDEB\uD83C\uDDEF"),
                keywords = listOf("Suva", "Nadi", "Lautoka", "Nausori", "Labasa", "Ba", "Levuka"),
            )
            "$region/Guam" -> Zone(
                zoneIdString, region,
                country = listOf("Guam", "GU", "\uD83C\uDDEC\uD83C\uDDFA"),
                keywords = listOf("Hag\u00E5t\u00F1a", "Dededo", "Tamuning", "Mangilao", "Yigo", "Barrigada"),
            )
            "$region/Saipan" -> Zone(
                zoneIdString, region,
                country = listOf("Northern Mariana Islands", "MP", "\uD83C\uDDF2\uD83C\uDDF5"),
                keywords = listOf("Saipan", "Garapan", "Tinian", "Rota", "Susupe", "Chalan Kanoa"),
            )
            "$region/Port_Moresby",
            "$region/Bougainville" -> Zone(
                zoneIdString, region,
                country = listOf("Papua New Guinea", "PG", "\uD83C\uDDF5\uD83C\uDDEC"),
                keywords = listOf("Port Moresby", "Lae", "Mount Hagen", "Madang", "Wewak", "Goroka", "Arawa"),
            )
            "$region/Noumea" -> Zone(
                zoneIdString, region,
                country = listOf("New Caledonia", "NC", "\uD83C\uDDF3\uD83C\uDDE8"),
                keywords = listOf("Noum\u00E9a", "Mont-Dore", "Dumb\u00E9a", "Pa\u00EFta", "Kon\u00E9"),
            )
            "$region/Tahiti",
            "$region/Marquesas",
            "$region/Gambier" -> Zone(
                zoneIdString, region,
                country = listOf("French Polynesia", "PF", "\uD83C\uDDF5\uD83C\uDDEB"),
                keywords = listOf("Papeete", "Faaa", "Punaauia", "Pirae", "Moorea", "Bora Bora", "Tahiti"),
            )
            "$region/Tongatapu" -> Zone(
                zoneIdString, region,
                country = listOf("Tonga", "TO", "\uD83C\uDDF9\uD83C\uDDF4"),
                keywords = listOf("Nuku\u02BBalofa", "Neiafu", "Haveluloto", "Vaini", "Pangai"),
            )
            "$region/Efate" -> Zone(
                zoneIdString, region,
                country = listOf("Vanuatu", "VU", "\uD83C\uDDFB\uD83C\uDDFA"),
                keywords = listOf("Port Vila", "Luganville", "Norsup", "Isangel", "Lakatoro"),
            )
            "$region/Guadalcanal" -> Zone(
                zoneIdString, region,
                country = listOf("Solomon Islands", "SB", "\uD83C\uDDF8\uD83C\uDDE7"),
                keywords = listOf("Honiara", "Auki", "Gizo", "Buala", "Tulagi", "Kirakira"),
            )
            "$region/Rarotonga" -> Zone(
                zoneIdString, region,
                country = listOf("Cook Islands", "CK", "\uD83C\uDDE8\uD83C\uDDF0"),
                keywords = listOf("Avarua", "Rarotonga", "Aitutaki", "Atiu", "Mangaia"),
            )
            "$region/Niue" -> Zone(
                zoneIdString, region,
                country = listOf("Niue", "NU", "\uD83C\uDDF3\uD83C\uDDFA"),
                keywords = listOf("Alofi", "Hakupu", "Avatele", "Tuapa"),
            )
            "$region/Palau" -> Zone(
                zoneIdString, region,
                country = listOf("Palau", "PW", "\uD83C\uDDF5\uD83C\uDDFC"),
                keywords = listOf("Ngerulmud", "Koror", "Melekeok", "Airai", "Meyungs"),
            )
            "$region/Majuro",
            "$region/Kwajalein" -> Zone(
                zoneIdString, region,
                country = listOf("Marshall Islands", "MH", "\uD83C\uDDF2\uD83C\uDDED"),
                keywords = listOf("Majuro", "Ebeye", "Kwajalein", "Jabor", "Wotje"),
            )
            "$region/Tarawa",
            "$region/Kiritimati",
            "$region/Kanton" -> Zone(
                zoneIdString, region,
                country = listOf("Kiribati", "KI", "\uD83C\uDDF0\uD83C\uDDEE"),
                keywords = listOf("Tarawa", "South Tarawa", "Betio", "Kiritimati", "Bairiki", "Bikenibeu"),
            )
            "$region/Nauru" -> Zone(
                zoneIdString, region,
                country = listOf("Nauru", "NR", "\uD83C\uDDF3\uD83C\uDDF7"),
                keywords = listOf("Yaren", "Denigomodu", "Aiwo", "Meneng", "Boe"),
            )
            "$region/Funafuti" -> Zone(
                zoneIdString, region,
                country = listOf("Tuvalu", "TV", "\uD83C\uDDF9\uD83C\uDDFB"),
                keywords = listOf("Funafuti", "Vaiaku", "Asau", "Tanrake"),
            )
            "$region/Chuuk",
            "$region/Pohnpei",
            "$region/Kosrae" -> Zone(
                zoneIdString, region,
                country = listOf("Micronesia", "FM", "\uD83C\uDDEB\uD83C\uDDF2"),
                keywords = listOf("Palikir", "Weno", "Kolonia", "Chuuk", "Pohnpei", "Kosrae", "Tofol"),
            )
            "$region/Wallis" -> Zone(
                zoneIdString, region,
                country = listOf("Wallis and Futuna", "WF", "\uD83C\uDDFC\uD83C\uDDEB"),
                keywords = listOf("Mata-Utu", "Leava", "Alo", "Sigave"),
            )
            "$region/Fakaofo" -> Zone(
                zoneIdString, region,
                country = listOf("Tokelau", "TK", "\uD83C\uDDF9\uD83C\uDDF0"),
                keywords = listOf("Fakaofo", "Nukunonu", "Atafu"),
            )
            "$region/Norfolk" -> Zone(
                zoneIdString, region,
                country = listOf("Norfolk Island", "NF", "\uD83C\uDDF3\uD83C\uDDEB"),
                keywords = listOf("Kingston", "Burnt Pine"),
            )
            "$region/Pitcairn" -> Zone(
                zoneIdString, region,
                country = listOf("Pitcairn Islands", "PN", "\uD83C\uDDF5\uD83C\uDDF3"),
                keywords = listOf("Adamstown"),
            )
            "$region/Easter" -> Zone(
                zoneIdString, region,
                country = listOf("Chile", "CL", "\uD83C\uDDE8\uD83C\uDDF1"),
                keywords = listOf("Easter Island", "Hanga Roa", "Rapa Nui"),
            )
            "$region/Galapagos" -> Zone(
                zoneIdString, region,
                country = listOf("Ecuador", "EC", "\uD83C\uDDEA\uD83C\uDDE8"),
                keywords = listOf("Gal\u00E1pagos", "Puerto Ayora", "Puerto Baquerizo Moreno", "Santa Cruz", "Isabela"),
            )
            else -> Zone(zoneIdString, region)
        }
    }
}