package com.eferraz.mygooglesheetsconnector.archtecture.datasource

import com.eferraz.mygooglesheetsconnector.archtecture.database.GenericDao
import javax.inject.Inject

class GenericWritableLocalDataSourceImpl<Params> @Inject constructor(private val dao: GenericDao<Params>) : BaseWritableDataSource<Params> {
    override fun insertOrUpdate(models: MutableList<Params>) {
        models.forEach { dao.update(it) }
    }
}