package ui.dashboard.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Badge
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.FormatPaint
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.model.TwilightTheme
import ui.component.ListItem
import ui.component.RadioGroup
import ui.component.RadioRowData
import ui.theme.AppTypography
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ThemeBottomSheet(isVisible: Boolean, onDismiss: () -> Unit) {
    if (!isVisible) return
    val modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = modalBottomSheetState,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(
                "Theme",
                modifier = Modifier.padding(horizontal = 16.dp),
                style = AppTypography.titleLarge
            )
            RadioGroup(
                options = TwilightTheme.entries.map {
                    RadioRowData(id = it.name, label = it.name)
                },
                onSelected = { index, id ->
                    TwilightTheme.entries[index]
                }
            )

            Button(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                onClick = { onDismiss() },
            ) {
                Text("Done", modifier = Modifier.padding(vertical = 8.dp))
            }
            Spacer(Modifier.height(16.dp))
        }
    }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DateFormatBottomSheet(isVisible: Boolean, onDismiss: () -> Unit) {
    if (!isVisible) return
    val modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = modalBottomSheetState,
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text("Date Format", style = AppTypography.titleLarge)
        }
    }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LocationTypeBottomSheet(isVisible: Boolean, onDismiss: () -> Unit) {
    if (!isVisible) return
    val modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = modalBottomSheetState,
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text("Location Type", style = AppTypography.titleLarge)
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
    }
}