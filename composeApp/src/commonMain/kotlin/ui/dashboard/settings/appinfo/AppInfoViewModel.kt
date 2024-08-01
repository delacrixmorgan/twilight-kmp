package ui.dashboard.settings.appinfo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

class AppInfoViewModel : ViewModel(), KoinComponent {
    private val _uiState = MutableStateFlow(AppInfoUiState())
    val uiState: StateFlow<AppInfoUiState> = _uiState

    fun onDeveloperClicked(show: Boolean) {
        _uiState.update { it.copy(openDeveloper = show) }
    }

    fun onSourceCodeClicked(show: Boolean) {
        _uiState.update { it.copy(openSourceCode = show) }
    }
}

data class AppInfoUiState(
    val openDeveloper: Boolean = false,
    val openSourceCode: Boolean = false,
)