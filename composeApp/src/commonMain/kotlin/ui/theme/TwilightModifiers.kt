package ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object TwilightModifiers {
    val iconModifier = Modifier.padding(
        horizontal = 16.dp,
        vertical = 20.dp,
    )
    val startIconModifier = Modifier.padding(
        top = 20.dp,
        bottom = 20.dp,
        end = 16.dp,
    )
    val endIconModifier = Modifier.padding(
        top = 20.dp,
        bottom = 20.dp,
        start = 16.dp,
    )
}