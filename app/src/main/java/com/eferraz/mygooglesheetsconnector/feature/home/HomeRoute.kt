package com.eferraz.mygooglesheetsconnector.feature.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eferraz.mygooglesheetsconnector.core.designsystem.theme.MyGoogleSheetsConnectorTheme
import com.eferraz.mygooglesheetsconnector.core.model.BaseModel
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.list.FixedIncomeView
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoute(viewModel: HomeViewModel = koinViewModel(), onFixedIncomeHeaderClicked: () -> Unit) {

    val uiState by viewModel.uiState.collectAsState(initial = hashMapOf())

    MyGoogleSheetsConnectorTheme {
        HomeScreen(modifier = Modifier, uiState, onFixedIncomeHeaderClicked)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(modifier: Modifier, data: Map<String, List<BaseModel>>, onFixedIncomeHeaderClicked: () -> Unit) {

    Scaffold(
        topBar = {
            LargeTopAppBar(title = {
                Text(
                    text = "Meus Investimentos",
                    textAlign = TextAlign.Center
                )
            })
        },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            ContentScreen(Modifier, data, onFixedIncomeHeaderClicked)
        }
    }
}

@Composable
private fun ContentScreen(modifier: Modifier, data: Map<String, List<BaseModel>>, onFixedIncomeHeaderClicked: () -> Unit) {

    LazyColumn(modifier = modifier.padding(horizontal = 16.dp)) {

        data.forEach {
            when (it.key) {
                "FixedIncome" -> homeItem(
                    title = "Renda fixa a vencer esse ano",
                    data = it.value,
                    onHeaderClicked = onFixedIncomeHeaderClicked
                ) { FixedIncomeView(it as FixedIncome) }
            }
        }
    }
}

private fun LazyListScope.homeItem(
    title: String,
    data: List<BaseModel>,
    onHeaderClicked: () -> Unit,
    template: @Composable (BaseModel) -> Unit
) {
    item {
        Text(
            text = title,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
            textAlign = TextAlign.End
        )
    }

    items(data) {
        template(it)
    }

    item {
        Column(modifier = Modifier.fillMaxWidth()) {
            TextButton(onClick = onHeaderClicked, modifier = Modifier.align(End)) {
                Row {
                    Text(
                        text = "Ver mais",
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        fontSize = 14.sp,
                        //fontWeight = FontWeight.Bold
                    )

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


@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview
fun HomeRoutePreview() {
    MyGoogleSheetsConnectorTheme {
        HomeScreen(
            modifier = Modifier,
            data = mutableListOf<FixedIncome>(
                //FixedIncome(2023, 5, "CDB Vence Perto", LocalDate.parse("2023-06-15"), "No Venc", 0.0, 10_000.00),
                //FixedIncome(2023, 5, "CDB Vence Perto", LocalDate.parse("2023-06-15"), "No Venc", 0.0, 10_000.00)
            ).groupBy { it::class.simpleName.orEmpty() },
            onFixedIncomeHeaderClicked = {}
        )
    }
}