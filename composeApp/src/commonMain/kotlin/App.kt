import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.StackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.essenty.backhandler.BackHandler
import navigation.Child
import navigation.RootComponent
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.dashboard.DashboardScreen
import ui.form.FormSelectionScreen

@Composable
@Preview
fun App(componentContext: RootComponent) {
    MaterialTheme {
        val childStack by componentContext.childStack.subscribeAsState()
        Children(
            stack = childStack,
            animation = backAnimation(
                backHandler = componentContext.backHandler,
                onBack = componentContext::onBackClicked
            )
        ) { child ->
            when (val instance = child.instance) {
                is Child.Dashboard -> DashboardScreen(instance.viewModel)
                is Child.FormSelection -> FormSelectionScreen(instance.viewModel)
            }
        }
    }
}

expect fun <C : Any, T : Any> backAnimation(
    backHandler: BackHandler,
    onBack: () -> Unit,
): StackAnimation<C, T>