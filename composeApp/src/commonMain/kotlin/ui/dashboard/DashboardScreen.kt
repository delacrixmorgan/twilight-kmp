package ui.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel
) {
    Column(Modifier.fillMaxSize()) {
        Text("Dashboard")

        Button(onClick = {
            viewModel.onEvent(DashboardEvent.CreateNew)
        }) {
            Text("Add")
        }
    }
}