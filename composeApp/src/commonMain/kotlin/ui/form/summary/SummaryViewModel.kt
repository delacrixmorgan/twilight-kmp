package ui.form.summary

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.createnewlocation.CreateNewLocationRepository
import data.model.Location
import data.model.LocationType
import data.timeregion.TimescapeRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SummaryViewModel : ViewModel(), KoinComponent {
    private val store: CreateNewLocationRepository by inject()
    private val timescapeRepository: TimescapeRepository by inject()

    val locationState = mutableStateOf<Location?>(null)
    val customRegionName = mutableStateOf("")

    init {
        viewModelScope.launch {
            store.observeLocation().collect {
                locationState.value = Location(
                    id = "",
                    label = it.label ?: "",
                    customRegionName = customRegionName.value.ifBlank { timescapeRepository.search(it.zoneId)?.city ?: "" },
                    type = it.type ?: LocationType.Custom,
                    zoneId = it.zoneId ?: ""
                )
            }
        }
    }

    fun onCustomRegionName(name: String) {
        customRegionName.value = name
    }

    fun onCreateClicked() {
        viewModelScope.launch {
            store.clear()
        }
    }
}