package ui.dashboard.convert

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.Text
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
import nav.Screens
import ui.common.observeEvent
import ui.dashboard.convert.ConvertViewModel.Companion.SCROLL_WHEEL_PAGE_SIZE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConvertScreen(
    modifier: Modifier,
    navHostController: NavHostController,
    viewModel: ConvertViewModel = viewModel { ConvertViewModel() },
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row {
            Column(modifier.weight(2F).padding(top = 64.dp)) {
                viewModel.localLocation.value?.let { location ->
                    NameTimeView(viewModel, location)

                    Spacer(Modifier.height(16.dp))
                    HorizontalDivider(color = MaterialTheme.colorScheme.onSurface)
                }

                val list by viewModel.locations.collectAsState()
                LazyColumn(
                    modifier = Modifier.padding(vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                    state = rememberLazyListState()
                ) {
                    items(count = list.size, key = { list[it].id }) { index ->
                        val location = list[index]
                        NameTimeView(viewModel, location)
                    }
                }
                Spacer(Modifier.height(32.dp))
            }

            VerticalScrollWheel(modifier.weight(1F), viewModel)
        }

        Box(modifier = modifier.align(Alignment.BottomStart).padding(16.dp)) {
            FloatingActionButton(
                onClick = { viewModel.onAddLocationClicked() },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.onSecondaryContainer
            ) {
                Icon(Icons.Rounded.Add, "Add", tint = MaterialTheme.colorScheme.onSecondary)
            }
        }

        Box(modifier = modifier.align(Alignment.TopCenter).padding(top = 8.dp)) {
            MultiChoiceSegmentedButtonRow {
                SegmentedButton(
                    checked = viewModel.selectedType.value == SegmentedButtonType.Place,
                    onCheckedChange = { viewModel.selectedType.value = SegmentedButtonType.Place },
                    shape = RoundedCornerShape(topStart = 100.dp, bottomStart = 100.dp),
                    label = { Text("Place") },
                    icon = { Icon(Icons.Rounded.Place, "Place") }
                )

                SegmentedButton(
                    checked = viewModel.selectedType.value == SegmentedButtonType.Person,
                    onCheckedChange = { viewModel.selectedType.value = SegmentedButtonType.Person },
                    shape = RoundedCornerShape(topEnd = 100.dp, bottomEnd = 100.dp),
                    label = { Text("Person") },
                    icon = { Icon(Icons.Rounded.Person, "Person") }
                )
            }
        }

        Box(modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 32.dp)) {
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
                    onClick = { viewModel.onScrollToTopClicked() }
                ) {
                    Text("+ ${viewModel.formatOffSetInMinutes(viewModel.offsetInMinutes.value)}")
                    Spacer(Modifier.width(4.dp))
                    Icon(Icons.Rounded.ArrowUpward, "Up")
                }
            }
        }
    }

    LaunchedEffect(viewModel, lifecycleOwner) {
        viewModel.openFormEvent.observeEvent(lifecycleOwner) {
            navHostController.navigate(Screens.FormSelectLocationType.route)
        }
    }
}

@Composable
private fun NameTimeView(viewModel: ConvertViewModel, location: Location) {
    val label = when (viewModel.selectedType.value) {
        SegmentedButtonType.Place -> location.regionName
        SegmentedButtonType.Person -> location.label
    }
    val offsetMinutes = DateTimePeriod(minutes = viewModel.offsetInMinutes.value)
    val adjustedTime = LocalDateTime.now(location.timeRegion).toInstant(location.timeRegion).plus(offsetMinutes, TimeZone.UTC).toLocalDateTime(location.timeRegion)
    val hourMinuteTime = adjustedTime.format(DateFormat.twentyFourHour)
    val dateMonthTime = adjustedTime.format(DateFormat.dayOfWeekDayMonth)

    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = label,
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
    viewModel: ConvertViewModel,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    buffer: Int = 2,
) {
    val items = remember { mutableStateListOf<Int>() }
    val listState = rememberLazyListState()

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

    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(viewModel, lifecycleOwner) {
        viewModel.scrollToTopEvent.observeEvent(lifecycleOwner) {
            coroutineScope.launch {
                listState.animateScrollToItem(0)
            }
        }
    }
}

data class InfiniteListContent(
    val firstVisibleIndex: Int,
    val isFirstItemVisible: Boolean,
    val reachedBottom: Boolean,
)