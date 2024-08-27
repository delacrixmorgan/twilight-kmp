package ui.dashboard.settings.appinfo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import org.koin.core.component.KoinComponent

class AppInfoViewModel : ViewModel(), KoinComponent {
    var state by mutableStateOf(AppInfoUiState())
        private set

    fun onAction(navHostController: NavHostController, action: AppInfoAction) {
        when (action) {
            is AppInfoAction.OpenDeveloper -> state = state.copy(openDeveloper = action.show)
            is AppInfoAction.OpenSourceCode -> state = state.copy(openSourceCode = action.show)
            AppInfoAction.GoBack -> navHostController.navigateUp()
        }
    }
}

data class AppInfoUiState(
    val openDeveloper: Boolean = false,
    val openSourceCode: Boolean = false,
)

sealed interface AppInfoAction {
    data class OpenDeveloper(val show: Boolean) : AppInfoAction
    data class OpenSourceCode(val show: Boolean) : AppInfoAction

    data object GoBack : AppInfoAction
}