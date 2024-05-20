package ui.dashboard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import nav.Screens
import nav.dashboard.DashboardBottomNavHost
import nav.dashboard.DashboardBottomNavItem
import ui.common.observeEvent

@Composable
fun DashboardScreen(
    navHostController: NavHostController,
    viewModel: DashboardViewModel = viewModel { DashboardViewModel() },
    bottomNavHostController: NavHostController = rememberNavController(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    Scaffold(
        bottomBar = { BottomNavigationBar(bottomNavHostController) },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Add") },
                icon = { Icon(imageVector = Icons.Filled.Add, contentDescription = "Add") },
                onClick = { viewModel.onAddLocationClicked() }
            )
        }
    ) { innerPadding ->
        DashboardBottomNavHost(bottomNavHostController, innerPadding)
    }

    LaunchedEffect(viewModel, lifecycleOwner) {
        viewModel.openFormEvent.observeEvent(lifecycleOwner) {
            navHostController.navigate(Screens.FormSelectLocationType.route)
        }
    }
}

@Composable
private fun BottomNavigationBar(navHostController: NavHostController) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        DashboardBottomNavItem.entries.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = screen.title) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navHostController.navigate(screen.route) {
                        popUpTo(navHostController.graph.findStartDestination().navigatorName) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}