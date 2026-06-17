package nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import kotlinx.coroutines.flow.Flow

@Composable
fun NavEffectHandler(
    effects: Flow<NavEffect>,
    uriHandler: UriHandler = LocalUriHandler.current,
) {
    LaunchedEffect(Unit) {
        effects.collect { effect ->
            when (effect) {
                is NavEffect.OpenUri -> uriHandler.openUri(effect.url)
            }
        }
    }
}
