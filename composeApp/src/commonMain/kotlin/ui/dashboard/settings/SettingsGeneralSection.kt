package ui.dashboard.settings

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Badge
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.FormatPaint
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.component.ListItem
import ui.theme.TwilightModifiers

@Composable
internal fun Theme(onClick: () -> Unit) {
    val label = "Theme"
    ListItem(
        modifier = Modifier.clickable { onClick() },
        label = label,
        startContent = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.FormatPaint,
                contentDescription = label
            )
        },
        endContent = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.ChevronRight,
                contentDescription = label
            )
        }
    )
}

@Composable
internal fun DateFormat(onClick: () -> Unit) {
    val label = "Date Format"
    ListItem(
        modifier = Modifier.clickable { onClick() },
        label = label,
        startContent = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.DateRange,
                contentDescription = label
            )
        },
        endContent = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.ChevronRight,
                contentDescription = label
            )
        }
    )
}

@Composable
internal fun LocationFormat(onClick: () -> Unit) {
    val label = "Location Format"
    ListItem(
        modifier = Modifier.clickable { onClick() },
        label = label,
        startContent = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.Badge,
                contentDescription = label
            )
        },
        endContent = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.ChevronRight,
                contentDescription = label
            )
        }
    )
}