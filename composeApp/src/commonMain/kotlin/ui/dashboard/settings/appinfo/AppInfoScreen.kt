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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import nav.NavEffect
import nav.NavEffectHandler
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.component.ListItem
import ui.component.ListView
import ui.component.navigationIcon.NavigationBackIcon
import ui.theme.AppTheme
import ui.theme.AppTypography
import ui.theme.TwilightModifiers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppInfoScreen(
    effects: Flow<NavEffect>,
    onAction: (AppInfoAction) -> Unit,
) {
    NavEffectHandler(effects)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("App Info", style = AppTypography.headlineMedium) },
                navigationIcon = { NavigationBackIcon(tint = MaterialTheme.colorScheme.onSurface) { onAction(AppInfoAction.GoBack) } },
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
                        { Developer { onAction(AppInfoAction.OpenDeveloper) } },
                        { SourceCode { onAction(AppInfoAction.OpenSourceCode) } },
                    ),
                    divider = { HorizontalDivider() }
                )
            }
        }
    }
}

@Composable
private fun Developer(onClick: () -> Unit) {
    val label = "Developer"
    val description = "Delacrix Morgan"
    ListItem(
        modifier = Modifier.clickable { onClick() },
        label = label,
        description = description,
        startContent = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.Person,
                contentDescription = label
            )
        },
        endContent = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.ChevronRight,
                contentDescription = label
            )
        }
    )
}

@Composable
private fun SourceCode(onClick: () -> Unit) {
    val label = "Source Code"
    val description = "GitHub"
    ListItem(
        modifier = Modifier.clickable { onClick() },
        label = label,
        description = description,
        startContent = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.Code,
                contentDescription = label
            )
        },
        endContent = {
            Icon(
                modifier = TwilightModifiers.iconModifier,
                imageVector = Icons.Rounded.ChevronRight,
                contentDescription = label
            )
        }
    )
}

@Preview
@Composable
private fun AppInfoScreenPreview() {
    AppTheme {
        AppInfoScreen(effects = emptyFlow(), onAction = {})
    }
}