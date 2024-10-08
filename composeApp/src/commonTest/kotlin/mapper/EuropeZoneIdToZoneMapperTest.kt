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

class EuropeZoneIdToZoneMapperTest : KoinTest {

    private val kairosRepository: KairosRepository by inject()

    private val zoneIds by lazy {
        kairosRepository.zones
            .filter { it.region == Region.Europe }
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
    fun `Given Europe zoneIdStrings When mapping Then it should contain them`() {
        val actualZoneIdStrings = listOf(
            "Europe/Andorra",
            "Europe/Vienna",
            "Europe/Brussels",
            "Europe/Sofia",
            "Europe/Zurich",
            "Europe/Prague",
            "Europe/Berlin",
            "Europe/Copenhagen",
            "Europe/Madrid",
            "Europe/Helsinki",
            "Europe/Paris",
            "Europe/London",
            "Europe/Dublin",
            "Europe/Isle_of_Man",
            "Europe/Athens",
            "Europe/Budapest",
            "Europe/Rome",
            "Europe/Jersey",
            "Europe/Vilnius",
            "Europe/Luxembourg",
            "Europe/Riga",
            "Europe/Monaco",
            "Europe/Chisinau",
            "Europe/Malta",
            "Europe/Amsterdam",
            "Europe/Oslo",
            "Europe/Warsaw",
            "Europe/Lisbon",
            "Europe/Bucharest",
            "Europe/San_Marino",
            "Europe/Stockholm",
            "Europe/Vatican",
            "Europe/Sarajevo",
            "Europe/Skopje",
            "Europe/Zagreb",
            "Europe/Tallinn",
            "Europe/Tirane",
            "Europe/Madrid",
            "Europe/Belgrade",
            "Europe/Bratislava",
            "Europe/Ljubljana",
            "Europe/Copenhagen"
        )
        assertTrue(zoneIds.containsAll(actualZoneIdStrings), "Missing: ${actualZoneIdStrings.minus(zoneIds.toSet())}")
    }
}