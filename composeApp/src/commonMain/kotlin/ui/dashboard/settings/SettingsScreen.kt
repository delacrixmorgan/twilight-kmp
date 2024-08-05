package ui.dashboard.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import data.preferences.DateFormatPreference
import data.preferences.LocationTypePreference
import data.preferences.ThemePreference
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.component.ListView
import ui.component.RadioGroupBottomSheet
import ui.component.RadioRowData
import ui.theme.AppTheme
import ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    state: SettingsUiState,
    onAction: (SettingsAction) -> Unit,
) {
    val uriHandler = LocalUriHandler.current
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Settings", style = AppTypography.headlineMedium) },
            )
        },
    ) { innerPadding ->
        Column(Modifier.fillMaxSize().padding(innerPadding).padding(16.dp)) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surfaceContainer)
            ) {
                ListView(
                    data = listOf(
                        { Theme { onAction(SettingsAction.ToggleThemeVisibility(show = true)) } },
                        { DateFormat { onAction(SettingsAction.ToggleDateFormatVisibility(show = true)) } },
                        { LocationType { onAction(SettingsAction.ToggleLocationTypeVisibility(show = true)) } },
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
                        { AppInfo { onAction(SettingsAction.OpenAppInfo(open = true)) } },
                        { PrivacyPolicy { onAction(SettingsAction.OpenPrivacyPolicy(open = true)) } },
                        { SendFeedback { onAction(SettingsAction.OpenSendFeedback(open = true)) } },
                        { RateUs { onAction(SettingsAction.OpenRateUs(open = true)) } },
                    ),
                    divider = { HorizontalDivider() }
                )
            }
            Spacer(Modifier.height(16.dp))
            Text(
                "2024.01 (1)",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
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
        title = "Location Type",
        selectedIndex = state.locationType.ordinal,
        items = LocationTypePreference.entries.map { RadioRowData(id = it.name, label = it.label, description = it.description) },
        isVisible = state.showLocationType,
        onSelected = { selectedItem ->
            onAction(SettingsAction.OnLocationTypeSelected(LocationTypePreference.entries.first { it.name == selectedItem.id }))
        },
        onDismissed = { onAction(SettingsAction.ToggleLocationTypeVisibility(show = false)) }
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

@Preview
@Composable
private fun SettingsScreenPreview() {
    AppTheme {
        SettingsScreen(Modifier, state = SettingsUiState(), onAction = {})
    }
}