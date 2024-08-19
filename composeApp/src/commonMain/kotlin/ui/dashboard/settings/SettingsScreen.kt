package ui.dashboard.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import data.preferences.model.DateFormatPreference
import data.preferences.model.LocationFormatPreference
import data.preferences.model.ThemePreference
import ui.component.ListView
import ui.component.RadioGroupBottomSheet
import ui.component.RadioRowData
import ui.theme.AppTypography

@Composable
fun SettingsScreen(
    innerPadding: PaddingValues,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    uriHandler: UriHandler = LocalUriHandler.current,
    state: SettingsUiState,
    onAction: (SettingsAction) -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(32.dp))
        Text("Settings", style = AppTypography.headlineMedium)
        Spacer(Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surfaceContainer)
        ) {
            ListView(
                data = listOf(
                    { Theme { onAction(SettingsAction.ToggleThemeVisibility(show = true)) } },
                    { DateFormat { onAction(SettingsAction.ToggleDateFormatVisibility(show = true)) } },
                    { LocationFormat { onAction(SettingsAction.ToggleLocationFormatVisibility(show = true)) } },
                ),
                divider = { HorizontalDivider() }
            )
        }
        Spacer(Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surfaceContainer)
        ) {
            ListView(
                data = listOf(
                    { AppInfo { onAction(SettingsAction.OpenAppInfo) } },
                    { PrivacyPolicy { onAction(SettingsAction.OpenPrivacyPolicy(open = true)) } },
                    { SendFeedback { onAction(SettingsAction.OpenSendFeedback(open = true)) } },
                    { RateUs { onAction(SettingsAction.OpenRateUs(open = true)) } },
                ),
                divider = { HorizontalDivider() }
            )
        }
        Spacer(Modifier.height(16.dp))

        Text(
            state.version,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }

    RadioGroupBottomSheet(
        title = "Theme",
        selectedIndex = state.theme.ordinal,
        items = ThemePreference.entries.map { RadioRowData(id = it.name, label = it.label) },
        isVisible = state.showTheme,
        onSelected = { selectedItem ->
            onAction(SettingsAction.OnThemeSelected(ThemePreference.entries.first { it.name == selectedItem.id }))
        },
        onDismissed = { onAction(SettingsAction.ToggleThemeVisibility(show = false)) }
    )

    RadioGroupBottomSheet(
        title = "Date Format",
        selectedIndex = state.dateFormat.ordinal,
        items = DateFormatPreference.entries.map { RadioRowData(id = it.name, label = it.label, description = it.description) },
        isVisible = state.showDateFormat,
        onSelected = { selectedItem ->
            onAction(SettingsAction.OnDateFormatSelected(DateFormatPreference.entries.first { it.name == selectedItem.id }))
        },
        onDismissed = { onAction(SettingsAction.ToggleDateFormatVisibility(show = false)) }
    )

    RadioGroupBottomSheet(
        title = "Location Format",
        selectedIndex = state.locationFormat.ordinal,
        items = LocationFormatPreference.entries.map { RadioRowData(id = it.name, label = it.label, description = it.description) },
        isVisible = state.showLocationFormat,
        onSelected = { selectedItem ->
            onAction(SettingsAction.OnLocationFormatSelected(LocationFormatPreference.entries.first { it.name == selectedItem.id }))
        },
        onDismissed = { onAction(SettingsAction.ToggleLocationFormatVisibility(show = false)) }
    )

    LaunchedEffect(state, lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            if (state.openPrivacyPolicy) {
                uriHandler.openUri("https://github.com/delacrixmorgan/twilight-kmp/blob/main/PRIVACY_POLICY.md")
                onAction(SettingsAction.OpenPrivacyPolicy(open = false))
            }
            if (state.openSendFeedback) {
                val email = "delacrixmorgan@gmail.com"
                val subject = "Twilight - App Feedback"
                uriHandler.openUri("mailto:$email?subject=$subject")
                onAction(SettingsAction.OpenSendFeedback(open = false))
            }
            if (state.openRateUs) {
                uriHandler.openUri("https://play.google.com/store/apps/details?id=com.delacrixmorgan.twilight")
                onAction(SettingsAction.OpenRateUs(open = false))
            }
        }
    }
}