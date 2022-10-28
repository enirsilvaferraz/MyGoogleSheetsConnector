package com.eferraz.mygooglesheetsconnector.feature.login


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.eferraz.googlesheets.providers.GoogleInstanceProviderImpl
import com.eferraz.mygooglesheetsconnector.core.designsystem.theme.MyGoogleSheetsConnectorTheme
import com.eferraz.mygooglesheetsconnector.feature.home.FixedIncomeActivity
import com.eferraz.mygooglesheetsconnector.feature.sync.SyncGoogleSheetsWorkManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GoogleSignInActivity : ComponentActivity() {

    @Inject
    lateinit var provider: GoogleInstanceProviderImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyGoogleSheetsConnectorTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Screen()
                }
            }
        }

        provider.login(this) {
            startWorkManager()
            startActivity(Intent(this, FixedIncomeActivity::class.java))
            finish()
        }
    }

    private fun startWorkManager() {
        WorkManager.getInstance(this).enqueue(OneTimeWorkRequestBuilder<SyncGoogleSheetsWorkManager>().build())
    }
}

@Composable
fun Screen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center, text = "Log user in...")
    }
}

@Preview(showBackground = true)
@Composable
fun GoogleSignInActivityPreview() {
    MyGoogleSheetsConnectorTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Screen()
        }
    }
}