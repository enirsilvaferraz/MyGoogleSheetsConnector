package com.eferraz.mygooglesheetsconnector.app

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class AppApplication @Inject constructor() : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration() = Configuration.Builder().setMinimumLoggingLevel(Log.INFO).setWorkerFactory(workerFactory).build()
}