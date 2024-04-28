package ui.dashboard

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.subscribe
import data.model.TimeRegion
import data.timezone.TimescapeRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import ui.extensions.componentCoroutineScope

@OptIn(FlowPreview::class)
class DashboardViewModel(
    componentContext: ComponentContext,
    private val openFormSelectionScreen: () -> Unit
) : ComponentContext by componentContext {

    companion object {
        private const val DEBOUNCE_IN_MILLISECONDS = 500L
        private const val TIMEOUT_IN_MILLISECONDS = 5_000L
    }

    init {
        lifecycle.subscribe(
            onCreate = {
                if (_timeRegions.value.isEmpty()) {
                    _timeRegions.value = TimescapeRepository.timeRegions
                }
            },
        )
    }

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _searching = MutableStateFlow(false)
    val searching = _searching.asStateFlow()

    private val _timeRegions = MutableStateFlow(emptyList<TimeRegion>())
    val timeRegions = _query
        .debounce(DEBOUNCE_IN_MILLISECONDS)
        .onEach { _searching.update { true } }
        .combine(_timeRegions) { query, timeRegions ->
            if (query.isBlank()) {
                timeRegions
            } else {
                delay(DEBOUNCE_IN_MILLISECONDS)
                val trimmedQuery = query.replace("\\s+".toRegex(), "")
                timeRegions.filter { it.doesMatchSearchQuery(trimmedQuery) }
            }
        }
        .onEach { _searching.update { false } }
        .stateIn(
            componentCoroutineScope(),
            SharingStarted.WhileSubscribed(TIMEOUT_IN_MILLISECONDS),
            _timeRegions.value
        )

    fun onSearchQueryChange(query: String) {
        _query.value = query
    }

    fun onAddTimeRegionClicked() {
        openFormSelectionScreen()
    }
}