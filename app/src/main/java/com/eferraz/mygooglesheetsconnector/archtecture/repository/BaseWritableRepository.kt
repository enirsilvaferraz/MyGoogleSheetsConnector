package com.eferraz.mygooglesheetsconnector.archtecture.repository

interface BaseWritableRepository<Params> {

    fun insertOrUpdate(models: MutableList<Params>)
}
