package ui.form.timeregion

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.createnewlocation.CreateNewLocationRepository
import data.model.TimeRegion
import data.timescape.TimescapeRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ui.common.Event
import ui.common.triggerEvent

@OptIn(FlowPreview::class)
class SelectTimeRegionViewModel : ViewModel(), KoinComponent {
    companion object {
        private const val DEBOUNCE_IN_MILLISECONDS = 500L
        private const val TIMEOUT_IN_MILLISECONDS = 5_000L
    }

    private val store: CreateNewLocationRepository by inject()
    private val timescapeRepository: TimescapeRepository by inject()

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _searching = MutableStateFlow(false)
    val searching = _searching.asStateFlow()
    val searchMode = mutableStateOf(false)

    private val favouriteTimeRegion = listOf(
        "Asia/Kuala_Lumpur",
        "Europe/Amsterdam",
        "Australia/Melbourne",
        "America/New_York",
    )

    private val _timeRegions = MutableStateFlow(timescapeRepository.timeRegions.sorted())
    val timeRegions = _query
        .debounce(DEBOUNCE_IN_MILLISECONDS)
        .onEach { _searching.update { true } }
        .combine(_timeRegions) { query, timeRegions ->
            if (query.isBlank()) {
                timeRegions.sorted()
            } else {
                delay(DEBOUNCE_IN_MILLISECONDS)
                val trimmedQuery = query.replace("\\s+".toRegex(), "")
                timeRegions.filter { it.doesMatchSearchQuery(trimmedQuery) }
            }
        }
        .onEach { _searching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(TIMEOUT_IN_MILLISECONDS),
            _timeRegions.value
        )

    val selectedTimeRegion = mutableStateOf<TimeRegion?>(null)
    val openSetupNameEvent = MutableSharedFlow<Event<Unit>>()
    val continueButtonEnabled = mutableStateOf(false)

    init {
        viewModelScope.launch {
            store.getZoneId().first()?.let {
                selectedTimeRegion.value = timescapeRepository.search(it)
                continueButtonEnabled.value = true
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _query.value = query
    }

    fun onTimeRegionSelected(timeRegion: TimeRegion) {
        this.selectedTimeRegion.value = timeRegion
        continueButtonEnabled.value = true
    }

    fun onContinueClicked() {
        viewModelScope.launch {
            store.saveZoneId(requireNotNull(selectedTimeRegion.value?.zoneIdString))
            openSetupNameEvent.triggerEvent()
        }
    }

    private fun List<TimeRegion>.sorted(): List<TimeRegion> =
        sortedWith(compareBy { it.zoneIdString !in favouriteTimeRegion })
}