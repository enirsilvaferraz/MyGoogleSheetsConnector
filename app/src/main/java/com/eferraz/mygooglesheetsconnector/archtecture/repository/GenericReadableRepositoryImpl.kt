package com.eferraz.mygooglesheetsconnector.archtecture.repository

import com.eferraz.mygooglesheetsconnector.archtecture.datasource.BaseReadableDataSource
import com.eferraz.mygooglesheetsconnector.di.LocalDataSource
import com.eferraz.mygooglesheetsconnector.di.RemoteDataSource
import javax.inject.Inject

class GenericReadableRepositoryImpl<Result> @Inject constructor(
    @RemoteDataSource private val remoteDatasource: BaseReadableDataSource<Result>,
    @LocalDataSource private val localDatasource: BaseReadableDataSource<Result>
) : BaseReadableRepository<GenericReadableRepositoryImpl.Params, MutableList<Result>> {

    override suspend fun get(params: Params) =
        if (params.forceRemote) remoteDatasource.get()
        else localDatasource.get()

    class Params(val forceRemote: Boolean)
}