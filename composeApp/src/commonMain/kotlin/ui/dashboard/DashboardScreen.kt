package ui.dashboard

import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import nav.Routes
import nav.dashboard.DashboardBottomNavHost
import nav.dashboard.DashboardBottomNavItem
import ui.component.pinnedExitUntilCollapsedScrollBehavior
import ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navHostController: NavHostController,
    bottomNavHostController: NavHostController = rememberNavController(),
) {
    val backStackEntry by bottomNavHostController.currentBackStackEntryAsState()
    val isSettingsRoute = backStackEntry?.destination?.route == "nav.Routes.${Routes.Settings}"

    val scrollState = rememberScrollState()
    val scrollBehavior = pinnedExitUntilCollapsedScrollBehavior(
        canScroll = { scrollState.canScrollForward || scrollState.canScrollBackward },
    )
    Scaffold(
        Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            if (isSettingsRoute) {
                TopAppBar(
                    scrollBehavior = scrollBehavior,
                    title = { Text("Settings", style = AppTypography.headlineMedium) }
                )
            }
        },
        bottomBar = { BottomNavigationBar(bottomNavHostController) }
    ) { innerPadding ->
        DashboardBottomNavHost(navHostController, bottomNavHostController, innerPadding, scrollState)
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