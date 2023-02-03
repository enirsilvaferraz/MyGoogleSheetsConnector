package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.ui.list

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eferraz.mygooglesheetsconnector.core.designsystem.theme.MyGoogleSheetsConnectorTheme
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.usecases.GetFixedIncomeListUseCase
import com.eferraz.mygooglesheetsconnector.feature.home.ui.homeItemHeader
import org.koin.androidx.compose.koinViewModel

@Composable
fun FixedIncomeListRoute(vm: FixedIncomeListViewModel = koinViewModel(), onBackClick: () -> Unit) {

    val data by vm.uiState.collectAsState(initial = mutableListOf())

    MyGoogleSheetsConnectorTheme {
        FixedIncomeListScreen(data = data, onBackClick = onBackClick)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FixedIncomeListScreen(data: List<GetFixedIncomeListUseCase.Grouped>, onBackClick: () -> Unit) {

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = { Text(text = "Renda Fíxa") },
                navigationIcon = { IconButton(onClick = onBackClick) { Icon(Icons.Rounded.ArrowBack, "") } }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        ContentScreen(modifier = Modifier.padding(paddingValues), data)
    }
}

@Composable
private fun ContentScreen(modifier: Modifier, data: List<GetFixedIncomeListUseCase.Grouped>) {
    LazyColumn(modifier = modifier.padding(horizontal = 16.dp)) {
        data.forEach {
            homeItemHeader(it.title)
            items(items = it.list) { dataItem ->
                FixedIncomeView(data = dataItem)
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    MyGoogleSheetsConnectorTheme {
        FixedIncomeListScreen(
            data = arrayListOf<GetFixedIncomeListUseCase.Grouped>(),
            onBackClick = {}
        )
    }
}