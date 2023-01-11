package com.eferraz.mygooglesheetsconnector.feature.login


import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.eferraz.googlesheets.providers.GoogleSignInActivityAbs
import com.eferraz.mygooglesheetsconnector.feature.home.MainActivity
import com.eferraz.mygooglesheetsconnector.feature.sync.SyncGoogleSheetsWorkManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GoogleSignInActivity : GoogleSignInActivityAbs() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoginRoute(vm = vm, onFinish = ::onLoginFinished)
        }
    }

    private fun onLoginFinished() {
        startWorkManager()
        lifecycleScope.launch {
            delay(1500)
            startActivity(Intent(this@GoogleSignInActivity, MainActivity::class.java))
            finish()
        }
    }

    private fun startWorkManager() {
        WorkManager.getInstance(this).enqueue(OneTimeWorkRequestBuilder<SyncGoogleSheetsWorkManager>().build())
    }
}