package ui.dashboard

import com.arkivanov.decompose.ComponentContext
import data.timezone.TimeRegionRepository

class DashboardViewModel(
    componentContext: ComponentContext,
    private val openFormSelectionScreen: () -> Unit
) : ComponentContext by componentContext {

    val timezones get() = TimeRegionRepository.timeRegions

    fun onAddTimezoneClicked() {
        openFormSelectionScreen()
    }
}