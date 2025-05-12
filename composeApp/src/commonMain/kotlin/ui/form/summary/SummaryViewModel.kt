package ui.form.summary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import data.location.LocationRepository
import data.location.model.Location
import data.locationform.LocationFormRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nav.Routes
import org.koin.core.component.KoinComponent
import randomUUID

class SummaryViewModel(
    private val store: LocationFormRepository,
    private val locationRepository: LocationRepository
) : ViewModel(), KoinComponent {

    private var _state = MutableStateFlow(SummaryUiState())
    val state: StateFlow<SummaryUiState>
        get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            store.observeLocation().first().let { newLocationData ->
                _state.update {
                    it.copy(
                        isEditMode = newLocationData.isEditMode,
                        location = Location(
                            id = newLocationData.id ?: randomUUID(),
                            name = newLocationData.label ?: "",
                            regionName = newLocationData.regionName ?: "",
                            zoneId = newLocationData.zoneId ?: ""
                        )
                    )
                }
            }
        }
    }

    fun onAction(navHostController: NavHostController, action: SummaryAction) {
        when (action) {
            SummaryAction.OnSubmitClicked -> {
                viewModelScope.launch {
                    val location = state.value.location
                    if (location != null) {
                        if (state.value.isEditMode) {
                            locationRepository.updateLocation(location)
                        } else {
                            locationRepository.addLocation(location)
                        }
                    }
                    store.clear()
                    onAction(navHostController, SummaryAction.OpenDashboard)
                }
            }
            SummaryAction.OpenDashboard -> navHostController.popBackStack(Routes.Dashboard, inclusive = false)
            SummaryAction.OnBackClicked -> navHostController.navigateUp()
        }
    }
}

data class SummaryUiState(
    val title: String = "",
    val location: Location? = null,
    val isEditMode: Boolean = false,
)

sealed interface SummaryAction {
    data object OnSubmitClicked : SummaryAction
    data object OpenDashboard : SummaryAction
    data object OnBackClicked : SummaryAction
}