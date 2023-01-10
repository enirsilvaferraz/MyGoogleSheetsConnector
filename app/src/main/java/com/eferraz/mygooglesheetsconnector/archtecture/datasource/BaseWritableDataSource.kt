package com.eferraz.mygooglesheetsconnector.archtecture.datasource

interface BaseWritableDataSource<T> {

    fun insert(obj: T)

    fun insert(vararg obj: T)

    fun delete(obj: T)
}
