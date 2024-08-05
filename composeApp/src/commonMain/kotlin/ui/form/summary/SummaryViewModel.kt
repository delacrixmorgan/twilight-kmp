package ui.form.summary

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.createnewlocation.CreateNewLocationRepository
import data.location.LocationRepository
import data.model.Location
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import randomUUID
import ui.common.Event
import ui.common.triggerEvent

class SummaryViewModel : ViewModel(), KoinComponent {
    private val store: CreateNewLocationRepository by inject()
    private val locationRepository: LocationRepository by inject()

    val isEditMode = mutableStateOf(false)
    val location = mutableStateOf<Location?>(null)
    val openDashboardEvent = MutableSharedFlow<Event<Unit>>()

    init {
        viewModelScope.launch {
            store.observeLocation().first().let {
                isEditMode.value = it.isEditMode
                location.value = Location(
                    id = it.id ?: randomUUID(),
                    name = it.label ?: "",
                    regionName = it.regionName ?: "",
                    zoneId = it.zoneId ?: ""
                )
            }
        }
    }

    fun onCreateClicked() {
        viewModelScope.launch {
            if (isEditMode.value) {
                locationRepository.updateLocation(requireNotNull(location.value))
            } else {
                locationRepository.addLocation(requireNotNull(location.value))
            }
            store.clear()
            openDashboardEvent.triggerEvent()
        }
    }
}