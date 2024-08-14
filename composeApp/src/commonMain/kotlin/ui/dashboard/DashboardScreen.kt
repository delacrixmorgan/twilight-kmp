package ui.dashboard

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
    Scaffold(
        bottomBar = { BottomNavigationBar(bottomNavHostController) }
    ) { innerPadding ->
        DashboardBottomNavHost(navHostController, bottomNavHostController, innerPadding)
    }
}

@Composable
private fun BottomNavigationBar(navHostController: NavHostController) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        DashboardBottomNavItem.entries.forEach { navItem ->
            val selected by remember(currentRoute) {
                derivedStateOf { currentRoute == navItem.route::class.qualifiedName }
            }
            NavigationBarItem(
                icon = { Icon(navItem.icon, contentDescription = navItem.title) },
                selected = selected,
                onClick = {
                    if (selected) return@NavigationBarItem
                    navHostController.navigate(navItem.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}