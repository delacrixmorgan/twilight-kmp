package nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ui.dashboard.DashboardScreen
import ui.dashboard.settings.appinfo.AppInfoScreen
import ui.dashboard.settings.appinfo.AppInfoViewModel
import ui.form.name.SetupNameScreen
import ui.form.summary.SummaryScreen
import ui.form.timeregion.SelectTimeRegionScreen

@Composable
fun TwilightNavHost(
    modifier: Modifier,
    navHostController: NavHostController = rememberNavController()
) {
    //    val backStackEntry by navController.currentBackStackEntryAsState()
//    val currentScreen = BottomNavItem.entries.firstOrNull {
//        it.route == backStackEntry?.destination?.route
//    } ?: BottomNavItem.Dashboard
    NavHost(
        navController = navHostController,
        startDestination = Routes.Dashboard
    ) {
        composable<Routes.Dashboard> { DashboardScreen(navHostController) }
        formGraph(navHostController)
    }
}

fun NavGraphBuilder.formGraph(navHostController: NavHostController) {
    composable<Routes.FormSetupName> { SetupNameScreen(navHostController) }
    composable<Routes.FormSelectTimeRegion> { SelectTimeRegionScreen(navHostController) }
    composable<Routes.FormSummary> { SummaryScreen(navHostController) }
    composable<Routes.AppInfo> {
        val viewModel: AppInfoViewModel = viewModel { AppInfoViewModel() }
        AppInfoScreen(state = viewModel.state, onAction = { viewModel.onAction(navHostController, it) })
    }
}