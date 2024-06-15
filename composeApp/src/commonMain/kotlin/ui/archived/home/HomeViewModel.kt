package ui.archived.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.createnewlocation.CreateNewLocationRepository
import data.location.LocationRepository
import data.model.Location
import data.utils.now
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ui.common.Event
import ui.common.triggerEvent
import kotlin.math.max

class HomeViewModel : ViewModel(), KoinComponent {
    companion object {
        private const val TIMEOUT_IN_MILLISECONDS = 5_000L
    }

    private val store: CreateNewLocationRepository by inject()
    private val repository: LocationRepository by inject()
    private var timeListenerJob: Job? = null
    private val _currentTime = MutableStateFlow(LocalDateTime.now())
    
    val currentTime: StateFlow<LocalDateTime> = _currentTime
    val locations: StateFlow<List<Location>> = repository.getLocations()
        .map { locations ->
            locations.sortedBy {
                LocalDateTime.now(it.timeRegion).toInstant(TimeZone.UTC).epochSeconds
            }
        }
        .catch { emit(emptyList()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_IN_MILLISECONDS),
            initialValue = emptyList()
        )
    val openFormEvent = MutableSharedFlow<Event<Unit>>()

    init {
        setupTimeListenerJob()
    }

    private fun setupTimeListenerJob() {
        if (timeListenerJob?.isActive == true) return
        _currentTime.value = LocalDateTime.now()

        timeListenerJob = viewModelScope.launch {
            val offSetSeconds = max((60 - LocalDateTime.now().second) * 1_000L, 0L)
            delay(offSetSeconds)
            _currentTime.value = LocalDateTime.now()

            while (true) {
                delay(60_000)
                _currentTime.value = LocalDateTime.now()
            }
        }
    }

    fun onAddLocationClicked() {
        viewModelScope.launch {
            store.clear()
            openFormEvent.triggerEvent()
        }
    }

    fun onLocationClicked(location: Location) {

    }
}