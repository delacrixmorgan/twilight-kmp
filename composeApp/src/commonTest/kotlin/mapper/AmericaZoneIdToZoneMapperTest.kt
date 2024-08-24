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

class AmericaZoneIdToZoneMapperTest : KoinTest {

    private val kairosRepository: KairosRepository by inject()

    @BeforeTest
    fun setup() {
        startKoin { modules(repositoryModules) }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    private val zoneIds by lazy {
        kairosRepository.zones
            .filter { it.region == Region.America }
            .map { it.timeZone.toString() }
    }

    @Test
    fun `Given America zoneIdStrings When mapping Then it should contain them`() {
        val actualZoneIdStrings = listOf(
            "America/New_York",
        )
        assertTrue(zoneIds.containsAll(actualZoneIdStrings), "Missing: ${actualZoneIdStrings.minus(zoneIds.toSet())}")
    }
}