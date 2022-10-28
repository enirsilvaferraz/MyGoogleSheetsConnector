package com.eferraz.mygooglesheetsconnector.archtecture.repository

import com.eferraz.mygooglesheetsconnector.archtecture.datasource.BaseWritableDataSource
import javax.inject.Inject

class GenericWritableRepositoryImpl<Params> @Inject constructor(
    private val writableDataSource: BaseWritableDataSource<Params>
) : BaseWritableRepository<Params> {

    override fun insertOrUpdate(models: MutableList<Params>) {
        writableDataSource.insertOrUpdate(models)
    }
}