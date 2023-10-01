package com.boostyboys.mcs.state

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class StateHandlerDelegate<S : Any, V : Any, A : Any, E : Any>(
    initialState: S,
    initialViewState: V
) : StateHandler<S, V, A, E> {

    private val _state = MutableStateFlow(initialState)
    override val state: StateFlow<S> get() = _state

    private val _viewState = MutableStateFlow(initialViewState)
    override val viewState: StateFlow<V> get() = _viewState

    private val _effect = MutableSharedFlow<E>()
    override val effect: SharedFlow<E> get() = _effect
    
    override suspend fun updateState(transform: S.() -> S) {
        _state.emit(transform(state.value))
    }

    override suspend fun updateViewState(transform: V.() -> V) {
        _viewState.emit(transform(viewState.value))
    }

    override suspend fun emitEffect(effect: E) {
        _effect.emit(effect)
    }
}