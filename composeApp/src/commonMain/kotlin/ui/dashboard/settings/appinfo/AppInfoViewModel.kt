package ui.dashboard.settings.appinfo

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import nav.EffectEmitter
import nav.NavEffect
import org.koin.core.component.KoinComponent

class AppInfoViewModel : ViewModel(), KoinComponent {
    private val emitter = EffectEmitter<NavEffect>()
    val effects = emitter.effects

    fun onAction(navHostController: NavHostController, action: AppInfoAction) {
        when (action) {
            is AppInfoAction.OpenDeveloper -> emitter.send(NavEffect.OpenUri("https://github.com/delacrixmorgan"))
            is AppInfoAction.OpenSourceCode -> emitter.send(NavEffect.OpenUri("https://github.com/delacrixmorgan/twilight-kmp"))
            AppInfoAction.GoBack -> navHostController.navigateUp()
        }
    }
}

sealed interface AppInfoAction {
    data object OpenDeveloper : AppInfoAction
    data object OpenSourceCode : AppInfoAction

    data object GoBack : AppInfoAction
}