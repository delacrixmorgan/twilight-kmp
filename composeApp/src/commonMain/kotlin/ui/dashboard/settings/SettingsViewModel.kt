package ui.dashboard.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.model.TwilightTheme
import data.preferences.PreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SettingsViewModel : ViewModel(), KoinComponent {
    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState

    private val preferences: PreferencesRepository by inject()
    val theme = mutableStateOf(TwilightTheme.Default)

    init {
        viewModelScope.launch {
            loadData()
        }
    }

    private suspend fun loadData() {
        preferences.getTheme().collect { theme.value = it }
    }

    fun onThemeSelected(theme: TwilightTheme) {
        viewModelScope.launch { preferences.saveTheme(theme) }
    }

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