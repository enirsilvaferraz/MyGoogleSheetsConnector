package com.eferraz.mygooglesheetsconnector.feature.home

import android.os.Bundle
import androidx.activity.ComponentActivity
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.eferraz.googlesheets.data.SheetsException.Companion.resolve
import com.eferraz.googlesheets.providers.GoogleInstanceProviderImpl
import com.eferraz.mygooglesheetsconnector.archtecture.DomainResponse
import com.eferraz.mygooglesheetsconnector.core.designsystem.theme.MyGoogleSheetsConnectorTheme
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FixedIncomeActivity : ComponentActivity() {

    @Inject
    lateinit var provider: GoogleInstanceProviderImpl

    private val vm: FixedIncomeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MyGoogleSheetsConnectorTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                    when (val value = vm.uiState.collectAsState(initial = null).value) {
                        is DomainResponse.Success -> Lista(value.result) {}
                        is DomainResponse.Failure -> provider.handleError(this, value.result.resolve().intent)
                        null -> {}
                    }
                }
            }
        }

    }
}

@Composable
fun Lista(data: List<FixedIncome>, function: (() -> Unit)? = null) {
    LazyColumn {
        items(data) { dataItem ->
            Row {
                Column(Modifier.weight(.1f)) { Greeting(dataItem.year, function) }
                Column(Modifier.weight(.05f)) { Greeting(dataItem.month, function) }
                Column(Modifier.weight(.55f)) { Greeting(dataItem.name, function) }
                Column(Modifier.weight(.2f)) { Greeting(dataItem.investment, function) }
                //Column(Modifier.weight(.2f)) { Greeting(dataItem.col5, function) }
            }
        }
    }
}

@Composable
fun Greeting(name: String, function: (() -> Unit)? = null) {
    Text(text = name, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.clickable {
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

