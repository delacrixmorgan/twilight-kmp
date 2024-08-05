package ui.dashboard.today

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberSwipeToDismissBoxState
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
import androidx.compose.ui.graphics.Color
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
                        EditableNameTimeView(viewModel, location,
                            onEdit = {
                                viewModel.selectedLocation.value = it
                                viewModel.onEditSwiped(swiped = true)
                            },
                            onDelete = {
                                viewModel.selectedLocation.value = it
                                viewModel.onDeleteSwiped(swiped = true)
                            })
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
    ItemSettingsBottomSheet(
        isVisible = uiState.openDeleteConfirmation,
        location = viewModel.selectedLocation.value,
        locationTypePreference = viewModel.locationTypePreference.value,
        onDelete = { viewModel.onItemDeleteClicked() },
        onDismiss = { viewModel.onDeleteSwiped(swiped = false) },
    )

    LaunchedEffect(uiState, lifecycleOwner) {
        if (uiState.openAddLocation) {
            navHostController.navigate(Routes.FormSelectTimeRegion)
            viewModel.onAddLocationClicked(open = false)
        }
        if (uiState.openEditLocation) {
            navHostController.navigate(Routes.FormSelectTimeRegion)
            viewModel.onEditSwiped(swiped = false)
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
internal fun ItemSettingsBottomSheet(
    isVisible: Boolean,
    location: Location?,
    locationTypePreference: LocationTypePreference,
    onDelete: () -> Unit,
    onDismiss: () -> Unit
) {
    if (!isVisible) return
    val label = when (locationTypePreference) {
        LocationTypePreference.Place -> location?.regionName
        LocationTypePreference.Person -> location?.name
    }
    AlertDialog(
        icon = { Icon(Icons.Rounded.Delete, contentDescription = "Delete") },
        title = { Text(text = "Delete Location") },
        text = { Text(text = "Are you sure you want to delete the location \"$label\"?") },
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = { onDelete() }) {
                Text("Delete", color = MaterialTheme.colorScheme.error)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) { Text("Cancel") }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditableNameTimeView(
    viewModel: TodayViewModel,
    location: Location,
    onEdit: ((Location) -> Unit),
    onDelete: ((Location) -> Unit)
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { state ->
            when (state) {
                SwipeToDismissBoxValue.StartToEnd -> onEdit(location)
                SwipeToDismissBoxValue.EndToStart -> onDelete(location)
                SwipeToDismissBoxValue.Settled -> Unit
            }
            false
        }
    )
    val color = when (dismissState.dismissDirection) {
        SwipeToDismissBoxValue.StartToEnd -> MaterialTheme.colorScheme.tertiary
        SwipeToDismissBoxValue.EndToStart -> MaterialTheme.colorScheme.error
        SwipeToDismissBoxValue.Settled -> Color.Transparent
    }
    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    Icons.Rounded.Edit,
                    contentDescription = "Edit"
                )
                Spacer(modifier = Modifier)
                Icon(
                    Icons.Rounded.Delete,
                    contentDescription = "Delete"
                )
            }
        },
    ) {
        NameTimeView(
            locationTypePreference = viewModel.locationTypePreference.value,
            dateFormatPreference = viewModel.dateFormatPreference.value,
            offsetInMinutes = viewModel.offsetInMinutes.value,
            location = location
        )
    }
}

@Composable
private fun NameTimeView(
    locationTypePreference: LocationTypePreference,
    dateFormatPreference: DateFormatPreference,
    offsetInMinutes: Int,
    location: Location,
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
        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background).padding(16.dp),
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