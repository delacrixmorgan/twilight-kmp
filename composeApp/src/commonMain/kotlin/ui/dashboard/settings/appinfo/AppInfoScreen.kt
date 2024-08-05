package ui.dashboard.settings.appinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ui.component.ListItem
import ui.component.ListItemColumnLabel
import ui.component.ListView
import ui.component.navigationIcon.NavigationBackIcon
import ui.theme.AppTypography
import ui.theme.TwilightModifiers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppInfoScreen(
    navHostController: NavHostController,
    viewModel: AppInfoViewModel = viewModel { AppInfoViewModel() },
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("App Info", style = AppTypography.headlineMedium) },
                navigationIcon = { NavigationBackIcon { navHostController.navigateUp() } },
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
                        { Developer(Modifier.clickable { viewModel.onDeveloperClicked(show = true) }) },
                        { SourceCode(Modifier.clickable { viewModel.onSourceCodeClicked(show = true) }) },
                    ),
                    divider = { HorizontalDivider() }
                )
            }
        }
    }

    val uiState by viewModel.uiState.collectAsState()
    val uriHandler = LocalUriHandler.current
    LaunchedEffect(uiState, lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            if (uiState.openDeveloper) {
                uriHandler.openUri("https://github.com/delacrixmorgan")
                viewModel.onDeveloperClicked(show = false)
            }
            if (uiState.openSourceCode) {
                uriHandler.openUri("https://github.com/delacrixmorgan/twilight-kmp")
                viewModel.onSourceCodeClicked(show = false)
            }
        }
    }
}

@Composable
private fun Developer(modifier: Modifier) {
    val label = "Developer"
    val description = "Delacrix Morgan"
    ListItem(
        modifier = modifier,
        label = {
            ListItemColumnLabel(
                label = label,
                description = description,
            )
        },
        startIcon = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.Person,
                contentDescription = label
            )
        },
        endIcon = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.ChevronRight,
                contentDescription = label
            )
        }
    )
}

@Composable
private fun SourceCode(modifier: Modifier) {
    val label = "Source Code"
    val description = "GitHub"
    ListItem(
        modifier = modifier,
        label = {
            ListItemColumnLabel(
                label = label,
                description = description,
            )
        },
        startIcon = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.Code,
                contentDescription = label
            )
        },
        endIcon = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.ChevronRight,
                contentDescription = label
            )
        }
    )
}