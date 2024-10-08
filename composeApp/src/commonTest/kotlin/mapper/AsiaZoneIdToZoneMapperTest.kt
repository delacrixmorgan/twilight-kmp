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

class AsiaZoneIdToZoneMapperTest : KoinTest {

    private val kairosRepository: KairosRepository by inject()

    private val zoneIds by lazy {
        kairosRepository.zones
            .filter { it.region == Region.Asia }
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
    fun `Given South East Asia zoneIdStrings When mapping Then it should contain them`() {
        val actualZoneIdStrings = listOf(
            "Asia/Brunei",
            "Asia/Phnom_Penh",
            "Asia/Jakarta",
            "Asia/Makassar",
            "Asia/Jayapura",
            "Asia/Vientiane",
            "Asia/Kuala_Lumpur",
            "Asia/Kuching",
            "Asia/Yangon",
            "Asia/Manila",
            "Asia/Singapore",
            "Asia/Bangkok",
            "Asia/Dili",
            "Asia/Ho_Chi_Minh",
            "Asia/Saigon",
        )
        assertTrue(zoneIds.containsAll(actualZoneIdStrings), "Missing: ${actualZoneIdStrings.minus(zoneIds.toSet())}")
    }

    @Test
    fun `Given East Asia zoneIdStrings When mapping Then it should contain them`() {
        val actualZoneIdStrings = listOf(
            "Asia/Shanghai",
            "Asia/Urumqi",
            "Asia/Tokyo",
            "Asia/Seoul",
            "Asia/Pyongyang",
            "Asia/Ulaanbaatar",
            "Asia/Taipei"
        )
        assertTrue(zoneIds.containsAll(actualZoneIdStrings), "Missing: ${actualZoneIdStrings.minus(zoneIds.toSet())}")
    }

    @Test
    fun `Given South Asia zoneIdStrings When mapping Then it should contain them`() {
        val actualZoneIdStrings = listOf(
            "Asia/Kabul",
            "Asia/Dhaka",
            "Asia/Thimphu",
            "Asia/Kolkata",
            "Asia/Kathmandu",
            "Asia/Karachi",
            "Asia/Colombo"
        )
        assertTrue(zoneIds.containsAll(actualZoneIdStrings), "Missing: ${actualZoneIdStrings.minus(zoneIds.toSet())}")
    }
}