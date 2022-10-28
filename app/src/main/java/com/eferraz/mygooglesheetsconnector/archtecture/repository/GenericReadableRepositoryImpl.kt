package com.eferraz.mygooglesheetsconnector.archtecture.repository

import com.eferraz.mygooglesheetsconnector.archtecture.datasource.BaseReadableDataSource
import com.eferraz.mygooglesheetsconnector.di.LocalDataSource
import com.eferraz.mygooglesheetsconnector.di.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GenericReadableRepositoryImpl<Result> @Inject constructor(
    @RemoteDataSource private val remoteDatasource: BaseReadableDataSource<Result>,
    @LocalDataSource private val localDatasource: BaseReadableDataSource<Result>
) : BaseReadableRepository<GenericReadableRepositoryImpl.Params, MutableList<Result>> {

    override fun get(params: Params): Flow<MutableList<Result>> = flow {
        if (params.forceRemote) emit(remoteDatasource.get())
        else emit(localDatasource.get())
    }.flowOn(Dispatchers.IO)

    class Params(val forceRemote: Boolean)
}