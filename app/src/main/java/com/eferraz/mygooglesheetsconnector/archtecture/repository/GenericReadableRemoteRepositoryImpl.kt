package com.eferraz.mygooglesheetsconnector.archtecture.repository

import com.eferraz.mygooglesheetsconnector.archtecture.datasource.BaseReadableDataSource
import com.eferraz.mygooglesheetsconnector.di.RemoteDataSource
import javax.inject.Inject

@Deprecated("")
class GenericReadableRemoteRepositoryImpl<Result> @Inject constructor(
    @RemoteDataSource private val datasource: BaseReadableDataSource<Result>
) : BaseReadableRepository<Unit, MutableList<Result>> {

    override fun get(params: Unit) = datasource.get()
}