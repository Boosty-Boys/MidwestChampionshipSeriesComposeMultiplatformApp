package com.boostyboys.mcs.state

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface StateHandler<S : Any, V : Any, A : Any, E : Any> {
    val state: StateFlow<S>
    val viewState: StateFlow<V>
    val effect: SharedFlow<E>

    fun handleAction(action: A) {
        proxy?.receivedActions?.add(action)
    }
    suspend fun updateState(transform: S.() -> S)
    suspend fun updateViewState(transform: V.() -> V)
    suspend fun emitEffect(effect: E)

    // for testing handleAction invocations purposes only
    var proxy: HandleActionProxy<A>?
    fun enableProxy() {
        proxy = HandleActionProxy()
    }
}
