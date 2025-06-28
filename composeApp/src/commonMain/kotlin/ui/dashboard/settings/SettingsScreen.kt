package ui.dashboard.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.composables.core.SheetDetent.Companion.FullyExpanded
import com.composables.core.SheetDetent.Companion.Hidden
import com.composables.core.rememberModalBottomSheetState
import data.preferences.model.DateFormatPreference
import data.preferences.model.LocationFormatPreference
import data.preferences.model.ThemePreference
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import rateUsStoreLink
import twilight.composeapp.generated.resources.Res
import twilight.composeapp.generated.resources.app_name
import twilight.composeapp.generated.resources.ic_logo_foreground
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
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(24.dp))
        Text("Settings", style = AppTypography.headlineMedium)
        Spacer(Modifier.height(12.dp))
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
        Spacer(Modifier.height(32.dp))

        Image(painter = painterResource(Res.drawable.ic_logo_foreground), "Twilight Logo")

        Spacer(Modifier.height(8.dp))

        Text(
            stringResource(Res.string.app_name) + " " + state.version,
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(Modifier.height(8.dp))
    }

    val themeBottomSheetState = rememberModalBottomSheetState(initialDetent = Hidden)
    RadioGroupBottomSheet(
        sheetState = themeBottomSheetState,
        title = "Theme",
        selectedIndex = state.theme.ordinal,
        items = ThemePreference.entries.map { RadioRowData(id = it.name, label = it.label) },
        onSelected = { selectedItem ->
            onAction(SettingsAction.OnThemeSelected(ThemePreference.entries.first { it.name == selectedItem.id }))
        },
        onDismissed = { onAction(SettingsAction.ToggleThemeVisibility(show = false)) }
    )

    val dateFormatBottomSheetState = rememberModalBottomSheetState(initialDetent = Hidden)
    RadioGroupBottomSheet(
        sheetState = dateFormatBottomSheetState,
        title = "Date Format",
        selectedIndex = state.dateFormat.ordinal,
        items = DateFormatPreference.entries.map { RadioRowData(id = it.name, label = it.label, description = it.description) },
        onSelected = { selectedItem ->
            onAction(SettingsAction.OnDateFormatSelected(DateFormatPreference.entries.first { it.name == selectedItem.id }))
        },
        onDismissed = { onAction(SettingsAction.ToggleDateFormatVisibility(show = false)) }
    )

    val locationFormatBottomSheetState = rememberModalBottomSheetState(initialDetent = Hidden)
    RadioGroupBottomSheet(
        sheetState = locationFormatBottomSheetState,
        title = "Location Format",
        selectedIndex = state.locationFormat.ordinal,
        items = LocationFormatPreference.entries.map { RadioRowData(id = it.name, label = it.label, description = it.description) },
        onSelected = { selectedItem ->
            onAction(SettingsAction.OnLocationFormatSelected(LocationFormatPreference.entries.first { it.name == selectedItem.id }))
        },
        onDismissed = { onAction(SettingsAction.ToggleLocationFormatVisibility(show = false)) }
    )

    LaunchedEffect(state, lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            themeBottomSheetState.targetDetent = if (state.showTheme) FullyExpanded else Hidden
            dateFormatBottomSheetState.targetDetent = if (state.showDateFormat) FullyExpanded else Hidden
            locationFormatBottomSheetState.targetDetent = if (state.showLocationFormat) FullyExpanded else Hidden

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
                uriHandler.openUri(rateUsStoreLink)
                onAction(SettingsAction.OpenRateUs(open = false))
            }
        }
    }
}