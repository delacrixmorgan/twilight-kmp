package mapper

import data.model.Region
import data.timezone.TimeRegionRepository
import kotlin.test.Test
import kotlin.test.assertTrue

class AsiaZoneIdToTimezoneMapperTest {
    private val asiaZoneIds by lazy {
        TimeRegionRepository.timeRegions
            .filter { it.region == Region.Asia }
            .map { it.timeZone.toString() }
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
        assertTrue(asiaZoneIds.containsAll(actualZoneIdStrings), "Missing: ${actualZoneIdStrings.minus(asiaZoneIds.toSet())}")
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
        assertTrue(asiaZoneIds.containsAll(actualZoneIdStrings), "Missing: ${actualZoneIdStrings.minus(asiaZoneIds.toSet())}")
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
        assertTrue(asiaZoneIds.containsAll(actualZoneIdStrings), "Missing: ${actualZoneIdStrings.minus(asiaZoneIds.toSet())}")
    }
}