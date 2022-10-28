package com.eferraz.mygooglesheetsconnector.archtecture.datasource

interface BaseReadableDataSource<Result> {
    suspend fun get(): MutableList<Result>
}