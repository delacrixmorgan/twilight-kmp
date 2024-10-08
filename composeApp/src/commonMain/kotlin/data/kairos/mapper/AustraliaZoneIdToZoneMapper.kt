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
                keywords = listOf("South Australia", "Adelaide"),
            )
            "$region/Brisbane" -> Zone(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                keywords = listOf("Queensland", "Brisbane"),
            )
            "$region/Broken_Hill" -> Zone(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                keywords = listOf("South Australia", "New South Wales", "Broken Hill", "Wollongong"),
            )
            "$region/Darwin" -> Zone(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                keywords = listOf("Northern Territory", "Darwin"),
            )
            "$region/Hobart" -> Zone(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                keywords = listOf("Tasmania", "Hobart"),
            )
            "$region/Lord_Howe" -> Zone(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                keywords = listOf("Lord Howe Island")
            )
            "$region/Melbourne" -> Zone(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                keywords = listOf("Victoria", "Melbourne"),
            )
            "$region/Perth" -> Zone(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                keywords = listOf("Perth")
            )
            "$region/Sydney" -> Zone(
                zoneIdString, region,
                country = listOf("Australia", "AU", "\uD83C\uDDE6\uD83C\uDDFA"),
                keywords = listOf("New South Wales", "Sydney"),
            )
            else -> Zone(zoneIdString, region)
        }
    }
}