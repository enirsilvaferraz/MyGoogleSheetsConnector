package com.eferraz.mygooglesheetsconnector.core.network

import com.eferraz.googlesheets.data.SheetsResponse
import com.eferraz.googlesheets.data.data
import com.eferraz.googlesheets.providers.SheetsProvider
import com.eferraz.mygooglesheetsconnector.archtecture.DomainResponse.Companion.result
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import javax.inject.Inject

class FixedIncomeRemoteDataSourceImpl @Inject constructor(
    private val sheetsProvider: SheetsProvider, private val environmentDataSource: EnvironmentDataSource
) : BaseReadableDataSource<MutableList<FixedIncome>> {

    private val RANGE: String = "'Histórico Renda Fixa'!A2:Z1000"

    override suspend fun get() = result {
        when (val response = sheetsProvider.get(environmentDataSource.sheetKey, RANGE)) {
            is SheetsResponse.Failure -> throw response.result
            is SheetsResponse.Success -> response.result.toResult()
        }
    }

    private fun List<List<Any>>.toResult(): MutableList<FixedIncome> = this.map {
        FixedIncome(
            name = it.data('F'),
            year = it.data('B'),
            month = it.data('C'),
            investment = it.data('N'),
            amount = it.data('O'),
        )
    }.toMutableList()
}


