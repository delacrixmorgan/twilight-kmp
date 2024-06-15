package nav.dashboard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Today
import androidx.compose.ui.graphics.vector.ImageVector

enum class DashboardBottomNavItem(
    val title: String,
    val route: String,
    val icon: ImageVector,
) {
    Today(
        "Today",
        "today",
        Icons.Rounded.Today
    ),
    Settings(
        "Settings",
        "settings",
        Icons.Rounded.Settings
    ),
}