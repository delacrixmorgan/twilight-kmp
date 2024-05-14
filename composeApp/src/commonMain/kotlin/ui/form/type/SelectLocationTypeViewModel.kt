package ui.form.type

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.createnewlocation.CreateNewLocationRepository
import data.model.LocationType
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ui.common.Event
import ui.common.triggerEvent

class SelectLocationTypeViewModel : ViewModel(), KoinComponent {

    private val repository: CreateNewLocationRepository by inject()

    val continueButtonEnabled = mutableStateOf(false)
    val openSetupNameEvent = MutableSharedFlow<Event<Unit>>()
    var selectedLocationType = mutableStateOf<LocationType?>(null)

    init {
        viewModelScope.launch {
            selectedLocationType.value = repository.getLocationType().first()
        }
    }

    fun onLocationTypeSelected(locationType: LocationType) {
        viewModelScope.launch {
            selectedLocationType.value = locationType
            repository.saveLocationType(locationType)
            continueButtonEnabled.value = true
        }
    }

    fun onContinueClicked() {
        viewModelScope.launch {
            openSetupNameEvent.triggerEvent()
        }
    }
}