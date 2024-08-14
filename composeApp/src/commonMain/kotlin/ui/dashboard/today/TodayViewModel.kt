package ui.dashboard.today

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import data.location.LocationRepository
import data.locationform.LocationFormRepository
import data.model.Location
import data.preferences.DateFormatPreference
import data.preferences.LocationFormatPreference
import data.preferences.PreferencesRepository
import data.timescape.TimescapeRepository
import data.utils.now
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import nav.Routes
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TodayViewModel : ViewModel(), KoinComponent {
    companion object {
        const val SCROLL_WHEEL_PAGE_SIZE = 300
    }

    private val preferences: PreferencesRepository by inject()
    private val repository: LocationRepository by inject()
    private val timescapeRepository: TimescapeRepository by inject()
    private val locationFormRepository: LocationFormRepository by inject()

    private var _state = MutableStateFlow(TodayUiState())
    val state: StateFlow<TodayUiState>
        get() = _state.asStateFlow()

    init {
        loadPreferences()
        loadLocations()
    }

    private fun loadPreferences() {
        viewModelScope.launch {
            launch {
                preferences.getDateFormat().collect { dateFormat ->
                    _state.update { it.copy(dateFormatPreference = dateFormat) }
                }
            }
            launch {
                preferences.getLocationFormat().collect { locationFormat ->
                    _state.update { it.copy(locationFormatPreference = locationFormat) }
                }
            }
        }
    }

    private fun loadLocations() {
        val currentTimeZone = TimeZone.currentSystemDefault()
        val currentTimeRegion = timescapeRepository.search(currentTimeZone.id)
        if (currentTimeRegion != null) {
            _state.update {
                it.copy(
                    localLocation = Location(
                        name = currentTimeRegion.city,
                        regionName = currentTimeRegion.city,
                        zoneId = currentTimeRegion.zoneIdString
                    )
                )
            }
        }
        viewModelScope.launch {
            repository.getLocations().collect { locations ->
                _state.update {
                    it.copy(
                        locations = locations.sortedBy { location ->
                            LocalDateTime.now(location.timeRegion).toInstant(TimeZone.UTC).epochSeconds
                        }
                    )
                }
            }
        }
    }

    fun onAction(navHostController: NavHostController, action: TodayAction) {
        when (action) {
            TodayAction.OpenCreateLocation -> {
                viewModelScope.launch {
                    locationFormRepository.clear()
                    navHostController.navigate(Routes.FormSelectTimeRegion)
                }
            }
            TodayAction.CloseEditLocationDialog -> {
                _state.update { it.copy(openEditLocationDialog = false) }

            }
            TodayAction.OnItemEditClicked -> {
                viewModelScope.launch {
                    state.value.selectedLocation?.let {
                        locationFormRepository.saveID(it.id)
                        locationFormRepository.saveName(it.name)
                        locationFormRepository.saveRegionName(it.regionName)
                        locationFormRepository.saveZoneId(it.zoneId)
                    }
                    _state.update {
                        it.copy(
                            openEditLocation = true,
                            openEditLocationDialog = false
                        )
                    }
                    navHostController.navigate(Routes.FormSelectTimeRegion)
                }
            }
            TodayAction.OnItemDeleteClicked -> {
                viewModelScope.launch {
                    state.value.selectedLocation?.id?.let { locationId ->
                        repository.deleteLocation(locationId)
                        _state.update {
                            it.copy(
                                selectedLocation = null,
                                openEditLocationDialog = false
                            )
                        }
                    }
                }
            }
            is TodayAction.OnSelectedLocation -> {
                _state.update {
                    it.copy(
                        selectedLocation = action.location,
                        openEditLocationDialog = true
                    )
                }
            }
            is TodayAction.OnScrollToTopClicked -> {
                _state.update { it.copy(scrollToTop = action.scroll) }
            }
            is TodayAction.OnTimeWheelScrolled -> {
                _state.update {
                    it.copy(
                        offsetInMinutes = action.offsetInMinutes,
                        formattedOffSetInMinutes = "+ ${formatOffSetInMinutes(action.offsetInMinutes)}",
                        isFirstItemVisible = state.value.isFirstItemVisible,
                    )
                }
            }
        }
    }

    private fun formatOffSetInMinutes(offSetMinutes: Int): String {
        val days = offSetMinutes / (24 * 60)
        val hours = (offSetMinutes % (24 * 60)) / 60
        val minutes = offSetMinutes % 60

        return buildString {
            if (days != 0) append("${days}d ")
            if (hours != 0) append("${hours}h ")
            append("${minutes}m")
        }
    }
}

data class TodayUiState(
    val localLocation: Location? = null,
    val locations: List<Location> = emptyList(),
    val selectedLocation: Location? = null,

    val offsetInMinutes: Int = 0,
    val formattedOffSetInMinutes: String = "",
    val isFirstItemVisible: Boolean = false,
    val dateFormatPreference: DateFormatPreference = DateFormatPreference.Default,
    val locationFormatPreference: LocationFormatPreference = LocationFormatPreference.Default,

    val openAddLocation: Boolean = false,
    val openEditLocation: Boolean = false,
    val openEditLocationDialog: Boolean = false,

    val scrollToTop: Boolean = false
)

sealed interface TodayAction {
    data object OpenCreateLocation : TodayAction
    data object CloseEditLocationDialog : TodayAction

    data object OnItemEditClicked : TodayAction
    data object OnItemDeleteClicked : TodayAction
    data class OnSelectedLocation(val location: Location) : TodayAction
    data class OnScrollToTopClicked(val scroll: Boolean) : TodayAction
    data class OnTimeWheelScrolled(val offsetInMinutes: Int, val isFirstItemVisible: Boolean) : TodayAction
}