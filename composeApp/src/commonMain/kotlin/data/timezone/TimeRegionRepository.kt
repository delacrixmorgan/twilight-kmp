package data.timezone

import data.model.Region
import data.model.TimeRegion
import data.timezone.mapper.AsiaZoneIdToTimezoneMapper
import data.timezone.mapper.EuropeZoneIdToTimezoneMapper
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

/**
 * https://en.wikipedia.org/wiki/List_of_tz_database_time_zones
 */
object TimeRegionRepository {
    val timeRegions: List<TimeRegion>
    private val asiaZoneIdToTimezoneMapper by lazy { AsiaZoneIdToTimezoneMapper() }
    private val europeZoneIdToTimezoneMapper by lazy { EuropeZoneIdToTimezoneMapper() }

    init {
        val availableZones: Set<String> = TimeZone.availableZoneIds
        timeRegions = Region.entries.toTypedArray().flatMap { zone ->
            availableZones.filter { it.contains("$zone/") }
                .transformZoneIds(zone)
        }
    }

    private fun List<String>.transformZoneIds(
        region: Region
    ): List<TimeRegion> = when (region) {
        Region.Africa -> genericZoneIdToTimezoneMapper(region)
        Region.America -> genericZoneIdToTimezoneMapper(region)
        Region.Antarctica -> genericZoneIdToTimezoneMapper(region)
        Region.Arctic -> genericZoneIdToTimezoneMapper(region)
        Region.Asia -> asiaZoneIdToTimezoneMapper(this)
        Region.Atlantic -> genericZoneIdToTimezoneMapper(region)
        Region.Australia -> genericZoneIdToTimezoneMapper(region)
        Region.Brazil -> genericZoneIdToTimezoneMapper(region)
        Region.Canada -> genericZoneIdToTimezoneMapper(region)
        Region.Chile -> genericZoneIdToTimezoneMapper(region)
        Region.Europe -> europeZoneIdToTimezoneMapper(this)
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

    fun convert(
        fromLocalDateTime: LocalDateTime,
        from: TimeRegion,
        to: TimeRegion
    ): LocalDateTime {
        return convert(fromLocalDateTime, from.timeZone, to.timeZone)
    }

    fun convert(
        fromLocalDateTime: LocalDateTime,
        from: TimeZone,
        to: TimeZone
    ): LocalDateTime {
        return fromLocalDateTime.toInstant(from)
            .toLocalDateTime(to)
    }
}