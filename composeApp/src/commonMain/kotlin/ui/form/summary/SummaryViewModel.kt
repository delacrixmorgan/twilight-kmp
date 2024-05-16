package ui.form.summary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.createnewlocation.CreateNewLocationRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SummaryViewModel : ViewModel(), KoinComponent {
    private val repository: CreateNewLocationRepository by inject()

    fun onCreateClicked() {
        repository.getLocationType()
        repository.getName()
        repository.getZoneId()

        viewModelScope.launch {
            repository.clear()
        }
    }
}