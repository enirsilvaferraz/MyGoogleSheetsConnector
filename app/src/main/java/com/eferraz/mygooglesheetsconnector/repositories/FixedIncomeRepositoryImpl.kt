package com.eferraz.mygooglesheetsconnector.repositories

import com.eferraz.finance.domain.archtecture.GenericReadableDataSource
import com.eferraz.finance.domain.entities.FixedIncome
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FixedIncomeRepositoryImpl @Inject constructor(
    private val dataSource: GenericReadableDataSource<List<FixedIncome>>
) : BaseRepository<@JvmSuppressWildcards List<FixedIncome>> {

    override fun get() = flow {
        emit(dataSource.get())
    }

    //override fun append(vararg data: FixedIncome) =
    //    dataSource.append(environmentDataSource.sheetKey, RANGE, data.toList().toRequestBody()).toResponse()
}