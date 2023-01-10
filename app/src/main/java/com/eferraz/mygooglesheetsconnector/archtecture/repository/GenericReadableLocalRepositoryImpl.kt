package com.eferraz.mygooglesheetsconnector.archtecture.repository

import com.eferraz.mygooglesheetsconnector.archtecture.datasource.BaseReadableDataSource
import com.eferraz.mygooglesheetsconnector.di.LocalDataSource
import javax.inject.Inject

class GenericReadableLocalRepositoryImpl<Result> @Inject constructor(
    @LocalDataSource private val datasource: BaseReadableDataSource<Result>
) : BaseReadableRepository<Unit, MutableList<Result>> {

    override fun get(params: Unit) = datasource.get()
}