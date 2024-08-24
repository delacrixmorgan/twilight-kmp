package data.kairos

import data.kairos.mapper.AmericaZoneIdToZoneMapper
import data.kairos.mapper.AsiaZoneIdToZoneMapper
import data.kairos.mapper.AustraliaZoneIdToZoneMapper
import data.kairos.mapper.EuropeZoneIdToZoneMapper
import data.kairos.mapper.PacificZoneIdToZoneMapper
import data.kairos.model.Region
import data.kairos.model.Zone
import kotlinx.datetime.TimeZone
import org.koin.core.component.KoinComponent

/**
 * Kairos, ancient Greek word meaning 'the right or critical moment'
 * https://en.wikipedia.org/wiki/List_of_tz_database_time_zones
 */
class KairosRepository : KoinComponent {
    val zones: List<Zone>
    private val americaZoneIdToZoneMapper by lazy { AmericaZoneIdToZoneMapper() }
    private val asiaZoneIdToZoneMapper by lazy { AsiaZoneIdToZoneMapper() }
    private val australiaZoneIdToZoneMapper by lazy { AustraliaZoneIdToZoneMapper() }
    private val europeZoneIdToZoneMapper by lazy { EuropeZoneIdToZoneMapper() }
    private val pacificZoneIdToZoneMapper by lazy { PacificZoneIdToZoneMapper() }

    init {
        val availableZones: Set<String> = TimeZone.availableZoneIds
        zones = Region.entries.toTypedArray().flatMap { region ->
            availableZones.filter { it.contains("$region/") }
                .transformZoneIds(region)
        }
    }

    private fun List<String>.transformZoneIds(
        region: Region
    ): List<Zone> = when (region) {
        Region.Africa -> genericZoneIdToTimezoneMapper(region)
        Region.America -> americaZoneIdToZoneMapper(this)
        Region.Antarctica -> genericZoneIdToTimezoneMapper(region)
        Region.Arctic -> genericZoneIdToTimezoneMapper(region)
        Region.Asia -> asiaZoneIdToZoneMapper(this)
        Region.Atlantic -> genericZoneIdToTimezoneMapper(region)
        Region.Australia -> australiaZoneIdToZoneMapper(this)
        Region.Brazil -> genericZoneIdToTimezoneMapper(region)
        Region.Canada -> genericZoneIdToTimezoneMapper(region)
        Region.Chile -> genericZoneIdToTimezoneMapper(region)
        Region.Europe -> europeZoneIdToZoneMapper(this)
        Region.Indian -> genericZoneIdToTimezoneMapper(region)
        Region.Mexico -> genericZoneIdToTimezoneMapper(region)
        Region.Pacific -> pacificZoneIdToZoneMapper(this)
        Region.US -> genericZoneIdToTimezoneMapper(region)
    }

    private fun List<String>.genericZoneIdToTimezoneMapper(
        region: Region
    ): List<Zone> = map {
        Zone(zoneIdString = it, region = region)
    }

    fun search(zoneId: String?): Zone? {
        return zones.firstOrNull { it.zoneIdString == zoneId }
    }
}
