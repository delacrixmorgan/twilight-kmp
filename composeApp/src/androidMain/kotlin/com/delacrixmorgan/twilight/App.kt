package com.delacrixmorgan.twilight

import AppContext
import android.app.Application
import di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppContext.init(applicationContext)

        initKoin {
            androidContext(this@App)
            androidLogger()
        }
    }
}