package com.eferraz.mygooglesheetsconnector.feature.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eferraz.mygooglesheetsconnector.archtectureImpl.utils.toCurrency
import com.eferraz.mygooglesheetsconnector.core.designsystem.theme.MyGoogleSheetsConnectorTheme
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeRoute(viewModel: HomeViewModel = koinViewModel()) {

    val uiState by viewModel.uiState.collectAsState(initial = emptyList())

    MyGoogleSheetsConnectorTheme {
        HomeScreen(modifier = Modifier, uiState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(modifier: Modifier, data: List<FixedIncome>) {

    Scaffold(
        topBar = { LargeTopAppBar(title = { Text(text = "Meus Investimentos") }) },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            ContentScreen(Modifier, data)
        }
    }
}

@Composable
private fun ContentScreen(modifier: Modifier, data: List<FixedIncome>) {

    LazyColumn(modifier = modifier.padding(horizontal = 16.dp)) {
        items(data) {
            FixedIncomeInRelease(it)
        }
    }
}

@Composable
fun FixedIncomeInRelease(data: FixedIncome) {

    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {

        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                Text(text = data.name, overflow = TextOverflow.Ellipsis, maxLines = 1, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Row {
                Text(text = "Vencimento: ", overflow = TextOverflow.Ellipsis, maxLines = 1, modifier = Modifier.weight(1f).padding(end = 8.dp))
                Text(text = LocalDate.from(data.dueDate).format(DateTimeFormatter.ISO_DATE))
            }
            Row {
                Text(text = "Valor Líquido: ", overflow = TextOverflow.Ellipsis, maxLines = 1, modifier = Modifier.weight(1f).padding(end = 8.dp))
                Text(text = data.amount.toCurrency())
            }
            //Row {
            //    Text(text = "Valor Investido: ", overflow = TextOverflow.Ellipsis, maxLines = 1, modifier = Modifier.weight(1f).padding(end = 8.dp))
            //    Text(text = data.amount.toCurrency())
            // }
            //Row {
            //    Text(text = "Valorização: ", overflow = TextOverflow.Ellipsis, maxLines = 1, modifier = Modifier.weight(1f).padding(end = 8.dp))
            //    Text(text = "10%")
            // }
        }
    }
}


@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview
fun HomeRoutePreview() {
    MyGoogleSheetsConnectorTheme {
        HomeScreen(
            modifier = Modifier, listOf(
                FixedIncome(2023, 5, "CDB Vence Perto", LocalDate.parse("2023-06-15"), "No Venc", 0.0, 10_000.00),
                FixedIncome(2023, 5, "CDB Vence Perto", LocalDate.parse("2023-06-15"), "No Venc", 0.0, 10_000.00),
                FixedIncome(2023, 5, "CDB Vence Perto", LocalDate.parse("2023-06-15"), "No Venc", 0.0, 10_000.00),
                FixedIncome(2023, 5, "CDB Vence Perto", LocalDate.parse("2023-06-15"), "No Venc", 0.0, 10_000.00)
            )
        )
    }
}