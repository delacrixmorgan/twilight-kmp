package data.kalika

import data.kalika.mapper.AmericaZoneIdToTimeRegionMapper
import data.kalika.mapper.AsiaZoneIdToTimeRegionMapper
import data.kalika.mapper.AustraliaZoneIdToTimeRegionMapper
import data.kalika.mapper.EuropeZoneIdToTimeRegionMapper
import data.kalika.mapper.PacificZoneIdToTimeRegionMapper
import data.kalika.model.Region
import data.kalika.model.TimeRegion
import kotlinx.datetime.TimeZone
import org.koin.core.component.KoinComponent

/**
 * https://en.wikipedia.org/wiki/Kairos
 * https://en.wikipedia.org/wiki/List_of_tz_database_time_zones
 */
class KairosRepository : KoinComponent {
    val timeRegions: List<TimeRegion>
    private val americaZoneIdToTimeRegionMapper by lazy { AmericaZoneIdToTimeRegionMapper() }
    private val asiaZoneIdToTimeRegionMapper by lazy { AsiaZoneIdToTimeRegionMapper() }
    private val australiaZoneIdToTimeRegionMapper by lazy { AustraliaZoneIdToTimeRegionMapper() }
    private val europeZoneIdToTimeRegionMapper by lazy { EuropeZoneIdToTimeRegionMapper() }
    private val pacificZoneIdToTimeRegionMapper by lazy { PacificZoneIdToTimeRegionMapper() }

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
        Region.Pacific -> pacificZoneIdToTimeRegionMapper(this)
        Region.US -> genericZoneIdToTimezoneMapper(region)
    }

    private fun List<String>.genericZoneIdToTimezoneMapper(
        region: Region
    ): List<TimeRegion> = map {
        TimeRegion(zoneIdString = it, region = region)
    }

    fun search(zoneId: String?): TimeRegion? {
        return timeRegions.firstOrNull { it.zoneIdString == zoneId }
    }
}
