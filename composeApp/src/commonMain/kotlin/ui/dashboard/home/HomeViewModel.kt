package ui.dashboard.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.location.LocationRepository
import data.model.Location
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : ViewModel(), KoinComponent {
    companion object {
        private const val TIMEOUT_IN_MILLISECONDS = 5_000L
    }

    private val repository: LocationRepository by inject()
    val locations: StateFlow<List<Location>> = repository.getLocations()
        .catch { emit(emptyList()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_IN_MILLISECONDS),
            initialValue = emptyList()
        )

    fun onLocationClicked(location: Location) {

    }
}