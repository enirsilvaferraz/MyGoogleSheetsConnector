package com.eferraz.mygooglesheetsconnector.core.data.repositories

import com.eferraz.mygooglesheetsconnector.core.domain.entities.FixedIncome
import com.eferraz.mygooglesheetsconnector.core.network.datasources.BaseReadableDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FixedIncomeRepositoryImpl @Inject constructor(
    private val dataSource: BaseReadableDataSource<MutableList<FixedIncome>>
) : BaseRepository<MutableList<FixedIncome>> {

    override fun get() = flow { emit(dataSource.get()) }.flowOn(Dispatchers.IO)
}