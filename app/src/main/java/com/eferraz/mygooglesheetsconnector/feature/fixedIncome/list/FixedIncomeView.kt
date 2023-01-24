package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.list

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eferraz.mygooglesheetsconnector.archtectureImpl.utils.toCurrency
import com.eferraz.mygooglesheetsconnector.core.designsystem.theme.MyGoogleSheetsConnectorTheme
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import com.eferraz.mygooglesheetsconnector.feature.home.mockFixedIncomeList
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun FixedIncomeView(modifier: Modifier = Modifier, avatarLabel: String, primaryText: String, secondText: String, tertiaryText: String) {

    ElevatedCard(modifier = modifier.fillMaxWidth().padding(vertical = 4.dp)) {

        Row(modifier = Modifier.padding(top = 12.dp, bottom = 12.dp, start = 16.dp, end = 24.dp)) {

            Box(modifier = Modifier.clip(CircleShape).background(MaterialTheme.colorScheme.primaryContainer).height(45.dp).aspectRatio(1f)) {

                Text(
                    text = avatarLabel,
                    modifier = Modifier.align(Center),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            Column(modifier = Modifier.padding(start = 16.dp).align(CenterVertically)) {

                Text(
                    text = primaryText,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Row {

                    Text(
                        text = secondText,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text = tertiaryText,
                        modifier = modifier.weight(1f),
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun FixedIncomeView(modifier: Modifier = Modifier, data: FixedIncome) {
    FixedIncomeView(
        modifier = modifier,
        avatarLabel = data.type,
        primaryText = data.name,
        secondText = data.dueDate?.let { LocalDate.from(data.dueDate).format(DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy")) } ?: "Diária",
        tertiaryText = data.amount.toCurrency()
    )
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun FixedIncomeViewPreview() {
    MyGoogleSheetsConnectorTheme {
        FixedIncomeView(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            data = mockFixedIncomeList[0]
        )
    }
}