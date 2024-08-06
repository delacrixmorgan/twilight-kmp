package ui.dashboard.today

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import data.model.DateFormat
import data.model.Location
import data.preferences.DateFormatPreference
import data.preferences.LocationTypePreference
import data.utils.now
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import nav.Routes
import ui.dashboard.today.TodayViewModel.Companion.SCROLL_WHEEL_PAGE_SIZE

@Composable
fun TodayScreen(
    modifier: Modifier,
    navHostController: NavHostController,
    viewModel: TodayViewModel = viewModel { TodayViewModel() },
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = modifier.fillMaxSize()) {
        Row {
            Column(Modifier.weight(2F).padding(top = 16.dp)) {
                viewModel.localLocation.value?.let { location ->
                    NameTimeView(
                        locationTypePreference = viewModel.locationTypePreference.value,
                        dateFormatPreference = viewModel.dateFormatPreference.value,
                        offsetInMinutes = viewModel.offsetInMinutes.value,
                        location = location
                    )
                    HorizontalDivider(color = MaterialTheme.colorScheme.onSurface)
                }

                val list by viewModel.locations.collectAsState()
                LazyColumn(state = rememberLazyListState()) {
                    items(count = list.size, key = { list[it].id }) { index ->
                        val location = list[index]
                        EditableNameTimeView(
                            viewModel, location,
                            onClick = {
                                viewModel.selectedLocation.value = it
                                viewModel.openEditLocationDialog(open = true)
                            },
                        )
                    }
                }
                Spacer(Modifier.height(32.dp))
            }

            VerticalScrollWheel(Modifier.weight(1F), viewModel, listState)
        }

        Box(modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)) {
            FloatingActionButton(onClick = { viewModel.onAddLocationClicked(open = true) }) {
                Icon(Icons.Rounded.Add, "Add")
            }
        }

        Box(modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 16.dp)) {
            AnimatedVisibility(
                visible = !viewModel.isFirstItemVisible.value && viewModel.offsetInMinutes.value != 0,
                enter = slideInVertically(
                    initialOffsetY = { it / 2 },
                ),
                exit = slideOutVertically(
                    targetOffsetY = { it / 2 },
                ),
            ) {
                Button(
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        top = 4.dp,
                        end = 12.dp,
                        bottom = 4.dp,
                    ),
                    onClick = { viewModel.onScrollToTopClicked(click = true) }
                ) {
                    Text("+ ${viewModel.formatOffSetInMinutes(viewModel.offsetInMinutes.value)}")
                    Spacer(Modifier.width(4.dp))
                    Icon(Icons.Rounded.ArrowUpward, "Up")
                }
            }
        }
    }

    val uiState by viewModel.uiState.collectAsState()
    EditLocationDialog(
        isVisible = uiState.openEditLocationDialog,
        location = viewModel.selectedLocation.value,
        locationTypePreference = viewModel.locationTypePreference.value,
        onDelete = { viewModel.onItemDeleteClicked() },
        onEdit = { viewModel.onItemEditClicked() },
        onDismiss = { viewModel.openEditLocationDialog(open = false) }
    )

    LaunchedEffect(uiState, lifecycleOwner) {
        if (uiState.openAddLocation) {
            navHostController.navigate(Routes.FormSelectTimeRegion)
            viewModel.onAddLocationClicked(open = false)
        }
        if (uiState.openEditLocation) {
            navHostController.navigate(Routes.FormSelectTimeRegion)
            viewModel.openEditLocation(open = false)
        }
        if (uiState.scrollToTop) {
            coroutineScope.launch {
                listState.animateScrollToItem(0)
                viewModel.onScrollToTopClicked(click = false)
            }
        }
    }
}

@Composable
internal fun EditLocationDialog(
    isVisible: Boolean,
    location: Location?,
    locationTypePreference: LocationTypePreference,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    onDismiss: () -> Unit
) {
    if (!isVisible) return
    val label = when (locationTypePreference) {
        LocationTypePreference.Place -> location?.regionName
        LocationTypePreference.Person -> location?.name
    }
    AlertDialog(
        icon = { Icon(Icons.Rounded.Edit, contentDescription = "Edit") },
        title = { Text(text = "Change Location") },
        text = { Text(text = "What would you like to do to location \"$label\"?") },
        confirmButton = {
            TextButton(onClick = { onDelete() }) { Text("Delete", color = MaterialTheme.colorScheme.error) }
        },
        dismissButton = {
            TextButton(onClick = { onEdit() }) { Text("Edit") }
        },
        onDismissRequest = { onDismiss() },
    )
}

@Composable
private fun EditableNameTimeView(
    viewModel: TodayViewModel,
    location: Location,
    onClick: ((Location) -> Unit)
) {
    NameTimeView(
        locationTypePreference = viewModel.locationTypePreference.value,
        dateFormatPreference = viewModel.dateFormatPreference.value,
        offsetInMinutes = viewModel.offsetInMinutes.value,
        location = location,
        onClick = onClick
    )
}

@Composable
private fun NameTimeView(
    locationTypePreference: LocationTypePreference,
    dateFormatPreference: DateFormatPreference,
    offsetInMinutes: Int,
    location: Location,
    onClick: ((Location) -> Unit)? = null,
) {
    val offsetMinutes = DateTimePeriod(minutes = offsetInMinutes)
    val adjustedTime = LocalDateTime.now(location.timeRegion).toInstant(location.timeRegion).plus(offsetMinutes, TimeZone.UTC).toLocalDateTime(location.timeRegion)
    val hourMinuteTime = adjustedTime.format(
        when (dateFormatPreference) {
            DateFormatPreference.Twelve -> DateFormat.twelveHour
            DateFormatPreference.TwentyFour -> DateFormat.twentyFourHour
        }
    )
    val dateMonthTime = adjustedTime.format(DateFormat.dayOfWeekDayMonth)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .then(if (onClick != null) Modifier.clickable { onClick(location) } else Modifier)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = when (locationTypePreference) {
                LocationTypePreference.Place -> location.regionName
                LocationTypePreference.Person -> location.name
            },
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = dateMonthTime,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = hourMinuteTime,
            style = MaterialTheme.typography.displayMedium
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun VerticalScrollWheel(
    modifier: Modifier,
    viewModel: TodayViewModel,
    listState: LazyListState,
    buffer: Int = 2,
) {
    val items = remember { mutableStateListOf<Int>() }

    LazyColumn(
        modifier = modifier.fillMaxSize().padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.End,
        state = listState,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
    ) {
        items(items) { index ->
            val width = if (index % 3 == 0) 32.dp else 16.dp
            HorizontalDivider(
                Modifier.width(width),
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }

    val listStateListener: State<InfiniteListContent> = remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val totalItemsNumber = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1

            InfiniteListContent(
                firstVisibleIndex = listState.firstVisibleItemIndex,
                isFirstItemVisible = listState.firstVisibleItemIndex == 0,
                reachedBottom = lastVisibleItemIndex > (totalItemsNumber - buffer)
            )
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listStateListener.value }
            .distinctUntilChanged()
            .collect { content ->
                viewModel.offsetInMinutes.value = content.firstVisibleIndex
                viewModel.isFirstItemVisible.value = content.isFirstItemVisible
                if (content.reachedBottom) items.addAll((items.size..items.size + SCROLL_WHEEL_PAGE_SIZE))
            }
    }
}

data class InfiniteListContent(
    val firstVisibleIndex: Int,
    val isFirstItemVisible: Boolean,
    val reachedBottom: Boolean,
)