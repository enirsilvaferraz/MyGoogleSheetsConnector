package com.eferraz.mygooglesheetsconnector.app

import android.app.Application
import com.eferraz.mygooglesheetsconnector.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module


class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@AppApplication)
            // Load modules
            modules(AppModules().module)
        }
    }
}