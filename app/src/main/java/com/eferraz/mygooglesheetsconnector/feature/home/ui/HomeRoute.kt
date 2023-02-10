package com.eferraz.mygooglesheetsconnector.feature.home.ui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eferraz.mygooglesheetsconnector.core.designsystem.theme.MyGoogleSheetsConnectorTheme
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models.FixedIncome
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models.FixedIncomeAndHistory
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models.FixedIncomeHistory
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.ui.list.FixedIncomeView
import com.eferraz.mygooglesheetsconnector.feature.home.domain.GetHomeDataUseCase
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@Composable
fun HomeRoute(viewModel: HomeViewModel = koinViewModel(), onFixedIncomeHeaderClicked: () -> Unit) {

    val uiState by viewModel.uiState.collectAsState(initial = listOf())
    val message by viewModel.message.collectAsState()

    MyGoogleSheetsConnectorTheme {
        HomeScreen(modifier = Modifier, uiState, onFixedIncomeHeaderClicked, onSyncClicked = viewModel::onSyncClicked)
    }

    if (message.isNotBlank()) {
        val current = LocalContext.current
        LaunchedEffect(key1 = message, block = {
            Toast.makeText(current, message, Toast.LENGTH_SHORT).show()
        })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    modifier: Modifier,
    data: List<GetHomeDataUseCase.HomeItem>,
    onFixedIncomeHeaderClicked: () -> Unit,
    onSyncClicked: () -> Unit
) {

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = "Meus Investimentos",
                        textAlign = TextAlign.Center
                    )
                }, actions = {
                    IconButton(onClick = onSyncClicked) {
                        Icon(imageVector = Icons.Outlined.Sync, contentDescription = null)
                    }
                })
        },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        ContentScreen(Modifier.padding(paddingValues), data, onFixedIncomeHeaderClicked)
    }
}

@Composable
private fun ContentScreen(modifier: Modifier, data: List<GetHomeDataUseCase.HomeItem>, onFixedIncomeHeaderClicked: () -> Unit) {

    LazyColumn(modifier = modifier.padding(horizontal = 16.dp)) {
        data.forEach {
            homeItemHeader(it.title)
            items(it.data) {
                when (it) {
                    is FixedIncomeAndHistory -> FixedIncomeView(data = it)
                }
            }
            homeItemFooter(onFixedIncomeHeaderClicked)
        }
    }
}

private fun LazyListScope.homeItemFooter(onHeaderClicked: () -> Unit) {
    item {
        Column(modifier = Modifier.fillMaxWidth()) {
            TextButton(onClick = onHeaderClicked, modifier = Modifier.align(End)) {
                Row {

                    Text(text = "Ver mais", overflow = TextOverflow.Ellipsis, maxLines = 1, fontSize = 14.sp)

                    Icon(
                        imageVector = Icons.Rounded.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 8.dp).size(20.dp).align(CenterVertically)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.homeItemHeader(title: String) {
    stickyHeader {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.End,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface).padding(top = 24.dp, bottom = 16.dp)
        )
    }
}


@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES, showSystemUi = true)
@Preview(uiMode = UI_MODE_NIGHT_NO, showSystemUi = true)
fun HomeRoutePreview() {
    MyGoogleSheetsConnectorTheme {
        HomeScreen(
            modifier = Modifier,
            data = mockHomeItemList,
            onFixedIncomeHeaderClicked = {},
            onSyncClicked = {}
        )
    }
}

val mockFixedIncomeList = arrayListOf<FixedIncomeAndHistory>().apply {
    repeat(5) {
        add(
            FixedIncomeAndHistory(
                fixedIncome = FixedIncome(
                    uuid = "A",
                    name = "CDB de 120% do CDI do Banco Máxima",
                    bank = "NuInvest",
                    type = "CDB",
                    dueDate = LocalDate.now(),
                    liquidity = "Diária"
                ),
                history = FixedIncomeHistory(
                    year = 2023,
                    month = 1,
                    fixedIncome = "A",
                    amount = 1000.0,
                    investment = 100.0,
                    target = "Aposentadoria"
                )
            )
        )
    }
}

val mockHomeItemList = listOf(
    GetHomeDataUseCase.HomeItem.FixedIncomeLiquidity(mockFixedIncomeList)
)
