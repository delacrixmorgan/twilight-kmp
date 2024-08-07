package ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.distinctUntilChanged
import ui.dashboard.today.TodayViewModel.Companion.SCROLL_WHEEL_PAGE_SIZE

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VerticalScrollWheel(
    modifier: Modifier,
    listState: LazyListState,
    onScrolled: (Int, Boolean) -> Unit,
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
                onScrolled(content.firstVisibleIndex, content.isFirstItemVisible)
                if (content.reachedBottom) items.addAll((items.size..items.size + SCROLL_WHEEL_PAGE_SIZE))
            }
    }
}

private data class InfiniteListContent(
    val firstVisibleIndex: Int,
    val isFirstItemVisible: Boolean,
    val reachedBottom: Boolean,
)