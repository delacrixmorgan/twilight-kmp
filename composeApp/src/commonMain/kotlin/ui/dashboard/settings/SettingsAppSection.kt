package ui.dashboard.settings

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Feedback
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Policy
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.component.ListItem
import ui.theme.TwilightModifiers

@Composable
internal fun AppInfo(onClick: () -> Unit) {
    val label = "App Info"
    ListItem(
        modifier = Modifier.clickable { onClick() },
        label = label,
        startContent = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.Info,
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
internal fun PrivacyPolicy(onClick: () -> Unit) {
    val label = "Privacy Policy"
    ListItem(
        modifier = Modifier.clickable { onClick() },
        label = label,
        startContent = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.Policy,
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
internal fun SendFeedback(onClick: () -> Unit) {
    val label = "Send Feedback"
    ListItem(
        modifier = Modifier.clickable { onClick() },
        label = label,
        startContent = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.Feedback,
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
internal fun RateUs(onClick: () -> Unit) {
    val label = "Rate Us"

    ListItem(
        modifier = Modifier.clickable { onClick() },
        label = label,
        startContent = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.ThumbUp,
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