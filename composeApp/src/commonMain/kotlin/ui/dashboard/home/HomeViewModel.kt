package ui.dashboard.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.location.LocationRepository
import data.model.DateFormat
import data.model.Location
import data.utils.now
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.math.max

class HomeViewModel : ViewModel(), KoinComponent {
    companion object {
        private const val TIMEOUT_IN_MILLISECONDS = 5_000L
    }

    private val _currentTime = MutableStateFlow(getCurrentTime().format(DateFormat.twelfthHour))
    val currentTime: StateFlow<String> = _currentTime
    private var timeListenerJob: Job? = null

    private val repository: LocationRepository by inject()
    val locations: StateFlow<List<Location>> = repository.getLocations()
        .catch { emit(emptyList()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_IN_MILLISECONDS),
            initialValue = emptyList()
        )

    init {
        setupTimeListenerJob()
    }

    private fun setupTimeListenerJob() {
        if (timeListenerJob?.isActive == true) return
        timeListenerJob = viewModelScope.launch {
            val offSetSeconds = max((60 - LocalDateTime.now().second) * 1_000L, 0L)
            delay(offSetSeconds)
            _currentTime.value = getCurrentTime().format(DateFormat.twelfthHour)

            while (true) {
                delay(60_000)
                _currentTime.value = getCurrentTime().format(DateFormat.twelfthHour)
            }
        }
    }

    private fun getCurrentTime(): LocalDateTime {
        return LocalDateTime.now()
    }

    fun onLocationClicked(location: Location) {

    }
}