package data.timeregion

import data.model.Region
import data.model.TimeRegion
import data.timeregion.mapper.AmericaZoneIdToTimeRegionMapper
import data.timeregion.mapper.AsiaZoneIdToTimeRegionMapper
import data.timeregion.mapper.AustraliaZoneIdToTimeRegionMapper
import data.timeregion.mapper.EuropeZoneIdToTimeRegionMapper
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

/**
 * https://en.wikipedia.org/wiki/List_of_tz_database_time_zones
 */
object TimescapeRepository {
    val timeRegions: List<TimeRegion>
    private val americaZoneIdToTimeRegionMapper by lazy { AmericaZoneIdToTimeRegionMapper() }
    private val asiaZoneIdToTimeRegionMapper by lazy { AsiaZoneIdToTimeRegionMapper() }
    private val australiaZoneIdToTimeRegionMapper by lazy { AustraliaZoneIdToTimeRegionMapper() }
    private val europeZoneIdToTimeRegionMapper by lazy { EuropeZoneIdToTimeRegionMapper() }

    init {
        val availableZones: Set<String> = TimeZone.availableZoneIds
        timeRegions = Region.entries.toTypedArray().flatMap { region ->
            availableZones.filter { it.contains("$region/") }
                .transformZoneIds(region)
        }
    }

    private fun List<String>.transformZoneIds(
        region: Region
    ): List<TimeRegion> = when (region) {
        Region.Africa -> genericZoneIdToTimezoneMapper(region)
        Region.America -> americaZoneIdToTimeRegionMapper(this)
        Region.Antarctica -> genericZoneIdToTimezoneMapper(region)
        Region.Arctic -> genericZoneIdToTimezoneMapper(region)
        Region.Asia -> asiaZoneIdToTimeRegionMapper(this)
        Region.Atlantic -> genericZoneIdToTimezoneMapper(region)
        Region.Australia -> australiaZoneIdToTimeRegionMapper(this)
        Region.Brazil -> genericZoneIdToTimezoneMapper(region)
        Region.Canada -> genericZoneIdToTimezoneMapper(region)
        Region.Chile -> genericZoneIdToTimezoneMapper(region)
        Region.Europe -> europeZoneIdToTimeRegionMapper(this)
        Region.Indian -> genericZoneIdToTimezoneMapper(region)
        Region.Mexico -> genericZoneIdToTimezoneMapper(region)
        Region.Pacific -> genericZoneIdToTimezoneMapper(region)
        Region.US -> genericZoneIdToTimezoneMapper(region)
    }

    private fun List<String>.genericZoneIdToTimezoneMapper(
        region: Region
    ): List<TimeRegion> = map {
        TimeRegion(it, region)
    }

    fun search(query: String): List<TimeRegion> {
        return timeRegions.filter { timeRegion -> timeRegion.doesMatchSearchQuery(query) }
    }
}

fun LocalDateTime.convert(
    from: TimeRegion,
    to: TimeRegion
): LocalDateTime {
    return this.toInstant(from.timeZone)
        .toLocalDateTime(to.timeZone)
}
