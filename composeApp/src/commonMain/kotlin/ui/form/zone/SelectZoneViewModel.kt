package ui.form.zone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import data.kairos.KairosRepository
import data.kairos.model.Zone
import data.locationform.LocationFormRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nav.Routes
import org.koin.core.component.KoinComponent

class SelectZoneViewModel(
    private val store: LocationFormRepository,
    private val kairosRepository: KairosRepository
) : ViewModel(), KoinComponent {
    companion object {
        private val popularZones = listOf(
            "Asia/Kuala_Lumpur",
            "Asia/Tokyo",
            "Asia/Kolkata",
            "Europe/Amsterdam",
            "Europe/London",
            "Europe/Kyiv",
            "Australia/Melbourne",
            "America/New_York",
            "America/Los_Angeles",
            "Pacific/Auckland",
        )
    }

    private var _state = MutableStateFlow(SelectZoneUiState())
    val state: StateFlow<SelectZoneUiState>
        get() = _state.asStateFlow()

    private val zones get() = kairosRepository.zones.sorted()
    private fun List<Zone>.sorted(): List<Zone> = sortedWith(
        compareBy(
            { it.zoneIdString != state.value.selectedZone?.zoneIdString },
            { it.zoneIdString !in popularZones },
        )
    )

    init {
        viewModelScope.launch {
            store.observeLocation().first().let { newLocationData ->
                val selectedZone = kairosRepository.search(newLocationData.zoneId)
                _state.update {
                    it.copy(
                        isEditMode = newLocationData.isEditMode,
                        selectedZone = selectedZone,
                        continueButtonEnabled = selectedZone != null,
                    )
                }
                _state.update {
                    it.copy(zones = kairosRepository.zones.sorted())
                }
            }
        }
    }

    fun onAction(navHostController: NavHostController, action: SelectZoneAction) {
        when (action) {
            is SelectZoneAction.OnSearchModeUpdated -> {
                _state.update { it.copy(searchMode = action.searchMode) }
            }
            is SelectZoneAction.OnQueryUpdated -> {
                _state.update {
                    it.copy(
                        query = action.query,
                        zones = if (action.query.isBlank()) {
                            zones.sorted()
                        } else {
                            val trimmedQuery = action.query.replace("\\s+".toRegex(), "")
                            zones.filter { region -> region.doesMatchSearchQuery(trimmedQuery) }
                        }
                    )
                }
            }
            is SelectZoneAction.OnZoneSelected -> {
                _state.update {
                    it.copy(
                        selectedZone = action.zone,
                        continueButtonEnabled = true
                    )
                }
            }
            SelectZoneAction.OnContinueClicked -> {
                viewModelScope.launch {
                    val selectedZone = state.value.selectedZone
                    if (selectedZone != null) {
                        store.saveZoneId(selectedZone.zoneIdString)
                        onAction(navHostController, SelectZoneAction.OpenSetupName)
                    }
                }
            }
            SelectZoneAction.OpenSetupName -> navHostController.navigate(Routes.FormSetupName)
            SelectZoneAction.OnBackClicked -> navHostController.navigateUp()
        }
    }
}

data class SelectZoneUiState(
    val query: String = "",
    val searching: Boolean = false,
    val searchMode: Boolean = false,
    val zones: List<Zone> = listOf(),
    val isEditMode: Boolean = false,
    val selectedZone: Zone? = null,
    val continueButtonEnabled: Boolean = false
)

sealed interface SelectZoneAction {
    data class OnSearchModeUpdated(val searchMode: Boolean) : SelectZoneAction
    data class OnQueryUpdated(val query: String) : SelectZoneAction
    data class OnZoneSelected(val zone: Zone) : SelectZoneAction
    data object OnContinueClicked : SelectZoneAction
    data object OpenSetupName : SelectZoneAction
    data object OnBackClicked : SelectZoneAction
}