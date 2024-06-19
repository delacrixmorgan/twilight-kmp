package ui.form.summary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import nav.Screens
import ui.common.observeEvent
import ui.component.LocationListRow
import ui.keyboardShownState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryScreen(
    modifier: Modifier,
    navHostController: NavHostController,
    viewModel: SummaryViewModel = viewModel { SummaryViewModel() },
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
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
                        "Summary",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                },
            )
        },
        bottomBar = {
            Column(Modifier.padding(16.dp)) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { viewModel.onCreateClicked() }
                ) {
                    Text("Create", modifier = Modifier.padding(vertical = 8.dp))
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    ) { innerPadding ->
        Column(Modifier.fillMaxSize().padding(innerPadding).padding(16.dp)) {
            Text(
                "Here is what it will look like in your dashboard. You can still edit it later.",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.weight(1F))

            viewModel.location.value?.let {
                LocationListRow(it)
            }
        }
    }

    LaunchedEffect(viewModel, lifecycleOwner) {
        viewModel.openDashboardEvent.observeEvent(lifecycleOwner) {
            navHostController.popBackStack(Screens.Dashboard.route, inclusive = false)
        }
    }
}