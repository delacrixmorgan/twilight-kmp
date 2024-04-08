package ui.dashboard

import com.arkivanov.decompose.ComponentContext
import data.timezone.TimezoneRepository

class DashboardViewModel(
    componentContext: ComponentContext,
    private val openFormSelectionScreen: () -> Unit
) : ComponentContext by componentContext {

    val timezones get() = TimezoneRepository.timezones

    fun onAddTimezoneClicked() {
        openFormSelectionScreen()
    }
}