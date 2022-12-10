package com.eferraz.mygooglesheetsconnector.archtecture.repository

import com.eferraz.mygooglesheetsconnector.archtecture.datasource.BaseReadableDataSource
import com.eferraz.mygooglesheetsconnector.archtecture.repository.GenericReadableRepositoryImpl.Params
import com.eferraz.mygooglesheetsconnector.di.LocalDataSource
import com.eferraz.mygooglesheetsconnector.di.RemoteDataSource
import javax.inject.Inject

class GenericReadableRepositoryImpl<Result> @Inject constructor(
    @RemoteDataSource private val remoteDatasource: BaseReadableDataSource<Result>,
    @LocalDataSource private val localDatasource: BaseReadableDataSource<Result>
) : BaseReadableRepository<Params, MutableList<Result>> {

    override fun get(params: Params) =
        if (params.forceRemote) remoteDatasource.get()
        else localDatasource.get()

    class Params(val forceRemote: Boolean)
}