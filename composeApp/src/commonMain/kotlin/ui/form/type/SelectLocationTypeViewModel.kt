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
    private val store: CreateNewLocationRepository by inject()

    var selectedLocationType = mutableStateOf<LocationType?>(null)
    val openSelectTimeRegionEvent = MutableSharedFlow<Event<Unit>>()
    val continueButtonEnabled = mutableStateOf(false)

    init {
        viewModelScope.launch {
            store.getLocationType().first()?.let {
                selectedLocationType.value = it
                continueButtonEnabled.value = true
            }
        }
    }

    fun onLocationTypeSelected(locationType: LocationType) {
        viewModelScope.launch {
            selectedLocationType.value = locationType
            store.saveLocationType(locationType)
            continueButtonEnabled.value = true
        }
    }

    fun onContinueClicked() {
        viewModelScope.launch {
            openSelectTimeRegionEvent.triggerEvent()
        }
    }
}