package com.eferraz.mygooglesheetsconnector.core.datasource.remote

import com.eferraz.googlesheets.providers.SheetsProvider
import com.eferraz.mygooglesheetsconnector.core.datasource.BaseReadableDataSource
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import com.eferraz.mygooglesheetsconnector.core.network.Constants.SHEETS_KEY
import javax.inject.Inject
import javax.inject.Named

class FixedIncomeRemoteDataSourceImpl @Inject constructor(
    @Named(SHEETS_KEY) private val sheetsKey: String, private val sheetsProvider: SheetsProvider, private val parsableModel: FixedIncomeParsable
) : BaseReadableDataSource<FixedIncome> {

    private val range: String = "'Histórico Renda Fixa'!A2:Z1000"

    override suspend fun get() = sheetsProvider.get(sheetsKey, range).mapNotNull {
        if (it.isNotEmpty()) parsableModel(it) else null
    }.toMutableList()
}