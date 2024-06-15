package ui.dashboard

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import nav.dashboard.DashboardBottomNavHost
import nav.dashboard.DashboardBottomNavItem

@Composable
fun DashboardScreen(
    navHostController: NavHostController,
    bottomNavHostController: NavHostController = rememberNavController(),
) {
    Scaffold(bottomBar = { BottomNavigationBar(bottomNavHostController) }) { innerPadding ->
        DashboardBottomNavHost(navHostController, bottomNavHostController, innerPadding)
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