import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import data.preferences.PreferencesRepositoryImpl
import data.preferences.model.ThemePreference
import ui.theme.AppTheme

fun MainViewController() = ComposeUIViewController {
    val theme = remember { mutableStateOf(ThemePreference.Default) }
    val preferences = PreferencesRepositoryImpl()

    AppTheme(theme.value) {
        Scaffold {
            val insetModifier = Modifier
                .windowInsetsPadding(WindowInsets.displayCutout)
                .consumeWindowInsets(it)
            App(insetModifier)
        }
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(LocalLifecycleOwner.current) {
        lifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            preferences.getTheme().collect { theme.value = it }
        }
    }
}