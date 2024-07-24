package data.preferences

import data.model.DateFormat
import data.utils.now
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format

enum class DateFormatPreference {
    Twelve,
    TwentyFour;

    companion object {
        val Default = Twelve
    }

    val label: String
        get() = when (this) {
            Twelve -> "12-hour clock"
            TwentyFour -> "24-hour clock"
        }

    val description: String
        get() = when (this) {
            Twelve -> LocalDateTime.now().format(DateFormat.twelveHour)
            TwentyFour -> LocalDateTime.now().format(DateFormat.twentyFourHour)
        }
}