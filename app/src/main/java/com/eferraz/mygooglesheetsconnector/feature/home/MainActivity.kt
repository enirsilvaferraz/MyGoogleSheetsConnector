package com.eferraz.mygooglesheetsconnector.feature.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eferraz.googlesheets.data.SheetsException.Companion.resolve
import com.eferraz.googlesheets.providers.GoogleInstanceProviderImpl
import com.eferraz.mygooglesheetsconnector.core.designsystem.theme.MyGoogleSheetsConnectorTheme
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

import androidx.compose.runtime.*
import androidx.compose.ui.platform.*
import androidx.lifecycle.*

@Composable
fun LifecycleAwareObserver(observer: LifecycleObserver) {

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(observer, lifecycleOwner) {

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var provider: GoogleInstanceProviderImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FixedIncomeRoute()
        }
    }

    @Composable
    private fun RunSafe(function: @Composable () -> Unit): Unit = runCatching {
        function()
    }.getOrElse {
        provider.handleError(this, it.resolve().intent)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FixedIncomeRoute(vm: FixedIncomeListViewModel = viewModel()) {

    LifecycleAwareObserver(observer = vm)

    val data by vm.uiState.collectAsState(initial = mutableMapOf())

    MyGoogleSheetsConnectorTheme {
        Scaffold {_ ->
            Lista(data = data)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Lista(data: Map<String, List<FixedIncome>>, function: (() -> Unit)? = null) {
    LazyColumn {
        data.forEach { (section, listItem) ->
            stickyHeader {
                Greeting(name = section)
            }
            items(items = listItem) { dataItem ->
                Row {
                    Column(Modifier.weight(.50f)) { Greeting(dataItem.name, function) }
                    Column(Modifier.weight(.25f)) { Greeting(dataItem.investment, function) }
                    Column(Modifier.weight(.25f)) { Greeting(dataItem.amount, function) }
                }
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

