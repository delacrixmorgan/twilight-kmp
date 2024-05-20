package ui.form.name

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.createnewlocation.CreateNewLocationRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ui.common.Event
import ui.common.triggerEvent

class SetupNameViewModel : ViewModel(), KoinComponent {

    private val store: CreateNewLocationRepository by inject()

    val continueButtonEnabled = mutableStateOf(false)
    val openSelectTimeRegionEvent = MutableSharedFlow<Event<Unit>>()

    var locationName = mutableStateOf("")

    init {
        viewModelScope.launch {
            if (!store.getName().first().isNullOrBlank()) {
                locationName.value = store.getName().first() ?: ""
                continueButtonEnabled.value = true
            }
        }
    }

    fun onLocationNameUpdated(name: String) {
        locationName.value = name
        continueButtonEnabled.value = locationName.value.isNotEmpty()
    }

    fun onContinueClicked() {
        viewModelScope.launch {
            store.saveName(locationName.value)
            openSelectTimeRegionEvent.triggerEvent()
        }
    }
}