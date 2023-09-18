import androidx.compose.ui.window.ComposeUIViewController
import com.boostyboys.mcs.Main
import com.boostyboys.mcs.di.initKoin

@Suppress("Unused")
fun MainViewController(): platform.UIKit.UIViewController {
    initKoin()
    return ComposeUIViewController {
        Main()
    }
}
