package ui.form.summary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.TravelExplore
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import nav.Screens
import ui.component.LocationListRow
import ui.keyboardShownState
import ui.theme.DefaultColors

@Composable
fun SummaryScreen(
    navHostController: NavHostController,
    viewModel: SummaryViewModel = viewModel { SummaryViewModel() }
) {
    val localFocusManager = LocalFocusManager.current
    if (!keyboardShownState().value) localFocusManager.clearFocus()

    Column(
        Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            "Summary",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(16.dp))

        viewModel.locationState.value?.let {
            LocationListRow(it)
        }

        Spacer(modifier = Modifier.weight(1F))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.onCreateClicked()
                navHostController.popBackStack(Screens.Dashboard.route, inclusive = false)
            }
        ) {
            Text("Create", modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}