package ui.dashboard.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SettingsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState

    fun onThemeClicked(show: Boolean) {
        _uiState.update { it.copy(showTheme = show) }
    }

    fun onDateFormatClicked(show: Boolean) {
        _uiState.update { it.copy(showDateFormat = show) }
    }

    fun onLocationTypeClicked(show: Boolean) {
        _uiState.update { it.copy(showLocationType = show) }
    }

    fun onAppInfoClicked(open: Boolean) {
        _uiState.update { it.copy(openAppInfo = open) }
    }

    fun onPrivacyPolicyClicked(open: Boolean) {
        _uiState.update { it.copy(openPrivacyPolicy = open) }
    }

    fun onSendFeedbackClicked(open: Boolean) {
        _uiState.update { it.copy(openSendFeedback = open) }
    }

    fun onRateUsClicked(open: Boolean) {
        _uiState.update { it.copy(openRateUs = open) }
    }
}

data class SettingsUiState(
    val showTheme: Boolean = false,
    val showDateFormat: Boolean = false,
    val showLocationType: Boolean = false,

    val openAppInfo: Boolean = false,
    val openPrivacyPolicy: Boolean = false,
    val openSendFeedback: Boolean = false,
    val openRateUs: Boolean = false,
)

enum class SegmentedButtonType {
    Place,
    Person
}