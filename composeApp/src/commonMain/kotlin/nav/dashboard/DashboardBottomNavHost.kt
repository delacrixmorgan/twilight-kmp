package nav.dashboard

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import nav.Routes
import ui.dashboard.settings.SettingsScreen
import ui.dashboard.settings.SettingsViewModel
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
    ) {
        composable<Routes.Today> { TodayScreen(Modifier.padding(innerPadding), navHostController) }
        composable<Routes.Settings> {
            val viewModel: SettingsViewModel = viewModel { SettingsViewModel() }
            SettingsScreen(Modifier.padding(innerPadding), state = viewModel.state, onAction = { viewModel.onAction(navHostController, it) })
        }
    }
}
