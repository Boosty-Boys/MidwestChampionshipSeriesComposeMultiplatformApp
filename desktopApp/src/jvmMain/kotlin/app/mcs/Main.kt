
package app.mcs

import MainView
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.awt.Dimension

private const val WINDOW_MIN_WIDTH = 700
private const val WINDOW_MIN_HEIGHT = 1000

fun main() = application {
    Window(
        onCloseRequest = {
            exitApplication()
        },
    ) {
        window.minimumSize = Dimension(
            WINDOW_MIN_WIDTH,
            WINDOW_MIN_HEIGHT,
        )
        MainView()
    }
}
