package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import kotlinx.serialization.Serializable
import ui.dashboard.DashboardViewModel
import ui.form.FormSelectionViewModel

interface BaseRootComponent : BackHandlerOwner {
    val childStack: Value<ChildStack<Configuration, Child>>
    fun onBackClicked()
}

class RootComponent(
    componentContext: ComponentContext,
) : ComponentContext by componentContext, BaseRootComponent {
    private val navigation = StackNavigation<Configuration>()
    override val childStack: Value<ChildStack<Configuration, Child>> = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.DashboardScreen,
        handleBackButton = true,
        childFactory = ::createChild
    )

    override fun onBackClicked() {
        navigation.pop()
    }

    @OptIn(ExperimentalDecomposeApi::class)
    private fun createChild(
        config: Configuration,
        context: ComponentContext
    ): Child {
        return when (config) {
            Configuration.DashboardScreen -> Child.Dashboard(
                DashboardViewModel(
                    componentContext = context,
                    openFormSelectionScreen = {
                        navigation.pushNew(Configuration.FormSelectionScreen)
                    },
                )
            )
            Configuration.FormSelectionScreen -> Child.FormSelection(
                FormSelectionViewModel(
                    componentContext = context,
                )
            )
        }
    }
}

sealed class Child {
    data class Dashboard(val viewModel: DashboardViewModel) : Child()
    data class FormSelection(val viewModel: FormSelectionViewModel) : Child()
}

@Serializable
sealed class Configuration {
    data object DashboardScreen : Configuration()
    data object FormSelectionScreen : Configuration()
}