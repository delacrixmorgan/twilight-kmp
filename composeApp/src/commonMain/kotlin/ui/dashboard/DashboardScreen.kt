package ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import data.model.localTime
import ui.theme.Typography

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Dashboard")

        val list = remember { viewModel.timezones }
        val state = rememberLazyListState()
        LazyColumn(
            contentPadding = PaddingValues(start = 16.dp, top = 72.dp, end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            state = state
        ) {
            items(count = list.size, key = { list[it].kotlinTimeZone.id }) { index ->
                val timezone = list[index]
                Row {
                    Column(Modifier.padding(vertical = 16.dp)) {
                        Text(
                            text = timezone.zone,
                            style = Typography.bodyLarge
                        )
                        Text(
                            text = timezone.city,
                            style = Typography.bodyMedium
                        )
                    }
                    Spacer(modifier = Modifier.weight(1F))
                    Text(
                        text = timezone.localTime(),
                        Modifier
                            .padding(vertical = 16.dp)
                            .align(alignment = Alignment.CenterVertically),
                        style = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
                Divider()
            }
        }

        Button(onClick = {
            viewModel.onAddTimezoneClicked()
        }) {
            Text("Add")
        }
    }
}

