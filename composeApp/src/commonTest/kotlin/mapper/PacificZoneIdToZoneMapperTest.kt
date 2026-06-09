package mapper

import data.kairos.model.Region
import data.kairos.KairosRepository
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import repositoryModules
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class PacificZoneIdToZoneMapperTest : KoinTest {

    private val kairosRepository: KairosRepository by inject()

    private val zoneIds by lazy {
        kairosRepository.zones
            .filter { it.region == Region.Pacific }
            .map { it.timeZone.toString() }
    }

    @BeforeTest
    fun setup() {
        startKoin { modules(repositoryModules) }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `Given Pacific zoneIdStrings When mapping Then it should contain them`() {
        val actualZoneIdStrings = listOf(
            "Pacific/Auckland",
            "Pacific/Chatham",
            "Pacific/Honolulu",
            "Pacific/Pago_Pago",
            "Pacific/Apia",
            "Pacific/Fiji",
            "Pacific/Guam",
            "Pacific/Saipan",
            "Pacific/Port_Moresby",
            "Pacific/Noumea",
            "Pacific/Tahiti",
            "Pacific/Tongatapu",
            "Pacific/Efate",
            "Pacific/Guadalcanal",
            "Pacific/Rarotonga",
            "Pacific/Niue",
            "Pacific/Palau",
            "Pacific/Majuro",
            "Pacific/Tarawa",
            "Pacific/Nauru",
            "Pacific/Funafuti",
            "Pacific/Chuuk",
            "Pacific/Wallis",
            "Pacific/Fakaofo",
            "Pacific/Norfolk",
            "Pacific/Pitcairn",
            "Pacific/Easter",
            "Pacific/Galapagos"
        )
        assertTrue(zoneIds.containsAll(actualZoneIdStrings), "Missing: ${actualZoneIdStrings.minus(zoneIds.toSet())}")
    }
}
