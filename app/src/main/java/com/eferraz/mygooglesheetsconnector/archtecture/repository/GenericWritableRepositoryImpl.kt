package com.eferraz.mygooglesheetsconnector.archtecture.repository

import com.eferraz.mygooglesheetsconnector.archtecture.datasource.BaseWritableDataSource
import com.eferraz.mygooglesheetsconnector.di.LocalDataSource
import javax.inject.Inject

class GenericWritableRepositoryImpl<Params> @Inject constructor(
    @LocalDataSource private val writableDataSource: BaseWritableDataSource<Params>
) : BaseWritableRepository<Params> {

    override fun insertOrUpdate(models: MutableList<Params>) {
        writableDataSource.insertOrUpdate(models)
    }
}