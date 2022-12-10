package com.eferraz.mygooglesheetsconnector.feature.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
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

    val data by vm.uiState.collectAsState(initial = mutableMapOf())

    MyGoogleSheetsConnectorTheme {
        FixedIncomeListScreen(data = data, onBackClick = onBackClick)
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FixedIncomeListScreen(data: Map<String, List<FixedIncome>>, function: (() -> Unit)? = null, onBackClick: () -> Unit) {

    Scaffold(topBar = { MyTopAppBar(onBackClick) }, modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.padding(it)) {

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
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun MyTopAppBar(onBackClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Lista de Renda Fíxa", modifier = Modifier.padding(start = 16.dp)) },
        navigationIcon = { Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null, modifier = Modifier.clickable { onBackClick() }) })
}

@Composable
fun Greeting(name: String, function: (() -> Unit)? = null) {
    Text(text = name, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.clickable {
        function?.invoke()
    })
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    MyGoogleSheetsConnectorTheme {
        FixedIncomeListScreen(
            mapOf("2022 - 10" to mutableListOf<FixedIncome>().apply {
                repeat(5) { add(FixedIncome("2022", "10", "Fixed Income", "R$ 100,00", "R$ 1000,00")) }
            })
        ) {}
    }
}