package ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import data.model.TimeRegion
import data.model.localTime
import ui.extensions.keyboardShownState
import ui.theme.Typography

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel
) {
    Column {
        val query by viewModel.query.collectAsState()
        val list by viewModel.timeRegions.collectAsState()
        val searching by viewModel.searching.collectAsState()
        val state = rememberLazyListState()
        if (searching) {
            Box(modifier = Modifier.fillMaxSize().weight(1F)) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1F),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                state = state
            ) {
                items(count = list.size, key = { list[it].timeZone.id }) { index ->
                    val timeRegion = list[index]
                    TimeRegionListRow(timeRegion)
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceContainer, shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .padding(16.dp),
        ) {
            val localFocusManager = LocalFocusManager.current
            if (!keyboardShownState().value) localFocusManager.clearFocus()

            TextField(
                modifier = Modifier.fillMaxWidth(),
                shape = CircleShape,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                ),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words, imeAction = ImeAction.Done),
                value = query,
                onValueChange = viewModel::onSearchQueryChange,
                placeholder = {
                    Text(text = "Kuala Lumpur, Malaysia")
                },
                leadingIcon = {
                    Icon(
                        Icons.Rounded.Search,
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        Icon(
                            modifier = Modifier.clickable { viewModel.onSearchQueryChange("") },
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = null
                        )
                    }
                },
            )
        }
    }
}

@Composable
private fun TimeRegionListRow(timeRegion: TimeRegion) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
    ) {
        Row(Modifier.fillMaxWidth()) {
            Column(Modifier.align(alignment = Alignment.CenterVertically), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = timeRegion.city,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = timeRegion.zone,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = Typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = timeRegion.localTime(),
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .align(alignment = Alignment.CenterVertically),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = Typography.labelLarge
            )
        }
    }
}