package ui.dashboard

import com.arkivanov.decompose.ComponentContext

class DashboardViewModel(
    componentContext: ComponentContext,
    private val openFormSelectionScreen: () -> Unit
) : ComponentContext by componentContext {

    fun onEvent(event: DashboardEvent) {
        when (event) {
            DashboardEvent.CreateNew -> openFormSelectionScreen()
        }
    }
}

sealed interface DashboardEvent {
    data object CreateNew : DashboardEvent
}