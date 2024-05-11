package ui.form.name

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ui.common.Event
import ui.common.triggerEvent

class SetupNameViewModel : ViewModel() {

    val continueButtonEnabled = mutableStateOf(false)
    val openSelectTimeRegionEvent = MutableSharedFlow<Event<Unit>>()

    var locationName = mutableStateOf("")

    fun onLocationNameUpdated(name: String) {
        locationName.value = name
        continueButtonEnabled.value = locationName.value.isNotEmpty()
    }

    fun onContinueClicked() {
        viewModelScope.launch {
            openSelectTimeRegionEvent.triggerEvent()
        }
    }
}