package ui.dashboard.convert

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.location.LocationRepository
import data.model.Location
import data.model.LocationType
import data.utils.now
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ConvertViewModel : ViewModel(), KoinComponent {
    private val repository: LocationRepository by inject()

    private val _locations = MutableStateFlow<List<Location>>(emptyList())
    val locations: StateFlow<List<Location>>
        get() = _locations

    val offsetInMinutes = mutableStateOf(0)

    init {
        loadLocations()
    }

    private fun loadLocations() {
        viewModelScope.launch {
            val currentLocation = Location(
                label = "Morgan",
                regionName = "Amsterdam",
                type = LocationType.Person,
                zoneId = "Europe/Amsterdam"
            )
            val sortedLocations = listOf(currentLocation) + repository.getLocations().first().sortedBy {
                LocalDateTime.now(it.timeRegion).toInstant(TimeZone.UTC).epochSeconds
            }
            _locations.value = sortedLocations
        }
    }
}