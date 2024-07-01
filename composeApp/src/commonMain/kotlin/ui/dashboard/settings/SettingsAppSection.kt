package ui.dashboard.settings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Feedback
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Policy
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.component.ListView
import ui.theme.TwilightModifiers

@Composable
internal fun AppInfo(modifier: Modifier) {
    val label = "App Info"
    ListView(
        modifier = modifier,
        label = { Text(label) },
        startIcon = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.Info,
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
internal fun PrivacyPolicy(modifier: Modifier) {
    val label = "Privacy Policy"
    ListView(
        modifier = modifier,
        label = { Text(label) },
        startIcon = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.Policy,
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
internal fun SendFeedback(modifier: Modifier) {
    val label = "Send Feedback"
    ListView(
        modifier = modifier,
        label = { Text(label) },
        startIcon = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.Feedback,
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
internal fun RateUs(modifier: Modifier) {
    val label = "Rate Us"

    ListView(
        modifier = modifier,
        label = { Text(label) },
        startIcon = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.ThumbUp,
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