package nav.dashboard

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ui.dashboard.settings.SettingsScreen
import ui.dashboard.today.TodayScreen

@Composable
fun DashboardBottomNavHost(
    navHostController: NavHostController,
    bottomNavHostController: NavHostController,
    innerPadding: PaddingValues,
) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = bottomNavHostController,
        startDestination = DashboardBottomNavItem.Today.route
//        startDestination = DashboardBottomNavItem.Settings.route
    ) {
        composable(DashboardBottomNavItem.Today.route) { TodayScreen(Modifier.padding(innerPadding), navHostController) }
        composable(DashboardBottomNavItem.Settings.route) { SettingsScreen(Modifier.padding(innerPadding)) }
    }
}
