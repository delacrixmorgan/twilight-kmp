package ui.dashboard.today

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.createnewlocation.CreateNewLocationRepository
import data.location.LocationRepository
import data.model.Location
import data.preferences.DateFormatPreference
import data.preferences.LocationTypePreference
import data.preferences.PreferencesRepository
import data.timescape.TimescapeRepository
import data.utils.now
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TodayViewModel : ViewModel(), KoinComponent {
    companion object {
        const val SCROLL_WHEEL_PAGE_SIZE = 300
    }

    private val preferences: PreferencesRepository by inject()
    private val repository: LocationRepository by inject()
    private val timescapeRepository: TimescapeRepository by inject()
    private val createNewLocationRepository: CreateNewLocationRepository by inject()

    private val _locations = MutableStateFlow<List<Location>>(emptyList())
    val locations: StateFlow<List<Location>>
        get() = _locations
    val localLocation = mutableStateOf<Location?>(null)

    val offsetInMinutes = mutableStateOf(0)
    val isFirstItemVisible = mutableStateOf(false)
    val selectedLocation = mutableStateOf<Location?>(null)

    val dateFormatPreference = mutableStateOf(DateFormatPreference.Default)
    val locationTypePreference = mutableStateOf(LocationTypePreference.Default)

    private val _uiState = MutableStateFlow(TodayUiState())
    val uiState: StateFlow<TodayUiState> = _uiState

    init {
        loadPreferences()
        loadLocations()
    }

    private fun loadPreferences() {
        viewModelScope.launch {
            launch { preferences.getDateFormat().collect { dateFormatPreference.value = it } }
            launch { preferences.getLocationType().collect { locationTypePreference.value = it } }
        }
    }

    private fun loadLocations() {
        val currentTimeZone = TimeZone.currentSystemDefault()
        val currentTimeRegion = timescapeRepository.search(currentTimeZone.id)
        currentTimeRegion?.let {
            localLocation.value = Location(
                name = it.city,
                regionName = it.city,
                zoneId = it.zoneIdString
            )
        }
        viewModelScope.launch {
            repository.getLocations().collect {
                _locations.value = it.sortedBy {
                    LocalDateTime.now(it.timeRegion).toInstant(TimeZone.UTC).epochSeconds
                }
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

    fun onScrollToTopClicked(click: Boolean) {
        _uiState.update { it.copy(scrollToTop = click) }
    }

    fun onAddLocationClicked(open: Boolean) {
        viewModelScope.launch {
            createNewLocationRepository.clear()
            _uiState.update { it.copy(openAddLocation = open) }
        }
    }

    fun onItemClicked(click: Boolean) {
        _uiState.update { it.copy(openItemSettings = click) }
    }

    fun onItemDeleteClicked() {
        viewModelScope.launch {
            selectedLocation.value?.id?.let { locationId ->
                repository.deleteLocation(locationId)
                selectedLocation.value = null
                _uiState.update { it.copy(openItemSettings = false) }
            }
        }
    }
}

data class TodayUiState(
    val openAddLocation: Boolean = false,
    val openItemSettings: Boolean = false,

    val scrollToTop: Boolean = false
)