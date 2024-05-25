package data.model

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char

object DateFormat {
    val dayOfWeek = LocalDateTime.Format {
        dayOfWeek(DayOfWeekNames.ENGLISH_FULL)
    }
    val date = LocalDateTime.Format {
        dayOfMonth()
        char(' ')
        monthName(MonthNames.ENGLISH_FULL)
    }
    val twelfthHour = LocalDateTime.Format {
        amPmHour()
        char(':')
        minute()
        char(' ')
        amPmMarker("AM", "PM")
    }
}