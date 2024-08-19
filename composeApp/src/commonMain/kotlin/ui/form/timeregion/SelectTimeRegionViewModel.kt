package ui.form.timeregion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import data.locationform.LocationFormRepository
import data.timescape.TimescapeRepository
import data.timescape.model.TimeRegion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nav.Routes
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SelectTimeRegionViewModel : ViewModel(), KoinComponent {
    companion object {
        private val favouriteTimeRegion = listOf(
            "Asia/Kuala_Lumpur",
            "Europe/Amsterdam",
            "Australia/Melbourne",
            "America/New_York",
        )
    }

    private val store: LocationFormRepository by inject()
    private val timescapeRepository: TimescapeRepository by inject()

    private var _state = MutableStateFlow(SelectTimeRegionUiState())
    val state: StateFlow<SelectTimeRegionUiState>
        get() = _state.asStateFlow()

    private val timeRegions get() = timescapeRepository.timeRegions.sorted()
    private fun List<TimeRegion>.sorted(): List<TimeRegion> = sortedWith(
        compareBy { it.zoneIdString !in favouriteTimeRegion }
    )

    init {
        viewModelScope.launch {
            store.observeLocation().first().let { newLocationData ->
                val selectedTimeRegion = timescapeRepository.search(newLocationData.zoneId)
                _state.update {
                    it.copy(
                        isEditMode = newLocationData.isEditMode,
                        selectedTimeRegion = selectedTimeRegion,
                        continueButtonEnabled = selectedTimeRegion != null,
                        timeRegions = timescapeRepository.timeRegions.sorted()
                    )
                }
            }
        }
    }

    fun onAction(navHostController: NavHostController, action: SelectTimeRegionAction) {
        when (action) {
            is SelectTimeRegionAction.OnSearchModeUpdated -> {
                _state.update { it.copy(searchMode = action.searchMode) }
            }
            is SelectTimeRegionAction.OnQueryUpdated -> {
                _state.update {
                    it.copy(
                        query = action.query,
                        timeRegions = if (action.query.isBlank()) {
                            timeRegions.sorted()
                        } else {
                            val trimmedQuery = action.query.replace("\\s+".toRegex(), "")
                            timeRegions.filter { region -> region.doesMatchSearchQuery(trimmedQuery) }
                        }
                    )
                }
            }
            is SelectTimeRegionAction.OnTimeRegionSelected -> {
                _state.update {
                    it.copy(
                        selectedTimeRegion = action.timeRegion,
                        continueButtonEnabled = true
                    )
                }
            }
            SelectTimeRegionAction.OnContinueClicked -> {
                viewModelScope.launch {
                    val selectedTimeRegion = state.value.selectedTimeRegion
                    if (selectedTimeRegion != null) {
                        store.saveZoneId(selectedTimeRegion.zoneIdString)
                        onAction(navHostController, SelectTimeRegionAction.OpenSetupName)
                    }
                }
            }
            SelectTimeRegionAction.OpenSetupName -> navHostController.navigate(Routes.FormSetupName)
            SelectTimeRegionAction.OnBackClicked -> navHostController.navigateUp()
        }
    }
}

data class SelectTimeRegionUiState(
    val query: String = "",
    val searching: Boolean = false,
    val searchMode: Boolean = false,
    val timeRegions: List<TimeRegion> = listOf(),
    val isEditMode: Boolean = false,
    val selectedTimeRegion: TimeRegion? = null,
    val continueButtonEnabled: Boolean = false
)

sealed interface SelectTimeRegionAction {
    data class OnSearchModeUpdated(val searchMode: Boolean) : SelectTimeRegionAction
    data class OnQueryUpdated(val query: String) : SelectTimeRegionAction
    data class OnTimeRegionSelected(val timeRegion: TimeRegion) : SelectTimeRegionAction
    data object OnContinueClicked : SelectTimeRegionAction
    data object OpenSetupName : SelectTimeRegionAction
    data object OnBackClicked : SelectTimeRegionAction
}