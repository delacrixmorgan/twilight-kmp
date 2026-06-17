package ui.dashboard.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import data.preferences.PreferencesRepository
import data.preferences.model.DateFormatPreference
import data.preferences.model.LocationFormatPreference
import data.preferences.model.ThemePreference
import getVersionCode
import getVersionName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nav.EffectEmitter
import nav.NavEffect
import nav.Routes
import org.koin.core.component.KoinComponent
import rateUsStoreLink

class SettingsViewModel(
    private val preferences: PreferencesRepository
) : ViewModel(), KoinComponent {
    private var _state = MutableStateFlow(SettingsUiState())
    val state: StateFlow<SettingsUiState> = _state.asStateFlow()

    private val emitter = EffectEmitter<NavEffect>()
    val effects = emitter.effects

    init {
        _state.update { it.copy(version = "${getVersionName()} (${getVersionCode()})") }
        loadPreferences()
    }

    private fun loadPreferences() {
        viewModelScope.launch {
            launch { preferences.getTheme().collect { theme -> _state.update { it.copy(theme = theme) } } }
            launch { preferences.getDateFormat().collect { dateFormat -> _state.update { it.copy(dateFormat = dateFormat) } } }
            launch { preferences.getLocationFormat().collect { locationFormat -> _state.update { it.copy(locationFormat = locationFormat) } } }
        }
    }

    fun onAction(navHostController: NavHostController, action: SettingsAction) {
        when (action) {
            is SettingsAction.ToggleThemeVisibility -> {
                _state.update { it.copy(showTheme = action.show) }
            }
            is SettingsAction.ToggleDateFormatVisibility -> {
                _state.update { it.copy(showDateFormat = action.show) }
            }
            is SettingsAction.ToggleLocationFormatVisibility -> {
                _state.update { it.copy(showLocationFormat = action.show) }
            }
            is SettingsAction.OpenAppInfo -> {
                navHostController.navigate(Routes.AppInfo)
            }
            is SettingsAction.OpenPrivacyPolicy -> {
                emitter.send(NavEffect.OpenUri("https://github.com/delacrixmorgan/twilight-kmp/blob/main/PRIVACY_POLICY.md"))
            }
            is SettingsAction.OpenRateUs -> {
                emitter.send(NavEffect.OpenUri(rateUsStoreLink))
            }
            is SettingsAction.OpenSendFeedback -> {
                val email = "delacrixmorgan@gmail.com"
                val subject = "Twilight - App Feedback"
                emitter.send(NavEffect.OpenUri("mailto:$email?subject=$subject"))
            }
            is SettingsAction.OnThemeSelected -> {
                viewModelScope.launch { preferences.saveTheme(action.theme) }
            }
            is SettingsAction.OnDateFormatSelected -> {
                viewModelScope.launch { preferences.saveDateFormat(action.dateFormat) }
            }
            is SettingsAction.OnLocationFormatSelected -> {
                viewModelScope.launch { preferences.saveLocationFormat(action.locationFormat) }
            }
        }
    }
}

data class SettingsUiState(
    val version: String = "",
    val theme: ThemePreference = ThemePreference.Default,
    val dateFormat: DateFormatPreference = DateFormatPreference.Default,
    val locationFormat: LocationFormatPreference = LocationFormatPreference.Default,

    val showTheme: Boolean = false,
    val showDateFormat: Boolean = false,
    val showLocationFormat: Boolean = false,
)

sealed interface SettingsAction {
    data class ToggleThemeVisibility(val show: Boolean) : SettingsAction
    data class ToggleDateFormatVisibility(val show: Boolean) : SettingsAction
    data class ToggleLocationFormatVisibility(val show: Boolean) : SettingsAction

    data object OpenAppInfo : SettingsAction
    data object OpenPrivacyPolicy : SettingsAction
    data object OpenSendFeedback : SettingsAction
    data object OpenRateUs : SettingsAction

    data class OnThemeSelected(val theme: ThemePreference) : SettingsAction
    data class OnDateFormatSelected(val dateFormat: DateFormatPreference) : SettingsAction
    data class OnLocationFormatSelected(val locationFormat: LocationFormatPreference) : SettingsAction
}