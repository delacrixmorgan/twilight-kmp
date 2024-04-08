package data.model

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.char

object DateFormat {
    val twelfthHour = LocalDateTime.Format {
        amPmHour()
        char(':')
        minute()
        amPmMarker("AM", "PM")
    }
}