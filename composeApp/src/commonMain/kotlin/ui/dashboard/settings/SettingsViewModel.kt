package ui.dashboard.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.preferences.DateFormatPreference
import data.preferences.LocationTypePreference
import data.preferences.PreferencesRepository
import data.preferences.ThemePreference
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
    val theme = mutableStateOf(ThemePreference.Default)
    val dateFormat = mutableStateOf(DateFormatPreference.Default)
    val locationType = mutableStateOf(LocationTypePreference.Default)

    init {
        loadPreferences()
    }

    private fun loadPreferences() {
        viewModelScope.launch {
            launch { preferences.getTheme().collect { theme.value = it } }
            launch { preferences.getDateFormat().collect { dateFormat.value = it } }
            launch { preferences.getLocationType().collect { locationType.value = it } }
        }
    }

    fun onThemeSelected(item: ThemePreference) {
        viewModelScope.launch { preferences.saveTheme(item) }
    }

    fun onThemeClicked(show: Boolean) {
        _uiState.update { it.copy(showTheme = show) }
    }

    fun onDateFormatSelected(item: DateFormatPreference) {
        viewModelScope.launch { preferences.saveDateFormat(item) }
    }

    fun onDateFormatClicked(show: Boolean) {
        _uiState.update { it.copy(showDateFormat = show) }
    }

    fun onLocationTypeSelected(item: LocationTypePreference) {
        viewModelScope.launch { preferences.saveLocationType(item) }
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