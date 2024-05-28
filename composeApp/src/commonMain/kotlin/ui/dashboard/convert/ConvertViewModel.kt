package ui.dashboard.convert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.location.LocationRepository
import data.model.Location
import data.model.LocationType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ConvertViewModel : ViewModel(), KoinComponent {
    private val repository: LocationRepository by inject()

    private val _locations = MutableStateFlow<List<Location>>(emptyList())
    val locations: StateFlow<List<Location>>
        get() = _locations

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
            _locations.value = listOf(currentLocation) + repository.getLocations().first()
        }
    }
}