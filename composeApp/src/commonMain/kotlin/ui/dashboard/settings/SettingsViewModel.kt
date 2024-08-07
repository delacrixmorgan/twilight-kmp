package ui.dashboard.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import data.preferences.DateFormatPreference
import data.preferences.LocationTypePreference
import data.preferences.PreferencesRepository
import data.preferences.ThemePreference
import getVersionCode
import getVersionName
import kotlinx.coroutines.launch
import nav.Routes
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SettingsViewModel : ViewModel(), KoinComponent {

    var state by mutableStateOf(SettingsUiState())
        private set

    private val preferences: PreferencesRepository by inject()

    init {
        state = state.copy(version = "${getVersionName()} (${getVersionCode()})")
        loadPreferences()
    }

    private fun loadPreferences() {
        viewModelScope.launch {
            launch { preferences.getTheme().collect { state = state.copy(theme = it) } }
            launch { preferences.getDateFormat().collect { state = state.copy(dateFormat = it) } }
            launch { preferences.getLocationType().collect { state = state.copy(locationType = it) } }
        }
    }

    fun onAction(navHostController: NavHostController, action: SettingsAction) {
        when (action) {
            is SettingsAction.ToggleThemeVisibility -> {
                state = state.copy(showTheme = action.show)
            }
            is SettingsAction.ToggleDateFormatVisibility -> {
                state = state.copy(showDateFormat = action.show)
            }
            is SettingsAction.ToggleLocationTypeVisibility -> {
                state = state.copy(showLocationType = action.show)
            }
            is SettingsAction.OpenAppInfo -> {
                navHostController.navigate(Routes.AppInfo)
            }
            is SettingsAction.OpenPrivacyPolicy -> {
                state = state.copy(openPrivacyPolicy = action.open)
            }
            is SettingsAction.OpenRateUs -> {
                state = state.copy(openRateUs = action.open)
            }
            is SettingsAction.OpenSendFeedback -> {
                state = state.copy(openSendFeedback = action.open)
            }
            is SettingsAction.OnThemeSelected -> {
                viewModelScope.launch { preferences.saveTheme(action.theme) }
            }
            is SettingsAction.OnDateFormatSelected -> {
                viewModelScope.launch { preferences.saveDateFormat(action.dateFormat) }
            }
            is SettingsAction.OnLocationTypeSelected -> {
                viewModelScope.launch { preferences.saveLocationType(action.locationType) }
            }
        }
    }
}

data class SettingsUiState(
    val version: String = "",
    val theme: ThemePreference = ThemePreference.Default,
    val dateFormat: DateFormatPreference = DateFormatPreference.Default,
    val locationType: LocationTypePreference = LocationTypePreference.Default,

    val showTheme: Boolean = false,
    val showDateFormat: Boolean = false,
    val showLocationType: Boolean = false,

    val openPrivacyPolicy: Boolean = false,
    val openSendFeedback: Boolean = false,
    val openRateUs: Boolean = false,
)

sealed interface SettingsAction {
    data class ToggleThemeVisibility(val show: Boolean) : SettingsAction
    data class ToggleDateFormatVisibility(val show: Boolean) : SettingsAction
    data class ToggleLocationTypeVisibility(val show: Boolean) : SettingsAction

    data object OpenAppInfo : SettingsAction
    data class OpenPrivacyPolicy(val open: Boolean) : SettingsAction
    data class OpenSendFeedback(val open: Boolean) : SettingsAction
    data class OpenRateUs(val open: Boolean) : SettingsAction

    data class OnThemeSelected(val theme: ThemePreference) : SettingsAction
    data class OnDateFormatSelected(val dateFormat: DateFormatPreference) : SettingsAction
    data class OnLocationTypeSelected(val locationType: LocationTypePreference) : SettingsAction
}