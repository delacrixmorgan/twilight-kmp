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

class IndianZoneIdToZoneMapperTest : KoinTest {

    private val kairosRepository: KairosRepository by inject()

    private val zoneIds by lazy {
        kairosRepository.zones
            .filter { it.region == Region.Indian }
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
    fun `Given Indian zoneIdStrings When mapping Then it should contain them`() {
        val actualZoneIdStrings = listOf(
            "Indian/Antananarivo",
            "Indian/Chagos",
            "Indian/Christmas",
            "Indian/Cocos",
            "Indian/Comoro",
            "Indian/Kerguelen",
            "Indian/Mahe",
            "Indian/Maldives",
            "Indian/Mauritius",
            "Indian/Mayotte",
            "Indian/Reunion"
        )
        assertTrue(zoneIds.containsAll(actualZoneIdStrings), "Missing: ${actualZoneIdStrings.minus(zoneIds.toSet())}")
    }
}
