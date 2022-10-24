package com.eferraz.mygooglesheetsconnector.repositories

import com.eferraz.finance.domain.archtecture.BaseReadableDataSource
import com.eferraz.finance.domain.archtecture.BaseRepository
import com.eferraz.finance.domain.entities.FixedIncome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FixedIncomeRepositoryImpl @Inject constructor(
    private val dataSource: BaseReadableDataSource<MutableList<FixedIncome>>
) : BaseRepository<MutableList<FixedIncome>> {

    override fun get() = flow { emit(dataSource.get()) }.flowOn(Dispatchers.IO)
}