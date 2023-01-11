package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.list

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eferraz.mygooglesheetsconnector.archtectureImpl.utils.toCurrency
import com.eferraz.mygooglesheetsconnector.core.designsystem.theme.MyGoogleSheetsConnectorTheme
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@Composable
fun FixedIncomeListRoute(vm: FixedIncomeListViewModel = koinViewModel(), onBackClick: () -> Unit) {

    val data by vm.uiState.collectAsState(initial = mutableListOf())

    MyGoogleSheetsConnectorTheme {
        FixedIncomeListScreen(data = data, onBackClick = onBackClick)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FixedIncomeListScreen(data: List<FixedIncome>, onBackClick: () -> Unit) {

    Scaffold(
        topBar = { LargeTopAppBar(title = { Text(text = "Renda Fíxa") }) },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            ContentScreen(data)
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun ContentScreen(data: List<FixedIncome>) {

    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
        items(items = data) { dataItem ->
            Row {
                Text(
                    text = dataItem.name,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.weight(1f).padding(end = 8.dp)
                )
                Text(text = dataItem.amount.toCurrency())
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
            data = arrayListOf<FixedIncome>().apply {
                repeat(6) {
                    add(FixedIncome(2022, 10, "Fixed Income com nome grande", LocalDate.parse("2023-10-01"), "No Venc", 100.00, 10_000.00))
                }
            },
            onBackClick = {}
        )
    }
}