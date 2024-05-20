package ui.form.summary

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.createnewlocation.CreateNewLocationRepository
import data.model.Location
import data.model.LocationType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SummaryViewModel : ViewModel(), KoinComponent {
    private val store: CreateNewLocationRepository by inject()
    val locationState = mutableStateOf<Location?>(null)

    init {
        viewModelScope.launch {
            store.observeLocation().first().let {
                locationState.value = Location(
                    id = "",
                    label = it.label ?: "",
                    customRegionName = it.customRegionName ?: "",
                    type = it.type ?: LocationType.Custom,
                    zoneId = it.zoneId ?: ""
                )
            }
        }
    }

    fun onCreateClicked() {
        viewModelScope.launch {
            store.clear()
        }
    }
}