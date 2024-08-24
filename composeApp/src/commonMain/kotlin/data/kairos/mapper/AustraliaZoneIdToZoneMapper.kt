package data.kairos.mapper

import data.kairos.model.Region
import data.kairos.model.Zone
import data.utils.Mapper

class AustraliaZoneIdToZoneMapper : Mapper<List<String>, List<Zone>> {
    companion object {
        private val region = Region.Australia
    }

    override fun invoke(input: List<String>): List<Zone> = input.map { zoneIdString ->
        when (zoneIdString) {
            "$region/Adelaide" -> Zone(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                states = listOf("South Australia"),
                cities = listOf("Adelaide")
            )
            "$region/Brisbane" -> Zone(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                states = listOf("Queensland"),
                cities = listOf("Brisbane")
            )
            "$region/Broken_Hill" -> Zone(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                states = listOf("South Australia", "New South Wales"),
                cities = listOf("Broken Hill")
            )
            "$region/Darwin" -> Zone(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                states = listOf("Northern Territory"),
                cities = listOf("Darwin")
            )
            "$region/Hobart" -> Zone(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                states = listOf("Tasmania"),
                cities = listOf("Hobart")
            )
            "$region/Lord_Howe" -> Zone(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                states = emptyList(),
                cities = listOf("Lord Howe Island")
            )
            "$region/Melbourne" -> Zone(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                states = listOf("Victoria"),
                cities = listOf("Melbourne")
            )
            "$region/Perth" -> Zone(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                states = emptyList(),
                cities = listOf("Perth")
            )
            "$region/Sydney" -> Zone(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                states = listOf("New South Wales"),
                cities = listOf("Sydney")
            )
            else -> Zone(zoneIdString, region)
        }
    }
}