package com.eferraz.mygooglesheetsconnector.core.datasource.local

import com.eferraz.mygooglesheetsconnector.core.database.FixedIncomeDao
import com.eferraz.mygooglesheetsconnector.core.datasource.BaseReadableDataSource
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import javax.inject.Inject

class FixedIncomeLocalDataSourceImpl @Inject constructor(private val dao: FixedIncomeDao) : BaseReadableDataSource<FixedIncome> {

    override suspend fun get() = dao.get()
}