package com.boostyboys.mcs

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.boostyboys.mcs.main.MainScreen

@Composable fun Main() {
//    Navigator(DemoScreen())
    Navigator(MainScreen())
}
