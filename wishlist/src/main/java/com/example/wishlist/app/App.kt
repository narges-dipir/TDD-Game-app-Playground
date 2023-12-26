package com.example.wishlist.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (GlobalContext.getOrNull() == null) {
            startKoin {
                // declare Android context
                androidContext(this@App)
                // declare used modules
                modules(appModule)
            }
        }
    }
}
