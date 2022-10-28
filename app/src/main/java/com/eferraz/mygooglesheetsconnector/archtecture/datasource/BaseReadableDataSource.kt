package com.eferraz.mygooglesheetsconnector.archtecture.datasource

import kotlinx.coroutines.flow.Flow

interface BaseReadableDataSource<Result> {
    fun get(): Flow<MutableList<Result>>
}