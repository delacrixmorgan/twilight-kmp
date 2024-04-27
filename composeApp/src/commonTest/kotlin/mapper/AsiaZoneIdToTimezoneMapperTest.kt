package mapper

import data.model.Region
import data.timezone.TimeRegionRepository
import kotlin.test.Test
import kotlin.test.assertTrue

class AsiaZoneIdToTimezoneMapperTest {

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
        val asiaZoneIds = TimeRegionRepository.timeRegions
            .filter { it.region == Region.Asia }
            .map { it.timeZone.toString() }

        assertTrue(asiaZoneIds.containsAll(actualZoneIdStrings), "Mapping incorrect")
    }
}