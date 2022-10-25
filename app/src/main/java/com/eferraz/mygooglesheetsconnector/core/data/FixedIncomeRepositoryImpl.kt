package com.eferraz.mygooglesheetsconnector.core.data

import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import com.eferraz.mygooglesheetsconnector.core.network.BaseReadableDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FixedIncomeRepositoryImpl @Inject constructor(
    private val dataSource: BaseReadableDataSource<MutableList<FixedIncome>>
) : BaseRepository<MutableList<FixedIncome>> {

    override fun get() = flow { emit(dataSource.get()) }.flowOn(Dispatchers.IO)
}