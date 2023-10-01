package com.boostyboys.mcs.state

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface StateHandler<S, V, A, E> {
    val state: StateFlow<S>
    val viewState: StateFlow<V>
    val effect: SharedFlow<E>

    fun handleAction(action: A) {}
    suspend fun updateState(transform: S.() -> S)
    suspend fun updateViewState(transform: V.() -> V)
    suspend fun emitEffect(effect: E)
}
