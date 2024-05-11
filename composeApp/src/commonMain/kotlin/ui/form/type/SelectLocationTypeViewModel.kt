package ui.form.type

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.model.LocationType
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ui.common.Event
import ui.common.triggerEvent

class SelectLocationTypeViewModel : ViewModel() {

    val continueButtonEnabled = mutableStateOf(false)
    val openSetupNameEvent = MutableSharedFlow<Event<Unit>>()

    var selectedLocationType = mutableStateOf<LocationType?>(null)

    fun onLocationTypeSelected(locationType: LocationType) {
        this.selectedLocationType.value = locationType
        continueButtonEnabled.value = true
    }

    fun onContinueClicked() {
        viewModelScope.launch {
            openSetupNameEvent.triggerEvent()
        }
    }
}