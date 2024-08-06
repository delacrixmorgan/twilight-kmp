package ui.dashboard.today

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import data.location.LocationRepository
import data.locationform.LocationFormRepository
import data.model.Location
import data.preferences.DateFormatPreference
import data.preferences.LocationTypePreference
import data.preferences.PreferencesRepository
import data.timescape.TimescapeRepository
import data.utils.now
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

    var state by mutableStateOf(TodayUiState())
        private set

    init {
        loadPreferences()
        loadLocations()
    }

    private fun loadPreferences() {
        viewModelScope.launch {
            launch { preferences.getDateFormat().collect { state = state.copy(dateFormatPreference = it) } }
            launch { preferences.getLocationType().collect { state = state.copy(locationTypePreference = it) } }
        }
    }

    private fun loadLocations() {
        val currentTimeZone = TimeZone.currentSystemDefault()
        val currentTimeRegion = timescapeRepository.search(currentTimeZone.id)
        currentTimeRegion?.let {
            state = state.copy(
                localLocation = Location(
                    name = it.city,
                    regionName = it.city,
                    zoneId = it.zoneIdString
                )
            )
        }
        viewModelScope.launch {
            repository.getLocations().collect { locations ->
                state = state.copy(
                    locations = locations.sortedBy {
                        LocalDateTime.now(it.timeRegion).toInstant(TimeZone.UTC).epochSeconds
                    }
                )
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
                state = state.copy(openEditLocationDialog = false)
            }
            TodayAction.OnItemEditClicked -> {
                viewModelScope.launch {
                    state.selectedLocation?.let {
                        locationFormRepository.saveID(it.id)
                        locationFormRepository.saveName(it.name)
                        locationFormRepository.saveRegionName(it.regionName)
                        locationFormRepository.saveZoneId(it.zoneId)
                    }
                    state = state.copy(
                        openEditLocation = true,
                        openEditLocationDialog = false
                    )
                    navHostController.navigate(Routes.FormSelectTimeRegion)
                }
            }
            TodayAction.OnItemDeleteClicked -> {
                viewModelScope.launch {
                    state.selectedLocation?.id?.let { locationId ->
                        repository.deleteLocation(locationId)
                        state = state.copy(
                            selectedLocation = null,
                            openEditLocationDialog = false
                        )
                    }
                }
            }
            is TodayAction.OnSelectedLocation -> {
                state = state.copy(
                    selectedLocation = action.location,
                    openEditLocationDialog = true
                )
            }
            is TodayAction.OnScrollToTopClicked -> {
                state = state.copy(scrollToTop = action.scroll)
            }
            is TodayAction.OnTimeWheelScrolled -> {
                state = state.copy(
                    offsetInMinutes = action.offsetInMinutes,
                    formattedOffSetInMinutes = "+ ${formatOffSetInMinutes(action.offsetInMinutes)}",
                    isFirstItemVisible = state.isFirstItemVisible,
                )
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
    val locations: List<Location> = emptyList(),
    val selectedLocation: Location? = null,
    val offsetInMinutes: Int = 0,
    val formattedOffSetInMinutes: String = "",
    val isFirstItemVisible: Boolean = false,
    val localLocation: Location? = null,
    val dateFormatPreference: DateFormatPreference = DateFormatPreference.Default,
    val locationTypePreference: LocationTypePreference = LocationTypePreference.Default,

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