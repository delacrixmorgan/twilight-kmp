package nav.dashboard

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import nav.Routes
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
        startDestination = DashboardBottomNavItem.Settings.route
//        startDestination = DashboardBottomNavItem.Today.route
    ) {
        composable<Routes.Today> { TodayScreen(Modifier.padding(innerPadding), navHostController) }
        composable<Routes.Settings> { SettingsScreen(Modifier.padding(innerPadding)) }
    }
}
