package nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import ui.create.timeregion.SelectTimeRegionScreen
import ui.dashboard.DashboardScreen
import ui.dashboard.home.HomeScreen
import ui.dashboard.settings.SettingsScreen

@Composable
fun TwilightNavHost(navController: NavHostController = rememberNavController()) {
    //    val backStackEntry by navController.currentBackStackEntryAsState()
//    val currentScreen = BottomNavItem.entries.firstOrNull {
//        it.route == backStackEntry?.destination?.route
//    } ?: BottomNavItem.Dashboard
    NavHost(
        navController = navController,
        startDestination = Screens.Dashboard.route
    ) {
        composable(Screens.Dashboard.route) { DashboardScreen(navController) }
        createNewLocationGraph(navController)
    }
}

fun NavGraphBuilder.createNewLocationGraph(navController: NavController) {
    navigation(startDestination = Screens.Create.route, route = Screens.Create.name) {
        composable(Screens.Create.route) { SelectTimeRegionScreen() }
    }
}

@Composable
fun DashboardBottomNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues,
) {
    NavHost(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(innerPadding),
        navController = navController,
        startDestination = DashboardBottomNavItem.Home.route
    ) {
        composable(DashboardBottomNavItem.Home.route) { HomeScreen() }
        composable(DashboardBottomNavItem.Settings.route) { SettingsScreen() }
    }
}
