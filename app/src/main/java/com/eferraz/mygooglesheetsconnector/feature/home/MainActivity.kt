package com.eferraz.mygooglesheetsconnector.feature.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.*
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.*
import com.eferraz.mygooglesheetsconnector.core.designsystem.theme.MyGoogleSheetsConnectorTheme
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import com.eferraz.mygooglesheetsconnector.feature.AppNavigation
import dagger.hilt.android.AndroidEntryPoint

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FixedIncomeRoute(vm: FixedIncomeListViewModel = hiltViewModel(), onBackClick: () -> Unit) {

   // LifecycleAwareObserver(observer = vm)

    val data by vm.uiState.collectAsState(initial = mutableMapOf())

    MyGoogleSheetsConnectorTheme {
        Scaffold {_ ->
            Lista(data = data, onBackClick = onBackClick)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Lista(data: Map<String, List<FixedIncome>>, function: (() -> Unit)? = null, onBackClick: ()->Unit) {

    Column {
        Text(text = "Voltar", modifier = Modifier.fillMaxWidth().padding(16.dp).clickable {
            onBackClick()
        })

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