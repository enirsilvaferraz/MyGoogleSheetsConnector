package com.eferraz.mygooglesheetsconnector.core.data

import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import com.eferraz.mygooglesheetsconnector.core.network.BaseReadableDataSource
import com.eferraz.mygooglesheetsconnector.core.network.LocalDataSource
import com.eferraz.mygooglesheetsconnector.core.network.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FixedIncomeRepositoryImpl @Inject constructor(
    @RemoteDataSource private val remoteDatasource: BaseReadableDataSource<MutableList<FixedIncome>>,
    @LocalDataSource private val localDatasource: BaseReadableDataSource<MutableList<FixedIncome>>
) : BaseRepository<FixedIncomeRepositoryImpl.Params, MutableList<FixedIncome>> {

    override fun get(params: Params) = flow {
        if (params.forceRemote) emit(remoteDatasource.get())
        else emit(localDatasource.get())
    }.flowOn(Dispatchers.IO)

    class Params(val forceRemote: Boolean)
}