package data.model

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char

object DateFormat {
    // 04:19 PM
    val twelveHour = LocalDateTime.Format {
        amPmHour()
        char(':')
        minute()
        char(' ')
        amPmMarker("AM", "PM")
    }

    // 22:20
    val twentyFourHour = LocalDateTime.Format {
        hour()
        char(':')
        minute()
    }

    // Thursday, 24 July
    val dayOfWeekDayMonth = LocalDateTime.Format {
        dayOfWeek(DayOfWeekNames.ENGLISH_FULL)
        char(',')
        char(' ')
        dayOfMonth()
        char(' ')
        monthName(MonthNames.ENGLISH_FULL)
    }
}