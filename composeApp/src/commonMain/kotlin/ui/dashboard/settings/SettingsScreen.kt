package ui.dashboard.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import ui.component.ListViewGroup
import ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier,
    viewModel: SettingsViewModel = viewModel { SettingsViewModel() },
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
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
                ListViewGroup(
                    data = listOf(
                        { Theme(Modifier.clickable { viewModel.onThemeClicked(show = true) }) },
                        { DateFormat(Modifier.clickable { viewModel.onDateFormatClicked(show = true) }) },
                        { LocationType(Modifier.clickable { viewModel.onLocationTypeClicked(show = true) }) },
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
                ListViewGroup(
                    data = listOf(
                        { AppInfo(Modifier.clickable { viewModel.onAppInfoClicked(open = true) }) },
                        { PrivacyPolicy(Modifier.clickable { viewModel.onPrivacyPolicyClicked(open = true) }) },
                        { SendFeedback(Modifier.clickable { viewModel.onSendFeedbackClicked(open = true) }) },
                        { RateUs(Modifier.clickable { viewModel.onRateUsClicked(open = true) }) },
                    ),
                    divider = { HorizontalDivider() }
                )
            }
        }
    }

    val uiState by viewModel.uiState.collectAsState()
    ThemeBottomSheet(
        isVisible = uiState.showTheme,
        onDismiss = { viewModel.onThemeClicked(show = false) }
    )
    DateFormatBottomSheet(
        isVisible = uiState.showDateFormat,
        onDismiss = { viewModel.onDateFormatClicked(show = false) }
    )
    LocationTypeBottomSheet(
        isVisible = uiState.showLocationType,
        onDismiss = { viewModel.onLocationTypeClicked(show = false) }
    )

    LaunchedEffect(uiState, lifecycleOwner) {
        if (uiState.openAppInfo) {
            viewModel.onAppInfoClicked(open = false)
        }
        if (uiState.openPrivacyPolicy) {
            viewModel.onPrivacyPolicyClicked(open = false)
        }
        if (uiState.openSendFeedback) {
            viewModel.onSendFeedbackClicked(open = false)
        }
        if (uiState.openRateUs) {
            uriHandler.openUri("https://play.google.com/store/apps/details?id=com.delacrixmorgan.twilight")
            viewModel.onRateUsClicked(open = false)
        }
    }
}