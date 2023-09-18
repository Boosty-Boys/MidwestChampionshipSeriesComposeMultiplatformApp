package app.com.mcs.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class SampleAppToDelete(val tabTitle: String) : Screen {

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<SampleAppScreenModel>()
        val count = screenModel.count.collectAsState().value

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Hello screen $tabTitle")

            Button(
                onClick = {
                    screenModel.increment()
                },
            ) {
                Text("Click to increment")
            }

            Text(count.value.toString())

            val navigator = LocalNavigator.currentOrThrow

            Button(
                onClick = {
                    navigator.push(ScreenTwo())
                },
            ) {
                Text("Go To Screen Two")
            }
        }
    }
}
