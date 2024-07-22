package com.delacrixmorgan.twilight

import App
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import data.model.TwilightTheme
import data.preferences.PreferencesRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ui.theme.AppTheme

class MainActivity : ComponentActivity(), KoinComponent {
    private val preferences: PreferencesRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val theme = remember { mutableStateOf(TwilightTheme.Default) }
            AppTheme(theme.value) {
                Scaffold {
                    val insetModifier = Modifier
                        .windowInsetsPadding(WindowInsets.displayCutout)
                        .consumeWindowInsets(it)
                    App(insetModifier)
                }
            }
            val view = LocalView.current
            SideEffect {
                val window = (view.context as ComponentActivity).window
                val isDarkTheme = when (theme.value) {
                    TwilightTheme.System -> isSystemInDarkTheme()
                    TwilightTheme.Light -> false
                    TwilightTheme.Dark -> true
                }
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDarkTheme
            }
            val lifecycleOwner = LocalLifecycleOwner.current
            LaunchedEffect(LocalLifecycleOwner.current) {
                lifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                    preferences.getTheme().collect { theme.value = it }
                }
            }
        }
    }

    private fun isSystemInDarkTheme(): Boolean {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}