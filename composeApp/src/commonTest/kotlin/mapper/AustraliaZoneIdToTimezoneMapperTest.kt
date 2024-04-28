package mapper

import data.model.Region
import data.timezone.TimescapeRepository
import kotlin.test.Test
import kotlin.test.assertTrue

class AustraliaZoneIdToTimezoneMapperTest {
    private val zoneIds by lazy {
        TimescapeRepository.timeRegions
            .filter { it.region == Region.Australia }
            .map { it.timeZone.toString() }
    }

    @Test
    fun `Given Australia zoneIdStrings When mapping Then it should contain them`() {
        val actualZoneIdStrings = listOf(
            "Australia/Adelaide",
            "Australia/Brisbane",
            "Australia/Broken_Hill",
            "Australia/Darwin",
            "Australia/Hobart",
            "Australia/Lord_Howe",
            "Australia/Melbourne",
            "Australia/Perth",
            "Australia/Sydney"
        )
        assertTrue(zoneIds.containsAll(actualZoneIdStrings), "Missing: ${actualZoneIdStrings.minus(zoneIds.toSet())}")
    }
}