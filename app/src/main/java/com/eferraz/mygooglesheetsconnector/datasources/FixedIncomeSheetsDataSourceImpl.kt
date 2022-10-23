package com.eferraz.mygooglesheetsconnector.datasources

import com.eferraz.finance.domain.archtecture.DomainResponse.Companion.result
import com.eferraz.finance.domain.archtecture.GenericReadableDataSource
import com.eferraz.finance.domain.datasources.EnvironmentDataSource
import com.eferraz.finance.domain.entities.FixedIncome
import com.eferraz.googlesheets.data
import com.eferraz.googlesheets.datasources.SheetsProvider
import com.eferraz.googlesheets.datasources.SheetsResponse
import javax.inject.Inject

class FixedIncomeSheetsDataSourceImpl @Inject constructor(
    private val sheetsProvider: SheetsProvider,
    private val environmentDataSource: EnvironmentDataSource
) : GenericReadableDataSource<@JvmSuppressWildcards List<FixedIncome>> {

    private val RANGE: String = "'Histórico Renda Fixa'!A2:Z1000"

    override suspend fun get() = result {
        when (val response = sheetsProvider.get(environmentDataSource.sheetKey, RANGE)) {
            is SheetsResponse.Failure -> throw response.result
            is SheetsResponse.Success -> response.result.toResult()
        }
    }

    private fun List<List<Any>>.toResult(): List<FixedIncome> = this.map {
        FixedIncome(
            col1 = runCatching { it.data('B') }.getOrNull().orEmpty(),
            col2 = runCatching { it.data('C') }.getOrNull().orEmpty(),
            col3 = runCatching { it.data('F') }.getOrNull().orEmpty(),
            col4 = runCatching { it.data('O') }.getOrNull().orEmpty(),
            col5 = runCatching { it.data('W') }.getOrNull().orEmpty(),
        )
    }
}


