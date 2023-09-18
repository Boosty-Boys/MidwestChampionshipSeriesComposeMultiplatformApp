
package app.mcs

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.com.mcs.Main
import app.com.mcs.di.initKoin
import java.awt.Dimension

private const val WINDOW_MIN_WIDTH = 700
private const val WINDOW_MIN_HEIGHT = 1000

fun main() = application {
    initKoin()

    Window(
        onCloseRequest = {
            exitApplication()
        },
    ) {
        window.minimumSize = Dimension(
            WINDOW_MIN_WIDTH,
            WINDOW_MIN_HEIGHT,
        )
        Main()
    }
}
