package ui.form.zone

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ui.component.ListItemRow
import ui.component.navigationIcon.NavigationBackIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectZoneScreen(
    state: SelectZoneUiState,
    onAction: (SelectZoneAction) -> Unit
) {
    val scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            if (state.searchMode) {
                SearchAppBar(state, onAction)
            } else {
                AppBar(scrollBehavior, state, onAction)
            }
        },
    ) { innerPadding ->
        val searching = state.searching
        if (searching) {
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        } else {
            LoadedSelectZoneScreen(innerPadding, state, onAction)
        }
    }
}

@Composable
private fun LoadedSelectZoneScreen(
    innerPadding: PaddingValues,
    state: SelectZoneUiState,
    onAction: (SelectZoneAction) -> Unit
) {
    val list = state.zones
    val lazyListState = rememberLazyListState()
    Box(Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(top = innerPadding.calculateTopPadding()),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            state = lazyListState
        ) {
            if (!state.searchMode) {
                item {
                    Text(
                        "Choose your time zone from the list below. Use the search bar to quickly find your specific time zone.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(Modifier.height(16.dp))
                }
            }
            items(count = list.size, key = { list[it].id }) { index ->
                val item = list[index]
                val selected = state.selectedZone?.zoneIdString == item.zoneIdString
                ListItemRow(
                    label = item.city,
                    description = item.zone,
                    endLabel = item.localTime(),
                    selected = selected,
                    onClicked = { onAction(SelectZoneAction.OnZoneSelected(item)) }
                )
            }
        }
        Column(
            Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .align(Alignment.BottomStart)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onAction(SelectZoneAction.OnContinueClicked) },
                enabled = state.continueButtonEnabled
            ) {
                Text("Continue", modifier = Modifier.padding(vertical = 8.dp))
            }
            Spacer(Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    state: SelectZoneUiState,
    onAction: (SelectZoneAction) -> Unit
) {
    MediumTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                if (state.isEditMode) "Edit your time zone" else "Select your time zone",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = { NavigationBackIcon { onAction(SelectZoneAction.OnBackClicked) } },
        actions = {
            IconButton(onClick = { onAction(SelectZoneAction.OnSearchModeUpdated(searchMode = true)) }) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchAppBar(
    state: SelectZoneUiState,
    onAction: (SelectZoneAction) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    TopAppBar(
        title = {
            TextField(
                modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words, imeAction = ImeAction.Done),
                value = state.query,
                onValueChange = { onAction(SelectZoneAction.OnQueryUpdated(it)) },
                placeholder = { Text(text = "Search") },
                trailingIcon = {
                    if (state.query.isNotEmpty()) {
                        Icon(
                            modifier = Modifier.clickable { onAction(SelectZoneAction.OnQueryUpdated("")) },
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = null
                        )
                    }
                },
            )
        },
        navigationIcon = {
            if (state.query.isNotBlank()) {
                NavigationBackIcon { onAction(SelectZoneAction.OnBackClicked) }
            } else {
                IconButton(onClick = { onAction(SelectZoneAction.OnSearchModeUpdated(searchMode = false)) }) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "Close"
                    )
                }
            }
        },
    )
    LaunchedEffect(state.searchMode) {
        if (state.searchMode) focusRequester.requestFocus()
    }
}