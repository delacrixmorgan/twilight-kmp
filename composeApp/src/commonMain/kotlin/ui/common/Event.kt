package ui.common

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.MutableSharedFlow

open class Event<out T>(private val content: T) {

    private var hasBeenHandled = false

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}

suspend inline fun <T> MutableSharedFlow<Event<T>>.observeEvent(
    lifecycleOwner: LifecycleOwner,
    noinline onEventUnhandledContent: (T) -> Unit
) {
    flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED).collect {
        it.getContentIfNotHandled()?.let(onEventUnhandledContent)
    }
}

suspend fun MutableSharedFlow<Event<Unit>>.triggerEvent() {
    emit(Event(Unit))
}

suspend fun <T> MutableSharedFlow<Event<T>>.triggerEvent(data: T) {
    emit(Event(data))
}
