import androidx.compose.ui.window.ComposeUIViewController
import app.com.mcs.Main
import app.com.mcs.di.initKoin

@Suppress("Unused")
fun MainViewController(): platform.UIKit.UIViewController {
    initKoin()
    return ComposeUIViewController {
        Main()
    }
}
