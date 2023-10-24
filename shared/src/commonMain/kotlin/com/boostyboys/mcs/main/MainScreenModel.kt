package com.boostyboys.mcs.main

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.boostyboys.mcs.data.api.McsRepository
import com.boostyboys.mcs.state.StateHandler
import com.boostyboys.mcs.state.StateHandlerDelegate
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainScreenModel(
    private val mcsRepository: McsRepository,
    private val dispatcher: CoroutineDispatcher,
) : ScreenModel,
    StateHandler<Any, MainViewState, MainAction, Any>
    by StateHandlerDelegate(Any(), MainViewState.Loading) {
    override fun handleAction(action: MainAction) {
        super.handleAction(action)
        coroutineScope.launch(dispatcher) {
            when (action) {
                is MainAction.Initialize -> {
                    initialize()
                }
            }
        }
    }

    private suspend fun initialize() {
        mcsRepository.reloadLeagueSeasonConfig()
        updateViewState { MainViewState.Content }
    }
}
