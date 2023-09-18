package app.com.mcs

import androidx.compose.runtime.Composable
import app.com.mcs.ui.SampleAppToDelete
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition

@Composable fun Main() {
    Navigator(SampleAppToDelete()) { navigator ->
        SlideTransition(navigator)
    }
}
