package mapper

import data.model.Region
import data.timezone.TimescapeRepository
import kotlin.test.Test
import kotlin.test.assertTrue

class AmericaZoneIdToTimezoneMapperTest {
    private val zoneIds by lazy {
        TimescapeRepository.timeRegions
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