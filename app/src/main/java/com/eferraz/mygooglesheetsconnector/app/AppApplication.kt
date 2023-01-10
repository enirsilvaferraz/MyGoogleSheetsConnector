package com.eferraz.mygooglesheetsconnector.app

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.eferraz.mygooglesheetsconnector.di.KoinModules
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import javax.inject.Inject

@HiltAndroidApp
class AppApplication @Inject constructor() : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration() = Configuration.Builder().setMinimumLoggingLevel(Log.INFO).setWorkerFactory(workerFactory).build()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@AppApplication)
            // Load modules
            modules(KoinModules.modules)
        }
    }
}