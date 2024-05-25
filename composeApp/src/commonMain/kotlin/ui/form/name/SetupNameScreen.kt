package ui.form.name

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.TravelExplore
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import nav.Screens
import ui.common.observeEvent
import ui.component.Header
import ui.theme.DefaultColors

@Composable
fun SetupNameScreen(
    modifier: Modifier,
    navHostController: NavHostController,
    viewModel: SetupNameViewModel = viewModel { SetupNameViewModel() },
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    Column(modifier.fillMaxSize().padding(16.dp)) {
        Header(
            title = "Setup name",
            description = "Enter a name and customise your region name if you'd like.",
        )

        Spacer(modifier = Modifier.weight(1F))

        Text(
            "Name",
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            colors = DefaultColors.textFieldDefaultColors(),
            shape = CircleShape,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words, imeAction = ImeAction.Done),
            value = viewModel.locationName.value,
            onValueChange = { viewModel.onLocationNameUpdated(it) },
            placeholder = { Text("Name") },
            leadingIcon = {
                Icon(
                    Icons.Rounded.Create,
                    contentDescription = null
                )
            },
        )

        Spacer(Modifier.height(16.dp))

        Text(
            "Region Name",
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            colors = DefaultColors.textFieldDefaultColors(),
            shape = CircleShape,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words, imeAction = ImeAction.Done),
            value = viewModel.regionName.value,
            onValueChange = { viewModel.onRegionNameUpdated(it) },
            placeholder = { Text("Region Name") },
            leadingIcon = {
                Icon(
                    Icons.Rounded.TravelExplore,
                    contentDescription = null
                )
            },
        )

        Spacer(Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.onContinueClicked() },
            enabled = viewModel.continueButtonEnabled.value
        ) {
            Text("Continue", modifier = Modifier.padding(vertical = 8.dp))
        }

        LaunchedEffect(viewModel, lifecycleOwner) {
            viewModel.openSummaryEvent.observeEvent(lifecycleOwner) {
                navHostController.navigate(Screens.FormSummary.route)
            }
        }
    }
}