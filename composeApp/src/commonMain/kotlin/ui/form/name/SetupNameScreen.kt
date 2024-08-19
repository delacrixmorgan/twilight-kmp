package ui.form.name

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.TravelExplore
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ui.component.navigationIcon.NavigationBackIcon
import ui.keyboardShownState
import ui.theme.DefaultColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupNameScreen(
    state: SetupNameUiState,
    onAction: (SetupNameAction) -> Unit
) {
    val scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val localFocusManager = LocalFocusManager.current
    if (!keyboardShownState().value) localFocusManager.clearFocus()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        if (state.isEditMode) "Edit name" else "Pick a name",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = { NavigationBackIcon { onAction(SetupNameAction.OnBackClicked) } },
            )
        },
        bottomBar = {
            Column(Modifier.padding(16.dp)) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = state.continueButtonEnabled,
                    onClick = { onAction(SetupNameAction.OnContinueClicked) }
                ) {
                    Text("Continue", modifier = Modifier.padding(vertical = 8.dp))
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    ) { innerPadding ->
        Column(Modifier.fillMaxSize().padding(innerPadding).padding(16.dp)) {
            Text("Enter a name and customise your region name if you'd like.", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.weight(1F))

            Text("Name", style = MaterialTheme.typography.labelLarge)
            Spacer(Modifier.height(8.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                colors = DefaultColors.textFieldDefaultColors(),
                shape = CircleShape,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words, imeAction = ImeAction.Done),
                value = state.locationName,
                onValueChange = { onAction(SetupNameAction.OnLocationNameUpdated(it)) },
                placeholder = { Text("Name") },
                leadingIcon = {
                    Icon(Icons.Rounded.Create, contentDescription = null)
                },
            )
            Spacer(Modifier.height(16.dp))

            Text("Region Name", style = MaterialTheme.typography.labelLarge)
            Spacer(Modifier.height(8.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                colors = DefaultColors.textFieldDefaultColors(),
                shape = CircleShape,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words, imeAction = ImeAction.Done),
                value = state.regionName,
                onValueChange = { onAction(SetupNameAction.OnRegionNameUpdated(it)) },
                placeholder = { Text("Region Name") },
                leadingIcon = { Icon(Icons.Rounded.TravelExplore, contentDescription = null) },
            )
        }
    }
}