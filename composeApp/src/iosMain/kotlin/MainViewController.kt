import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import ui.theme.AppTheme

fun MainViewController() = ComposeUIViewController {
    AppTheme {
        Scaffold {
            val insetModifier = Modifier
                .windowInsetsPadding(WindowInsets.displayCutout)
                .consumeWindowInsets(it)
            App(insetModifier)
        }
    }
}