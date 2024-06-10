package com.boostyboys.mcs

import android.app.Application
import com.boostyboys.mcs.di.initKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
