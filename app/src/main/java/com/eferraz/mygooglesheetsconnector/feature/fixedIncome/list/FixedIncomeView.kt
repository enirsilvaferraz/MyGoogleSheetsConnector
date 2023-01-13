package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eferraz.mygooglesheetsconnector.archtectureImpl.utils.toCurrency
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun FixedIncomeView(data: FixedIncome) {

    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {

        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                Text(text = data.name, overflow = TextOverflow.Ellipsis, maxLines = 1, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Row {
                Text(text = "Vencimento: ", overflow = TextOverflow.Ellipsis, maxLines = 1, modifier = Modifier.weight(1f).padding(end = 8.dp))
                Text(text = data.dueDate?.let { LocalDate.from(it).format(DateTimeFormatter.ISO_DATE) } ?: "")
            }
            Row {
                Text(text = "Valor Líquido: ", overflow = TextOverflow.Ellipsis, maxLines = 1, modifier = Modifier.weight(1f).padding(end = 8.dp))
                Text(text = data.amount.toCurrency())
            }
            //Row {
            //    Text(text = "Valor Investido: ", overflow = TextOverflow.Ellipsis, maxLines = 1, modifier = Modifier.weight(1f).padding(end = 8.dp))
            //    Text(text = data.investment.toCurrency())
            // }
            //Row {
            //    Text(text = "Valorização: ", overflow = TextOverflow.Ellipsis, maxLines = 1, modifier = Modifier.weight(1f).padding(end = 8.dp))
            //    Text(text = "10%")
            // }
        }
    }
}