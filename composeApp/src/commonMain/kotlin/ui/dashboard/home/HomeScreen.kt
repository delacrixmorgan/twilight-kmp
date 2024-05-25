package ui.dashboard.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import nav.Screens
import ui.common.observeEvent
import ui.component.LocationListRow

@Composable
fun HomeScreen(
    modifier: Modifier,
    navHostController: NavHostController,
    viewModel: HomeViewModel = viewModel { HomeViewModel() },
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    Column(modifier.fillMaxSize().padding(horizontal = 16.dp)) {
        TopAppBar(viewModel)
        SummaryView(viewModel)
        LocationListView(viewModel)
    }

    LaunchedEffect(viewModel, lifecycleOwner) {
        viewModel.openFormEvent.observeEvent(lifecycleOwner) {
            navHostController.navigate(Screens.FormSelectLocationType.route)
        }
    }
}

@Composable
private fun TopAppBar(viewModel: HomeViewModel) {
    Spacer(Modifier.height(16.dp))
    Row {
        Button(onClick = {}) {
            Text("Today", modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp))
        }
        Spacer(Modifier.width(8.dp))
        OutlinedButton(onClick = {}) {
            Text("Settings", modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp))
        }
        Spacer(Modifier.weight(1F))
        FloatingActionButton(
            onClick = { viewModel.onAddLocationClicked() },
            shape = CircleShape
        ) {
            Icon(Icons.Rounded.Add, "Add")
        }
    }
}

@Composable
private fun SummaryView(viewModel: HomeViewModel) {
    Row {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            val currentTime by viewModel.currentTime.collectAsState()
            Text(
                text = currentTime,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium,
            )
        }
    }
}

@Composable
private fun LocationListView(viewModel: HomeViewModel) {
    val state = rememberLazyListState()
    val list by viewModel.locations.collectAsState()

    if (list.isNotEmpty()) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            state = state
        ) {
            items(count = list.size, key = { list[it].id }) { index ->
                val location = list[index]
                LocationListRow(location) {
                    viewModel.onLocationClicked(it)
                }
            }
        }
    } else {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Home")
        }
    }
}