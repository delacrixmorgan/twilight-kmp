package ui.dashboard.settings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Badge
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.FormatPaint
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.component.ListItem
import ui.theme.TwilightModifiers

@Composable
internal fun Theme(modifier: Modifier) {
    val label = "Theme"
    ListItem(
        modifier = modifier,
        label = { Text(label) },
        startIcon = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.FormatPaint,
                contentDescription = label
            )
        },
        endIcon = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.ChevronRight,
                contentDescription = label
            )
        }
    )
}

@Composable
internal fun DateFormat(modifier: Modifier) {
    val label = "Date Format"
    ListItem(
        modifier = modifier,
        label = { Text(label) },
        startIcon = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.DateRange,
                contentDescription = label
            )
        },
        endIcon = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.ChevronRight,
                contentDescription = label
            )
        }
    )
}

@Composable
internal fun LocationType(modifier: Modifier) {
    val label = "Location Type"
    ListItem(
        modifier = modifier,
        label = { Text(label) },
        startIcon = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.Badge,
                contentDescription = label
            )
        },
        endIcon = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.ChevronRight,
                contentDescription = label
            )
        }
    )
}