package ui.dashboard.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ui.common.Event
import ui.common.triggerEvent

class SettingsViewModel : ViewModel() {
    val openThemeEvent = MutableSharedFlow<Event<Unit>>()
    val openDateFormatEvent = MutableSharedFlow<Event<Unit>>()
    val openLocationTypeEvent = MutableSharedFlow<Event<Unit>>()
    val openAppInfoEvent = MutableSharedFlow<Event<Unit>>()
    val openPrivacyPolicyEvent = MutableSharedFlow<Event<Unit>>()
    val openSendFeedbackEvent = MutableSharedFlow<Event<Unit>>()
    val openRateUsEvent = MutableSharedFlow<Event<Unit>>()

    fun onThemeClicked() {
        viewModelScope.launch { openThemeEvent.triggerEvent() }
    }

    fun onDateFormatClicked() {
        viewModelScope.launch { openDateFormatEvent.triggerEvent() }
    }

    fun onLocationTypeClicked() {
        viewModelScope.launch { openLocationTypeEvent.triggerEvent() }
    }

    fun onAppInfoClicked() {
        viewModelScope.launch { openAppInfoEvent.triggerEvent() }
    }

    fun onPrivacyPolicyClicked() {
        viewModelScope.launch { openPrivacyPolicyEvent.triggerEvent() }
    }

    fun onSendFeedbackClicked() {
        viewModelScope.launch { openSendFeedbackEvent.triggerEvent() }
    }

    fun onRateUsClicked() {
        viewModelScope.launch { openRateUsEvent.triggerEvent() }
    }
}