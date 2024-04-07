import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.PredictiveBackGestureIcon
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.PredictiveBackGestureOverlay
import com.arkivanov.essenty.backhandler.BackDispatcher
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import navigation.RootComponent

fun MainViewController() = ComposeUIViewController {
    val backDispatcher = BackDispatcher()
    val componentContext = remember {
        RootComponent(
            DefaultComponentContext(
                lifecycle = LifecycleRegistry(),
                backHandler = backDispatcher
            )
        )
    }

    PredictiveBackGestureOverlay(
        modifier = Modifier.fillMaxSize(),
        backDispatcher = backDispatcher,
        backIcon = { progress, _ ->
            PredictiveBackGestureIcon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                progress = progress,
            )
        },
    ) {
        App(componentContext)
    }
}