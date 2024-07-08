package ui.form.timeregion

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
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import nav.Routes
import ui.common.observeEvent
import ui.component.TimeRegionListRow
import ui.keyboardShownState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectTimeRegionScreen(
    navHostController: NavHostController,
    viewModel: SelectTimeRegionViewModel = viewModel { SelectTimeRegionViewModel() },
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    val scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val query by viewModel.query.collectAsState()
    val localFocusManager = LocalFocusManager.current
    if (!keyboardShownState().value) localFocusManager.clearFocus()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            if (viewModel.searchMode.value) {
                SearchAppBar(query, navHostController, viewModel)
            } else {
                AppBar(scrollBehavior, navHostController, viewModel)
            }
        },
        bottomBar = {
            Column(Modifier.padding(16.dp)) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { viewModel.onContinueClicked() },
                    enabled = viewModel.continueButtonEnabled.value
                ) {
                    Text("Continue", modifier = Modifier.padding(vertical = 8.dp))
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    ) { innerPadding ->
        val list by viewModel.timeRegions.collectAsState()
        val searching by viewModel.searching.collectAsState()
        val state = rememberLazyListState()

        if (searching) {
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                state = state
            ) {
                if (!viewModel.searchMode.value) {
                    item {
                        Text(
                            "Choose your time zone from the list below. Use the search bar to quickly find your specific time zone.",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
                items(count = list.size, key = { list[it].id }) { index ->
                    val item = list[index]
                    TimeRegionListRow(
                        timeRegion = item,
                        selected = viewModel.selectedTimeRegion.value?.zoneIdString == item.zoneIdString
                    ) { viewModel.onTimeRegionSelected(it) }
                }
            }
        }
    }

    LaunchedEffect(viewModel, lifecycleOwner) {
        viewModel.openSetupNameEvent.observeEvent(lifecycleOwner) {
            navHostController.navigate(Routes.FormSetupName)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    navHostController: NavHostController,
    viewModel: SelectTimeRegionViewModel,
) {
    MediumTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                "Select your time zone",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = { navHostController.navigateUp() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Go back"
                )
            }
        },
        actions = {
            IconButton(onClick = { viewModel.searchMode.value = true }) {
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
    query: String,
    navHostController: NavHostController,
    viewModel: SelectTimeRegionViewModel,
) {
    TopAppBar(
        title = {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words, imeAction = ImeAction.Done),
                value = query,
                onValueChange = viewModel::onSearchQueryChange,
                placeholder = { Text(text = "Search") },
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
        },
        navigationIcon = {
            if (query.isNotBlank()) {
                IconButton(onClick = { navHostController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "Go back"
                    )
                }
            } else {
                IconButton(onClick = { viewModel.searchMode.value = false }) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "Close"
                    )
                }
            }
        },
    )
}