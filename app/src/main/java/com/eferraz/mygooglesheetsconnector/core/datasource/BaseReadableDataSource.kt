package com.eferraz.mygooglesheetsconnector.core.datasource

interface BaseReadableDataSource<Result> {
    suspend fun get(): MutableList<Result>
}