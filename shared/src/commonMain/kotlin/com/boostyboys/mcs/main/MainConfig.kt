package com.boostyboys.mcs.main

sealed interface MainViewState {
    data object Loading : MainViewState
    data object Content : MainViewState
}

sealed interface MainAction {
    data object Initialize : MainAction
}
