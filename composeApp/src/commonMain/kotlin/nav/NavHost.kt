package nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import ui.dashboard.DashboardScreen
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
        startDestination = Screens.Dashboard.route
    ) {
        composable(Screens.Dashboard.route) { DashboardScreen(navHostController) }
        formGraph(navHostController)
    }
}

fun NavGraphBuilder.formGraph(navHostController: NavHostController) {
    navigation(startDestination = Screens.FormSetupName.route, route = Screens.FormSetupName.name) {
        composable(Screens.FormSetupName.route) { SetupNameScreen(navHostController) }
        composable(Screens.FormSelectTimeRegion.route) { SelectTimeRegionScreen(navHostController) }
        composable(Screens.FormSummary.route) { SummaryScreen(navHostController) }
    }
}