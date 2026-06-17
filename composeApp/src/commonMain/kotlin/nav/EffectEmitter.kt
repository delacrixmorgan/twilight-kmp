package nav

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class EffectEmitter<T> {
    private val _effects = Channel<T>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()
    fun send(effect: T) {
        _effects.trySend(effect)
    }
}
