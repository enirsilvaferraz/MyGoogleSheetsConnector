package com.eferraz.mygooglesheetsconnector.core.network

import com.eferraz.mygooglesheetsconnector.archtecture.DomainResponse.Companion.result
import com.eferraz.mygooglesheetsconnector.core.database.FixedIncomeDao
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import javax.inject.Inject

class FixedIncomeLocalDataSourceImpl @Inject constructor(private val dao: FixedIncomeDao) : BaseReadableDataSource<MutableList<FixedIncome>> {

    override suspend fun get() = result { dao.get() }
}