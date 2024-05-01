package ui.form.type

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import nav.Screens

@Composable
fun SelectLocationTypeScreen(
    navHostController: NavHostController,
    viewModel: SelectLocationTypeViewModel = viewModel { SelectLocationTypeViewModel() }
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
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navHostController.navigate(Screens.FormSetupName.route) }
        ) {
            Text("Continue", modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}