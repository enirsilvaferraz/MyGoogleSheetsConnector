package com.eferraz.mygooglesheetsconnector.archtecture.datasource

interface BaseWritableDataSource<Params> {
    fun insertOrUpdate(models: MutableList<Params>)
}
