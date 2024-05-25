package nav.dashboard

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ui.dashboard.home.HomeScreen
import ui.dashboard.settings.SettingsScreen

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
//        composable(DashboardBottomNavItem.Home.route) { HomeScreen(navController) }
        composable(DashboardBottomNavItem.Settings.route) { SettingsScreen() }
    }
}
