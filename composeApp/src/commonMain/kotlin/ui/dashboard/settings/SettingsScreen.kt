package ui.dashboard.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Badge
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Feedback
import androidx.compose.material.icons.rounded.FormatPaint
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Policy
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ui.component.ListView
import ui.component.ListViewGroup
import ui.theme.AppTypography
import ui.theme.TwilightModifiers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier,
    viewModel: SettingsViewModel = viewModel { SettingsViewModel() }
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Settings", style = AppTypography.headlineLarge) },
            )
        },
    ) { innerPadding ->
        Column(Modifier.fillMaxSize().padding(innerPadding).padding(16.dp)) {
            ListViewGroup(
                data = listOf(
                    { Theme(viewModel) },
                    { DateFormat(viewModel) },
                    { LocationType(viewModel) },
                ),
                divider = { HorizontalDivider() }
            )
            ListViewGroup(
                data = listOf(
                    { AppInfo(viewModel) },
                    { PrivacyPolicy(viewModel) },
                    { SendFeedback(viewModel) },
                    { RateUs(viewModel) },
                ),
                divider = { HorizontalDivider() }
            )
        }
    }
}

@Composable
private fun Theme(viewModel: SettingsViewModel) {
    val label = "Theme"
    ListView(
        modifier = Modifier.clickable { },
        startIcon = {
            Icon(
                modifier = TwilightModifiers.startIconModifier,
                imageVector = Icons.Rounded.FormatPaint,
                contentDescription = label
            )
        },
        label = { Text(label) }
    )
}

@Composable
private fun DateFormat(viewModel: SettingsViewModel) {
    val label = "Date Format"
    ListView(
        modifier = Modifier.clickable { },
        startIcon = {
            Icon(
                modifier = TwilightModifiers.startIconModifier,
                imageVector = Icons.Rounded.DateRange,
                contentDescription = label
            )
        },
        label = { Text(label) }
    )
}

@Composable
private fun LocationType(viewModel: SettingsViewModel) {
    val label = "Location Type"
    ListView(
        modifier = Modifier.clickable { },
        startIcon = {
            Icon(
                modifier = TwilightModifiers.startIconModifier,
                imageVector = Icons.Rounded.Badge,
                contentDescription = label
            )
        },
        label = { Text(label) }
    )
}

@Composable
private fun AppInfo(viewModel: SettingsViewModel) {
    val label = "App Info"
    ListView(
        modifier = Modifier.clickable { },
        startIcon = {
            Icon(
                modifier = TwilightModifiers.startIconModifier,
                imageVector = Icons.Rounded.Info,
                contentDescription = label
            )
        },
        label = { Text(label) }
    )
}

@Composable
private fun PrivacyPolicy(viewModel: SettingsViewModel) {
    val label = "Privacy Policy"
    ListView(
        modifier = Modifier.clickable { },
        startIcon = {
            Icon(
                modifier = TwilightModifiers.startIconModifier,
                imageVector = Icons.Rounded.Policy,
                contentDescription = label
            )
        },
        label = { Text(label) }
    )
}

@Composable
private fun SendFeedback(viewModel: SettingsViewModel) {
    val label = "Send Feedback"
    ListView(
        modifier = Modifier.clickable { },
        startIcon = {
            Icon(
                modifier = TwilightModifiers.startIconModifier,
                imageVector = Icons.Rounded.Feedback,
                contentDescription = label
            )
        },
        label = { Text(label) }
    )
}

@Composable
private fun RateUs(viewModel: SettingsViewModel) {
    val label = "Rate Us"
    ListView(
        modifier = Modifier.clickable { },
        startIcon = {
            Icon(
                modifier = TwilightModifiers.startIconModifier,
                imageVector = Icons.Rounded.ThumbUp,
                contentDescription = label
            )
        },
        label = { Text(label) }
    )
}


//        Box(modifier = modifier.align(Alignment.TopCenter).padding(top = 8.dp)) {
//            MultiChoiceSegmentedButtonRow {
//                SegmentedButton(
//                    checked = viewModel.selectedType.value == SegmentedButtonType.Place,
//                    onCheckedChange = { viewModel.selectedType.value = SegmentedButtonType.Place },
//                    shape = RoundedCornerShape(topStart = 100.dp, bottomStart = 100.dp),
//                    label = { Text("Place") },
//                    icon = { Icon(Icons.Rounded.Place, "Place") }
//                )
//
//                SegmentedButton(
//                    checked = viewModel.selectedType.value == SegmentedButtonType.Person,
//                    onCheckedChange = { viewModel.selectedType.value = SegmentedButtonType.Person },
//                    shape = RoundedCornerShape(topEnd = 100.dp, bottomEnd = 100.dp),
//                    label = { Text("Person") },
//                    icon = { Icon(Icons.Rounded.Person, "Person") }
//                )
//            }
//        }