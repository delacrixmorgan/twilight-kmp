package nav.dashboard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Today
import androidx.compose.ui.graphics.vector.ImageVector
import nav.Routes

enum class DashboardBottomNavItem(
    val title: String,
    val route: Routes,
    val icon: ImageVector,
) {
    Today(
        "Today",
        Routes.Today,
        Icons.Rounded.Today
    ),
    Settings(
        "Settings",
        Routes.Settings,
        Icons.Rounded.Settings
    ),
}