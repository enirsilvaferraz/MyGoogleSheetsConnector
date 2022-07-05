package com.eferraz.mygooglesheetsconnector.ui.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.eferraz.mygooglesheetsconnector.GoogleSheetsViewModel
import com.eferraz.mygooglesheetsconnector.GoogleSheetsViewModel.UiState.*
import com.eferraz.mygooglesheetsconnector.entities.FixedIncome
import com.eferraz.googlesheets.GoogleSignInActivity
import com.eferraz.mygooglesheetsconnector.R
import com.eferraz.mygooglesheetsconnector.ui.theme.MyGoogleSheetsConnectorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleActivity : GoogleSignInActivity() {

    private val vm: GoogleSheetsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureGoogleSignIn(getString(R.string.default_web_client_id))

        setContent {

            MyGoogleSheetsConnectorTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                    when (val state = vm.uiState.collectAsState().value) {
                        is Loading -> {}
                        is Success -> Lista(state.data) { vm.append() }
                        is Failure -> state.intent?.let { registerThrowableResult.launch(it) }
                    }
                }
            }
        }
    }

    override fun onSignInReady() {
        vm.getData()
    }
}

@Composable
fun Lista(data: List<FixedIncome>, function: (() -> Unit)? = null) {
    LazyColumn {
        items(data) { dataItem ->
            Row {
                Column { Greeting(dataItem.col1, function) }
                Column { Greeting(dataItem.col2, function) }
                Column { Greeting(dataItem.col3, function) }
                Column { Greeting(dataItem.col4, function) }
                Column { Greeting(dataItem.col5, function) }
            }
        }
    }
}

@Composable
fun Greeting(name: String, function: (() -> Unit)? = null) {
    Text(text = name, Modifier.clickable {
        function?.invoke()
    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyGoogleSheetsConnectorTheme {
        Greeting("Android") {}
    }
}

