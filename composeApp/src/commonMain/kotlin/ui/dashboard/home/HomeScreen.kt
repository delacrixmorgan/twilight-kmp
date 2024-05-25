package ui.dashboard.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import data.model.DateFormat
import data.utils.now
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import ui.component.LocationListRow

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel { HomeViewModel() }
) {
    Column(Modifier.fillMaxSize()) {
        val state = rememberLazyListState()
        val list by viewModel.locations.collectAsState()

        Column(Modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            val currentTime by viewModel.currentTime.collectAsState()
            Text(
                text = currentTime,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium,
            )
        }

        if (list.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.weight(1F),
                contentPadding = PaddingValues(16.dp),
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
}