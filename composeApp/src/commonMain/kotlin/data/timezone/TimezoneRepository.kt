package data.timezone

import data.model.Region
import data.model.Timezone
import data.timezone.mapper.AsiaZoneIdToTimezoneMapper
import data.timezone.mapper.EuropeZoneIdToTimezoneMapper
import kotlinx.datetime.TimeZone

/**
 * https://en.wikipedia.org/wiki/List_of_tz_database_time_zones
 */
object TimezoneRepository {
    val timezones: List<Timezone>
    private val asiaZoneIdToTimezoneMapper by lazy { AsiaZoneIdToTimezoneMapper() }
    private val europeZoneIdToTimezoneMapper by lazy { EuropeZoneIdToTimezoneMapper() }

    init {
        val availableZones: Set<String> = TimeZone.availableZoneIds
        timezones = Region.entries.toTypedArray().flatMap { zone ->
            availableZones.filter { it.contains("$zone/") }
                .transformZoneIds(zone)
        }
    }

    private fun List<String>.transformZoneIds(
        region: Region
    ): List<Timezone> = when (region) {
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
    ): List<Timezone> = map {
        Timezone(it, region)
    }
}