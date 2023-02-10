package com.eferraz.mygooglesheetsconnector.core.utils


import androidx.activity.ComponentActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.eferraz.mygooglesheetsconnector.feature.sync.ui.SyncGoogleSheetsWorkManager

fun ComponentActivity.startWorkManager() {
    WorkManager.getInstance(this).enqueue(OneTimeWorkRequestBuilder<SyncGoogleSheetsWorkManager>().build())
}