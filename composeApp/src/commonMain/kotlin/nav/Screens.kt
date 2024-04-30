package nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screens(
    val title: String,
    val route: String
) {
    Dashboard(
        "Dashboard",
        "dashboard"
    ),
    Create(
        "Add",
        "create"
    )
}

enum class DashboardBottomNavItem(
    val title: String,
    val route: String,
    val icon: ImageVector,
) {
    Home(
        "Home",
        "home",
        Icons.Rounded.Home
    ),
    Settings(
        "Settings",
        "settings",
        Icons.Rounded.Settings
    ),
}