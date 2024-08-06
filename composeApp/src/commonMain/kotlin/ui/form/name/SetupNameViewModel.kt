package ui.form.name

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.createnewlocation.CreateNewLocationRepository
import data.timescape.TimescapeRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ui.common.Event
import ui.common.triggerEvent

class SetupNameViewModel : ViewModel(), KoinComponent {
    private val store: CreateNewLocationRepository by inject()
    private val timescapeRepository: TimescapeRepository by inject()

    var isEditMode = mutableStateOf(false)
    var locationName = mutableStateOf("")
    val regionName = mutableStateOf("")

    val continueButtonEnabled = mutableStateOf(false)
    val openSummaryEvent = MutableSharedFlow<Event<Unit>>()

    init {
        viewModelScope.launch {
            store.observeLocation().first().let {
                isEditMode.value = it.isEditMode
                locationName.value = it.label ?: ""
                regionName.value = (it.regionName ?: "").ifBlank { getFallbackRegionName() ?: "" }
                continueButtonEnabled.value = locationName.value.isNotBlank()
            }
        }
    }

    private suspend fun getFallbackRegionName(): String? {
        return timescapeRepository.search(requireNotNull(store.getZoneId().first()))?.city
    }

    fun onLocationNameUpdated(name: String) {
        locationName.value = name
        continueButtonEnabled.value = locationName.value.isNotBlank()
    }

    fun onRegionNameUpdated(name: String) {
        regionName.value = name
    }

    fun onContinueClicked() {
        viewModelScope.launch {
            store.saveName(locationName.value.trimEnd())
            store.saveRegionName(regionName.value.ifBlank { getFallbackRegionName() ?: "" })
            openSummaryEvent.triggerEvent()
        }
    }
}