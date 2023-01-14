package com.eferraz.mygooglesheetsconnector.core.repositories

import android.icu.text.NumberFormat
import com.eferraz.googlesheets.data.data
import com.eferraz.googlesheets.providers.GenericSheetsDatasource
import com.eferraz.mygooglesheetsconnector.BuildConfig
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory
import java.time.LocalDate
import java.time.format.DateTimeFormatter

interface FixedIncomeSheetsRepository {
    fun get(): Flow<List<FixedIncome>>
}

@Factory
class FixedIncomeSheetsRepositoryImpl(
    private val datasource: GenericSheetsDatasource
) : FixedIncomeSheetsRepository {

    private val sheetsKey: String = BuildConfig.SHEET_KEY
    private val range: String = "'Histórico Renda Fixa'!2:1000"

    override fun get(): Flow<List<FixedIncome>> = datasource.get(sheetsKey, range).map { it.toFixedIncome() }

    private fun List<List<Any>>.toFixedIncome() = this.map { data ->
        FixedIncome(
            name = data.data('F'),
            year = data.data('B').toInt(),
            month = data.data('C').toInt(),
            bank = data.data('D'),
            type = data.data('E'),
            group = data.data('Q'),
            target = data.data('R'),
            dueDate = data.data('K').toDate(),
            liquidity = data.data('L'),
            investment = data.data('N').toDouble(),
            amount = data.data('O').toDouble(),
        )
    }

    private fun String.toDouble() = if (this.isNotBlank()) NumberFormat.getCurrencyInstance().parse(this).toDouble() else 0.0
    private fun String.toDate() = runCatching { LocalDate.parse(this, DateTimeFormatter.ofPattern("dd/MM/yyyy")) }.getOrNull()
}