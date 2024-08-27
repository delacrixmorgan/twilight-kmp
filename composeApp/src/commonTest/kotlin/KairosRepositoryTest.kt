import data.kairos.KairosRepository
import data.utils.convert
import kotlinx.datetime.LocalDateTime
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class KairosRepositoryTest : KoinTest {

    private val kairosRepository: KairosRepository by inject()

    private val netherlandsZone by lazy { kairosRepository.search("Europe/Amsterdam")!! }
    private val malaysiaZone by lazy { kairosRepository.search("Asia/Kuala_Lumpur")!! }

    @BeforeTest
    fun setup() {
        startKoin { modules(repositoryModules) }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    /**
     * Initialisation
     */
    @Test
    fun `Given time with DST When set to Malaysia Time Then return time`() {
        val expected = "2024-01-01T00:00"
        val localDateTime: LocalDateTime = LocalDateTime.parse(expected)
        val actualLocalDateTime: LocalDateTime = localDateTime.convert(
            from = malaysiaZone,
            to = malaysiaZone
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
        val actualLocalDateTime: LocalDateTime = netherlandsDSTLocalDateTime.convert(
            from = netherlandsZone,
            to = malaysiaZone
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
        val actualLocalDateTime: LocalDateTime = netherlandsDSTLocalDateTime.convert(
            from = netherlandsZone,
            to = malaysiaZone,
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
        val actualLocalDateTime: LocalDateTime = malaysiaLocalDateTime.convert(
            from = malaysiaZone,
            to = netherlandsZone
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
        val actualLocalDateTime: LocalDateTime = malaysiaLocalDateTime.convert(
            from = malaysiaZone,
            to = netherlandsZone
        )

        assertEquals(
            expected = expected,
            actual = actualLocalDateTime.toString()
        )
    }
}