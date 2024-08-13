package ui.dashboard.today

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import data.model.DateFormat
import data.model.Location
import data.preferences.DateFormatPreference
import data.preferences.LocationFormatPreference
import data.utils.now
import kotlinx.coroutines.launch
import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.offsetIn
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import ui.component.VerticalScrollWheel

@Composable
fun TodayScreen(
    modifier: Modifier,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    state: TodayUiState,
    onAction: (TodayAction) -> Unit,
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = modifier.fillMaxSize()) {
        Row {
            Column(Modifier.weight(2F).padding(top = 16.dp)) {
                state.localLocation?.let { location ->
                    NameTimeView(
                        locationFormatPreference = state.locationFormatPreference,
                        dateFormatPreference = state.dateFormatPreference,
                        offsetInMinutes = state.offsetInMinutes,
                        location = location
                    )
                    HorizontalDivider(color = MaterialTheme.colorScheme.onSurface)
                }

                LazyColumn(state = rememberLazyListState()) {
                    items(count = state.locations.size, key = { state.locations[it].id }) { index ->
                        val location = state.locations[index]
                        EditableNameTimeView(
                            state = state,
                            location = location,
                            onClick = { onAction(TodayAction.OnSelectedLocation(it)) },
                        )
                    }
                }
                Spacer(Modifier.height(32.dp))
            }

            VerticalScrollWheel(
                modifier = Modifier.weight(1F),
                listState = listState,
                onScrolled = { offsetInMinutes, isFirstItemVisible ->
                    onAction(TodayAction.OnTimeWheelScrolled(offsetInMinutes, isFirstItemVisible))
                }
            )
        }

        Box(modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)) {
            FloatingActionButton(onClick = { onAction(TodayAction.OpenCreateLocation) }) {
                Icon(Icons.Rounded.Add, "Add")
            }
        }

        Box(modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 16.dp)) {
            AnimatedVisibility(
                visible = !state.isFirstItemVisible && state.offsetInMinutes != 0,
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
                    onClick = { onAction(TodayAction.OnScrollToTopClicked(scroll = true)) }
                ) {
                    Text(state.formattedOffSetInMinutes)
                    Spacer(Modifier.width(4.dp))
                    Icon(Icons.Rounded.ArrowUpward, "Up")
                }
            }
        }
    }

    EditLocationDialog(
        isVisible = state.openEditLocationDialog,
        location = state.selectedLocation,
        locationFormatPreference = state.locationFormatPreference,
        onDelete = { onAction(TodayAction.OnItemDeleteClicked) },
        onEdit = { onAction(TodayAction.OnItemEditClicked) },
        onDismiss = { onAction(TodayAction.CloseEditLocationDialog) }
    )

    LaunchedEffect(state, lifecycleOwner) {
        if (state.scrollToTop) {
            coroutineScope.launch {
                listState.animateScrollToItem(0)
                onAction(TodayAction.OnScrollToTopClicked(scroll = false))
            }
        }
    }
}

@Composable
internal fun EditLocationDialog(
    isVisible: Boolean,
    location: Location?,
    locationFormatPreference: LocationFormatPreference,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    onDismiss: () -> Unit
) {
    if (!isVisible) return
    val label = when (locationFormatPreference) {
        LocationFormatPreference.Place,
        LocationFormatPreference.PlaceWithGMT -> location?.regionName
        LocationFormatPreference.Person,
        LocationFormatPreference.PersonWithGMT -> location?.name
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
    state: TodayUiState,
    location: Location,
    onClick: ((Location) -> Unit)
) {
    NameTimeView(
        locationFormatPreference = state.locationFormatPreference,
        dateFormatPreference = state.dateFormatPreference,
        offsetInMinutes = state.offsetInMinutes,
        location = location,
        onClick = onClick
    )
}

@Composable
private fun NameTimeView(
    locationFormatPreference: LocationFormatPreference,
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
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = when (locationFormatPreference) {
                    LocationFormatPreference.Place,
                    LocationFormatPreference.PlaceWithGMT -> location.regionName
                    LocationFormatPreference.Person,
                    LocationFormatPreference.PersonWithGMT -> location.name
                },
                style = MaterialTheme.typography.titleLarge
            )
            if (locationFormatPreference == LocationFormatPreference.PlaceWithGMT || locationFormatPreference == LocationFormatPreference.PersonWithGMT) {
                val gmtOffset = adjustedTime.toInstant(location.timeRegion).offsetIn(location.timeRegion).toString()
                Text(
                    modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer, shape = RoundedCornerShape(8.dp)).padding(6.dp),
                    color = MaterialTheme.colorScheme.onSurface,
                    text = "GMT $gmtOffset",
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
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