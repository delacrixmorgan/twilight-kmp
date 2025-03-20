package nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.viewmodel.koinViewModel
import ui.dashboard.DashboardScreen
import ui.dashboard.settings.appinfo.AppInfoScreen
import ui.dashboard.settings.appinfo.AppInfoViewModel
import ui.form.name.SetupNameScreen
import ui.form.name.SetupNameViewModel
import ui.form.summary.SummaryScreen
import ui.form.summary.SummaryViewModel
import ui.form.zone.SelectZoneScreen
import ui.form.zone.SelectZoneViewModel

@Composable
fun TwilightNavHost(navHostController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.Dashboard
    ) {
        composable<Routes.Dashboard> { DashboardScreen(navHostController) }
        formGraph(navHostController)
    }
}

fun NavGraphBuilder.formGraph(navHostController: NavHostController) {
    composable<Routes.FormSelectZone> {
        val viewModel = koinViewModel<SelectZoneViewModel>()
        SelectZoneScreen(state = viewModel.state.collectAsStateWithLifecycle().value, onAction = { viewModel.onAction(navHostController, it) })
    }
    composable<Routes.FormSetupName> {
        val viewModel = koinViewModel<SetupNameViewModel>()
        SetupNameScreen(state = viewModel.state.collectAsStateWithLifecycle().value, onAction = { viewModel.onAction(navHostController, it) })
    }
    composable<Routes.FormSummary> {
        val viewModel = koinViewModel<SummaryViewModel>()
        SummaryScreen(state = viewModel.state.collectAsStateWithLifecycle().value, onAction = { viewModel.onAction(navHostController, it) })
    }
    composable<Routes.AppInfo> {
        val viewModel = koinViewModel<AppInfoViewModel>()
        AppInfoScreen(state = viewModel.state, onAction = { viewModel.onAction(navHostController, it) })
    }
}