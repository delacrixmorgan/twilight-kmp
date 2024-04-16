import data.timezone.TimeRegionRepository
import kotlinx.datetime.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class TimeRegionRepositoryTest {
    private val netherlandsTimeRegion by lazy { TimeRegionRepository.filterTimeRegion("Amsterdam").first() }
    private val malaysiaTimeRegion by lazy { TimeRegionRepository.filterTimeRegion("Kuala Lumpur").first() }

    /**
     * Initialisation
     */
    @Test
    fun `Given time with DST When set to Malaysia Time Then return time`() {
        val expected = "2024-01-01T00:00"
        val localDateTime: LocalDateTime = LocalDateTime.parse(expected)
        val actualLocalDateTime: LocalDateTime = TimeRegionRepository.convert(
            fromLocalDateTime = localDateTime,
            from = malaysiaTimeRegion,
            to = malaysiaTimeRegion
        )

        assertEquals(
            expected = expected,
            actual = actualLocalDateTime.toString()
        )
    }

    /**
     * Netherlands to Malaysia Time
     */
    @Test
    fun `Given Netherlands Time with DST When convert to Malaysia Time Then return Malaysia Time`() {
        val netherlandsDST = "2024-01-01T00:00"
        val expected = "2024-01-01T07:00"
        val netherlandsDSTLocalDateTime: LocalDateTime = LocalDateTime.parse(netherlandsDST)
        val actualLocalDateTime: LocalDateTime = TimeRegionRepository.convert(
            fromLocalDateTime = netherlandsDSTLocalDateTime,
            from = netherlandsTimeRegion,
            to = malaysiaTimeRegion
        )

        assertEquals(
            expected = expected,
            actual = actualLocalDateTime.toString()
        )
    }

    @Test
    fun `Given Netherlands Time without DST When convert to Malaysia Time Then return Malaysia Time`() {
        val netherlandsDST = "2024-06-01T00:00"
        val expected = "2024-06-01T06:00"
        val netherlandsDSTLocalDateTime: LocalDateTime = LocalDateTime.parse(netherlandsDST)
        val actualLocalDateTime: LocalDateTime = TimeRegionRepository.convert(
            fromLocalDateTime = netherlandsDSTLocalDateTime,
            from = netherlandsTimeRegion,
            to = malaysiaTimeRegion,
        )

        assertEquals(
            expected = expected,
            actual = actualLocalDateTime.toString()
        )
    }

    /**
     * Malaysia to Netherlands Time
     */
    @Test
    fun `Given Malaysia Time When convert to Netherlands with DST Then return Netherlands DST`() {
        val malaysiaTime = "2024-01-01T07:00"
        val expected = "2024-01-01T00:00"
        val malaysiaLocalDateTime: LocalDateTime = LocalDateTime.parse(malaysiaTime)
        val actualLocalDateTime: LocalDateTime = TimeRegionRepository.convert(
            fromLocalDateTime = malaysiaLocalDateTime,
            from = malaysiaTimeRegion,
            to = netherlandsTimeRegion
        )

        assertEquals(
            expected = expected,
            actual = actualLocalDateTime.toString()
        )
    }

    @Test
    fun `Given Malaysia Time When convert to Netherlands without DST Then return Netherlands without DST`() {
        val malaysiaTime = "2024-06-01T06:00"
        val expected = "2024-06-01T00:00"
        val malaysiaLocalDateTime: LocalDateTime = LocalDateTime.parse(malaysiaTime)
        val actualLocalDateTime: LocalDateTime = TimeRegionRepository.convert(
            fromLocalDateTime = malaysiaLocalDateTime,
            from = malaysiaTimeRegion,
            to = netherlandsTimeRegion
        )

        assertEquals(
            expected = expected,
            actual = actualLocalDateTime.toString()
        )
    }
}