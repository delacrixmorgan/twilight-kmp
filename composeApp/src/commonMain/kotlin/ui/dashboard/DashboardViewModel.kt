package ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.createnewlocation.CreateNewLocationRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ui.common.Event
import ui.common.triggerEvent

class DashboardViewModel : ViewModel(), KoinComponent {
    private val store: CreateNewLocationRepository by inject()
    val openFormEvent = MutableSharedFlow<Event<Unit>>()

    fun onAddLocationClicked() {
        viewModelScope.launch {
            store.clear()
            openFormEvent.triggerEvent()
        }
    }
}