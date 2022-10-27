package com.eferraz.mygooglesheetsconnector.core.data

import com.eferraz.mygooglesheetsconnector.core.datasource.BaseReadableDataSource
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import com.eferraz.mygooglesheetsconnector.core.network.LocalDataSource
import com.eferraz.mygooglesheetsconnector.core.network.RemoteDataSource
import com.eferraz.mygooglesheetsconnector.core.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FixedIncomeRepositoryImpl @Inject constructor(
    @RemoteDataSource private val remoteDatasource: BaseReadableDataSource<FixedIncome>,
    @LocalDataSource private val localDatasource: BaseReadableDataSource<FixedIncome>
) : BaseRepository<FixedIncomeRepositoryImpl.Params, MutableList<FixedIncome>> {

    override fun get(params: Params): Flow<MutableList<FixedIncome>> = flow {
        if (params.forceRemote) emit(remoteDatasource.get())
        else emit(localDatasource.get())
    }.flowOn(Dispatchers.IO)

    class Params(val forceRemote: Boolean)
}