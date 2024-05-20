package ui.form.type

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import data.model.LocationType
import nav.Screens
import ui.common.observeEvent
import ui.component.RadioGroup
import ui.component.RadioRowData

@Composable
fun SelectLocationTypeScreen(
    navHostController: NavHostController,
    viewModel: SelectLocationTypeViewModel = viewModel { SelectLocationTypeViewModel() },
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    Column(
        Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            "Select Location Type",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.weight(1F))

        val options = LocationType.entries.map { locationType ->
            when (locationType) {
                LocationType.Person -> RadioRowData(
                    id = locationType.name,
                    "Person",
                    "Family members, friends or your pets",
                )
                LocationType.Country -> RadioRowData(
                    id = locationType.name,
                    "Country",
                    "Cities, counties or towns"
                )
                LocationType.Custom -> RadioRowData(
                    id = locationType.name,
                    "Custom",
                    "Office branch, Team names or anything you like"
                )
            }
        }
        val selectedLocaleIndex = options.indexOfFirst { it.id == viewModel.selectedLocationType.value?.name }
        RadioGroup(
            initialIndex = selectedLocaleIndex,
            options = options,
            onSelected = { _, id ->
                viewModel.onLocationTypeSelected(LocationType.valueOf(id))
            }
        )

        Spacer(Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.onContinueClicked() },
            enabled = viewModel.continueButtonEnabled.value
        ) {
            Text("Continue", modifier = Modifier.padding(vertical = 8.dp))
        }
    }

    LaunchedEffect(viewModel, lifecycleOwner) {
        viewModel.openSelectTimeRegionEvent.observeEvent(lifecycleOwner) {
            navHostController.navigate(Screens.FormSelectTimeRegion.route)
        }
    }
}