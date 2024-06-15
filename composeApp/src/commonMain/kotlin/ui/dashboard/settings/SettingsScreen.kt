package ui.dashboard.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = viewModel { SettingsViewModel() }
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Settings")

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