package nav.dashboard

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import nav.Routes
import org.koin.compose.viewmodel.koinViewModel
import ui.dashboard.settings.SettingsScreen
import ui.dashboard.settings.SettingsViewModel
import ui.dashboard.today.TodayScreen
import ui.dashboard.today.TodayViewModel

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
        composable<Routes.Today> {
            val viewModel = koinViewModel<TodayViewModel>()
            TodayScreen(Modifier.padding(innerPadding), state = viewModel.state.collectAsStateWithLifecycle().value, onAction = { viewModel.onAction(navHostController, it) })
        }
        composable<Routes.Settings> {
            val viewModel = koinViewModel<SettingsViewModel>()
            SettingsScreen(innerPadding, state = viewModel.state.collectAsStateWithLifecycle().value, onAction = { viewModel.onAction(navHostController, it) })
        }
    }
}
