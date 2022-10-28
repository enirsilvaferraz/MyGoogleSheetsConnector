package com.eferraz.mygooglesheetsconnector.archtecture.datasource

import com.eferraz.mygooglesheetsconnector.archtecture.database.GenericDao
import javax.inject.Inject

class GenericLocalDataSourceImpl<Result> @Inject constructor(private val dao: GenericDao<Result>) : BaseReadableDataSource<Result> {

    override suspend fun get() = dao.get()
}