package data.preferences

import data.model.DateFormat
import data.utils.now
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format

enum class DateFormatPreference {
    TwentyFour,
    Twelve;

    companion object {
        val Default = TwentyFour
    }

    val label: String
        get() = when (this) {
            TwentyFour -> "24-hour clock"
            Twelve -> "12-hour clock"
        }

    val description: String
        get() = when (this) {
            TwentyFour -> LocalDateTime.now().format(DateFormat.twentyFourHour)
            Twelve -> LocalDateTime.now().format(DateFormat.twelveHour)
        }
}