package data.timescape.mapper

import data.model.Region
import data.model.TimeRegion
import data.utils.Mapper

class AustraliaZoneIdToTimeRegionMapper : Mapper<List<String>, List<TimeRegion>> {
    companion object {
        private val region = Region.Australia
    }

    override fun invoke(input: List<String>): List<TimeRegion> = input.map { zoneIdString ->
        when (zoneIdString) {
            "$region/Adelaide" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                states = listOf("South Australia"),
                cities = listOf("Adelaide")
            )
            "$region/Brisbane" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                states = listOf("Queensland"),
                cities = listOf("Brisbane")
            )
            "$region/Broken_Hill" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                states = listOf("South Australia", "New South Wales"),
                cities = listOf("Broken Hill")
            )
            "$region/Darwin" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                states = listOf("Northern Territory"),
                cities = listOf("Darwin")
            )
            "$region/Hobart" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                states = listOf("Tasmania"),
                cities = listOf("Hobart")
            )
            "$region/Lord_Howe" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                states = emptyList(),
                cities = listOf("Lord Howe Island")
            )
            "$region/Melbourne" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                states = listOf("Victoria"),
                cities = listOf("Melbourne")
            )
            "$region/Perth" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                states = emptyList(),
                cities = listOf("Perth")
            )
            "$region/Sydney" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                states = listOf("New South Wales"),
                cities = listOf("Sydney")
            )
            else -> TimeRegion(zoneIdString, region)
        }
    }
}