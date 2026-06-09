package data.kairos

import data.kairos.mapper.AfricaZoneIdToZoneMapper
import data.kairos.mapper.AmericaZoneIdToZoneMapper
import data.kairos.mapper.AntarcticaZoneIdToZoneMapper
import data.kairos.mapper.ArcticZoneIdToZoneMapper
import data.kairos.mapper.AsiaZoneIdToZoneMapper
import data.kairos.mapper.AtlanticZoneIdToZoneMapper
import data.kairos.mapper.AustraliaZoneIdToZoneMapper
import data.kairos.mapper.BrazilZoneIdToZoneMapper
import data.kairos.mapper.CanadaZoneIdToZoneMapper
import data.kairos.mapper.ChileZoneIdToZoneMapper
import data.kairos.mapper.EuropeZoneIdToZoneMapper
import data.kairos.mapper.IndianZoneIdToZoneMapper
import data.kairos.mapper.MexicoZoneIdToZoneMapper
import data.kairos.mapper.PacificZoneIdToZoneMapper
import data.kairos.mapper.USZoneIdToZoneMapper
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
    private val africaZoneIdToZoneMapper by lazy { AfricaZoneIdToZoneMapper() }
    private val americaZoneIdToZoneMapper by lazy { AmericaZoneIdToZoneMapper() }
    private val antarcticaZoneIdToZoneMapper by lazy { AntarcticaZoneIdToZoneMapper() }
    private val arcticZoneIdToZoneMapper by lazy { ArcticZoneIdToZoneMapper() }
    private val asiaZoneIdToZoneMapper by lazy { AsiaZoneIdToZoneMapper() }
    private val atlanticZoneIdToZoneMapper by lazy { AtlanticZoneIdToZoneMapper() }
    private val australiaZoneIdToZoneMapper by lazy { AustraliaZoneIdToZoneMapper() }
    private val brazilZoneIdToZoneMapper by lazy { BrazilZoneIdToZoneMapper() }
    private val canadaZoneIdToZoneMapper by lazy { CanadaZoneIdToZoneMapper() }
    private val chileZoneIdToZoneMapper by lazy { ChileZoneIdToZoneMapper() }
    private val europeZoneIdToZoneMapper by lazy { EuropeZoneIdToZoneMapper() }
    private val indianZoneIdToZoneMapper by lazy { IndianZoneIdToZoneMapper() }
    private val mexicoZoneIdToZoneMapper by lazy { MexicoZoneIdToZoneMapper() }
    private val pacificZoneIdToZoneMapper by lazy { PacificZoneIdToZoneMapper() }
    private val usZoneIdToZoneMapper by lazy { USZoneIdToZoneMapper() }

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
        Region.Africa -> africaZoneIdToZoneMapper(this)
        Region.America -> americaZoneIdToZoneMapper(this)
        Region.Antarctica -> antarcticaZoneIdToZoneMapper(this)
        Region.Arctic -> arcticZoneIdToZoneMapper(this)
        Region.Asia -> asiaZoneIdToZoneMapper(this)
        Region.Atlantic -> atlanticZoneIdToZoneMapper(this)
        Region.Australia -> australiaZoneIdToZoneMapper(this)
        Region.Brazil -> brazilZoneIdToZoneMapper(this)
        Region.Canada -> canadaZoneIdToZoneMapper(this)
        Region.Chile -> chileZoneIdToZoneMapper(this)
        Region.Europe -> europeZoneIdToZoneMapper(this)
        Region.Indian -> indianZoneIdToZoneMapper(this)
        Region.Mexico -> mexicoZoneIdToZoneMapper(this)
        Region.Pacific -> pacificZoneIdToZoneMapper(this)
        Region.US -> usZoneIdToZoneMapper(this)
    }

    fun search(zoneId: String?): Zone? {
        return zones.firstOrNull { it.zoneIdString == zoneId }
    }
}
