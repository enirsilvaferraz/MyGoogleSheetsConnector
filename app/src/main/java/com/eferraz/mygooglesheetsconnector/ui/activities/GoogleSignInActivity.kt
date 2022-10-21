package com.eferraz.mygooglesheetsconnector.ui.activities


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
import com.eferraz.googlesheets.providers.SheetsInstanceProvider
import com.eferraz.mygooglesheetsconnector.ui.theme.MyGoogleSheetsConnectorTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GoogleSignInActivity : ComponentActivity() {

    @Inject
    lateinit var provider: SheetsInstanceProvider

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
            startActivity(Intent(this, SampleActivity::class.java))
            finish()
        }
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