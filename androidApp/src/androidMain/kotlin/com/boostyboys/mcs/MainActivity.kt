package com.boostyboys.mcs

import MainView
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.toArgb
import com.boostyboys.mcs.designsystem.api.theme.McsTheme
import com.boostyboys.mcs.di.initKoin

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKoin()

        setContent {
            McsTheme {
                window.statusBarColor = McsTheme.colors.background.toArgb()
                MainView()
            }
        }
    }
}
