package ui.form.name

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import data.kairos.KairosRepository
import data.locationform.LocationFormRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nav.Routes
import org.koin.core.component.KoinComponent

class SetupNameViewModel(
    private val store: LocationFormRepository,
    private val kairosRepository: KairosRepository
) : ViewModel(), KoinComponent {

    private var _state = MutableStateFlow(SetupNameUiState())
    val state: StateFlow<SetupNameUiState>
        get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            store.observeLocation().first().let { location ->
                _state.update {
                    it.copy(
                        locationName = location.label ?: "",
                        regionName = (location.regionName ?: "").ifBlank { getFallbackRegionName() ?: "" },
                        isEditMode = location.isEditMode,
                        continueButtonEnabled = state.value.locationName.isNotBlank()
                    )
                }
            }
        }
    }

    private suspend fun getFallbackRegionName(): String? {
        return kairosRepository.search(requireNotNull(store.getZoneId().first()))?.city
    }

    fun onAction(navHostController: NavHostController, action: SetupNameAction) {
        when (action) {
            is SetupNameAction.OnLocationNameUpdated -> {
                _state.update {
                    it.copy(
                        locationName = action.name,
                        continueButtonEnabled = action.name.isNotBlank()
                    )
                }
            }
            is SetupNameAction.OnRegionNameUpdated -> {
                _state.update { it.copy(regionName = action.name) }
            }
            SetupNameAction.OnContinueClicked -> {
                viewModelScope.launch {
                    store.saveName(state.value.locationName.trimEnd())
                    store.saveRegionName(state.value.regionName.ifBlank { getFallbackRegionName() ?: "" })
                    onAction(navHostController, SetupNameAction.OpenSummary)
                }
            }
            SetupNameAction.OpenSummary -> navHostController.navigate(Routes.FormSummary)
            SetupNameAction.OnBackClicked -> navHostController.navigateUp()
        }
    }
}

data class SetupNameUiState(
    var locationName: String = "",
    val regionName: String = "",
    val isEditMode: Boolean = false,
    val continueButtonEnabled: Boolean = false
)

sealed interface SetupNameAction {
    data class OnLocationNameUpdated(val name: String) : SetupNameAction
    data class OnRegionNameUpdated(val name: String) : SetupNameAction
    data object OnContinueClicked : SetupNameAction
    data object OpenSummary : SetupNameAction
    data object OnBackClicked : SetupNameAction
}