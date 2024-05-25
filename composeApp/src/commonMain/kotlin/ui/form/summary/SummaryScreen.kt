package ui.form.summary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import nav.Screens
import ui.common.observeEvent
import ui.component.Header
import ui.component.LocationListRow
import ui.keyboardShownState

@Composable
fun SummaryScreen(
    modifier: Modifier,
    navHostController: NavHostController,
    viewModel: SummaryViewModel = viewModel { SummaryViewModel() },
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    val localFocusManager = LocalFocusManager.current
    if (!keyboardShownState().value) localFocusManager.clearFocus()

    Column(modifier.fillMaxSize().padding(16.dp)) {
        Header(
            title = "Summary",
            description = "Here is what it will look like in your dashboard. You can still edit it later.",
        )

        Spacer(modifier = Modifier.weight(1F))

        viewModel.location.value?.let {
            LocationListRow(it)
        }

        Spacer(Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.onCreateClicked() }
        ) {
            Text("Create", modifier = Modifier.padding(vertical = 8.dp))
        }

        LaunchedEffect(viewModel, lifecycleOwner) {
            viewModel.openDashboardEvent.observeEvent(lifecycleOwner) {
                navHostController.popBackStack(Screens.Dashboard.route, inclusive = false)
            }
        }
    }
}