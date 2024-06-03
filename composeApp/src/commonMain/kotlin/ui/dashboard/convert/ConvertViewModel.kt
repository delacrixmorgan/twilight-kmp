package ui.dashboard.convert

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.location.LocationRepository
import data.model.Location
import data.model.LocationType
import data.timescape.TimescapeRepository
import data.utils.now
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ui.common.Event
import ui.common.triggerEvent

class ConvertViewModel : ViewModel(), KoinComponent {
    companion object {
        const val SCROLL_WHEEL_PAGE_SIZE = 300
    }

    private val repository: LocationRepository by inject()
    private val timescapeRepository: TimescapeRepository by inject()

    private val _locations = MutableStateFlow<List<Location>>(emptyList())
    val locations: StateFlow<List<Location>>
        get() = _locations
    val localLocation = mutableStateOf<Location?>(null)

    val offsetInMinutes = mutableStateOf(0)
    val isFirstItemVisible = mutableStateOf(false)

    val scrollToTopEvent = MutableSharedFlow<Event<Unit>>()

    init {
        loadLocations()
    }

    private fun loadLocations() {
        val currentTimeZone = TimeZone.currentSystemDefault()
        val currentTimeRegion = timescapeRepository.search(currentTimeZone.id)
        currentTimeRegion?.let {
            localLocation.value = Location(
                label = it.city,
                regionName = it.city,
                type = LocationType.Country,
                zoneId = it.zoneIdString
            )
        }
        viewModelScope.launch {
            _locations.value = repository.getLocations().first().sortedBy {
                LocalDateTime.now(it.timeRegion).toInstant(TimeZone.UTC).epochSeconds
            }
        }
    }

    fun formatOffSetInMinutes(offSetMinutes: Int): String {
        val days = offSetMinutes / (24 * 60)
        val hours = (offSetMinutes % (24 * 60)) / 60
        val minutes = offSetMinutes % 60

        return buildString {
            if (days != 0) append("${days}d ")
            if (hours != 0) append("${hours}h ")
            append("${minutes}m")
        }
    }

    fun onScrollToTopClicked() {
        viewModelScope.launch {
            scrollToTopEvent.triggerEvent()
        }
    }
}