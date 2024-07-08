package ui.archived.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import data.model.DateFormat
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import ui.common.observeEvent
import ui.component.LocationCard

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = viewModel { HomeViewModel() },
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    val currentTime by viewModel.currentTime.collectAsState()
    Column(Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
        TopAppBar(viewModel)
        SummaryView(viewModel, currentTime)
        LocationListView(viewModel)
    }

    LaunchedEffect(viewModel, lifecycleOwner) {
        viewModel.openFormEvent.observeEvent(lifecycleOwner) {
//            navHostController.navigate(Screens.FormSelectLocationType.route)
        }
    }
}

@Composable
private fun TopAppBar(viewModel: HomeViewModel) {
    Column(Modifier.padding(vertical = 8.dp)) {
        Row {
            Button(onClick = {}) {
                Text("Today", modifier = Modifier.padding(4.dp))
            }
            Spacer(Modifier.width(8.dp))
            OutlinedButton(onClick = {}) {
                Text("Settings", modifier = Modifier.padding(4.dp))
            }
            Spacer(Modifier.weight(1F))
            FloatingActionButton(
                modifier = Modifier.size(48.dp),
                onClick = { viewModel.onAddLocationClicked() },
                shape = CircleShape
            ) {
                Icon(Icons.Rounded.Add, "Add")
            }
        }
    }
}

@Composable
private fun SummaryView(viewModel: HomeViewModel, currentTime: LocalDateTime) {
    Column(Modifier.padding(vertical = 8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1F), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = currentTime.format(DateFormat.dayOfWeek),
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = currentTime.format(DateFormat.date),
                    style = MaterialTheme.typography.displayMedium,
                )
            }
            Box(modifier = Modifier.weight(1F).fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                Text(
                    text = currentTime.format(DateFormat.twelfthHour),
                    style = MaterialTheme.typography.headlineMedium,
                )
            }
        }
    }
}

@Composable
private fun LocationListView(viewModel: HomeViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainer, shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            .padding(16.dp)
    ) {
        val state = rememberLazyListState()
        val list by viewModel.locations.collectAsState()
        if (list.isNotEmpty()) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                state = state
            ) {
                items(count = list.size, key = { list[it].id }) { index ->
                    val location = list[index]
                    LocationCard(location) {
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
}