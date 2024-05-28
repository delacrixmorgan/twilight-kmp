package ui.dashboard.convert

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import data.model.DateFormat
import data.model.Location
import data.utils.now
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format

@Composable
fun ConvertScreen(
    modifier: Modifier,
    navHostController: NavHostController,
    viewModel: ConvertViewModel = viewModel { ConvertViewModel() },
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        val list by viewModel.locations.collectAsState()
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(32.dp),
            state = rememberLazyListState()
        ) {
            items(count = list.size, key = { list[it].id }) { index ->
                val location = list[index]
                NameTimeView(location)
            }
        }

        Spacer(Modifier.weight(1F))

        VerticalScrollWheel(modifier)
    }
}

@Composable
private fun NameTimeView(location: Location) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = location.regionName,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = LocalDateTime.now(location.timeRegion).format(DateFormat.twentyFourHour),
            style = MaterialTheme.typography.displayMedium
        )
    }
}

@Composable
private fun VerticalScrollWheel(modifier: Modifier) {
    val _list = MutableStateFlow(listOf<Int>())
    val list: StateFlow<List<Int>> = _list

    val items by list.collectAsState()
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier.then(Modifier.padding(horizontal = 16.dp)),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        state = listState
    ) {
        items(items) { index ->
            Text(
                text = (index + 1).toString(),
                textAlign = TextAlign.End
            )
        }
    }

    InfiniteListHandler(listState = listState) {
        _list.value += (_list.value.size.._list.value.size + 100)
    }
}

@Composable
fun InfiniteListHandler(
    listState: LazyListState,
    buffer: Int = 2,
    onLoadMore: () -> Unit
) {
    val reachedBottom: State<Boolean> = remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val totalItemsNumber = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1

            lastVisibleItemIndex > (totalItemsNumber - buffer)
        }
    }

    LaunchedEffect(reachedBottom) {
        snapshotFlow { reachedBottom.value }
            .distinctUntilChanged()
            .collect { onLoadMore() }
    }
}