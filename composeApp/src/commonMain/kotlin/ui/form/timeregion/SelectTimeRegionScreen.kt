package ui.form.timeregion

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import nav.Screens
import ui.common.observeEvent
import ui.component.TimeRegionListRow
import ui.keyboardShownState

@Composable
fun SelectTimeRegionScreen(
    navHostController: NavHostController,
    viewModel: SelectTimeRegionViewModel = viewModel { SelectTimeRegionViewModel() },
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    Column {
        val query by viewModel.query.collectAsState()
        val list by viewModel.timeRegions.collectAsState()
        val searching by viewModel.searching.collectAsState()
        val state = rememberLazyListState()

        Text(
            "Select Time Region",
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )

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
                    TimeRegionListRow(timeRegion) { viewModel.onTimeRegionSelected(it) }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceContainer, shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .padding(16.dp)
        ) {
            val localFocusManager = LocalFocusManager.current
            if (!keyboardShownState().value) localFocusManager.clearFocus()

            Column {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent
                    ),
                    shape = CircleShape,
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words, imeAction = ImeAction.Done),
                    value = query,
                    onValueChange = viewModel::onSearchQueryChange,
                    placeholder = { Text(text = "Search") },
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

    LaunchedEffect(viewModel, lifecycleOwner) {
        viewModel.openSetupNameEvent.observeEvent(lifecycleOwner) {
            navHostController.navigate(Screens.FormSetupName.route)
        }
    }
}