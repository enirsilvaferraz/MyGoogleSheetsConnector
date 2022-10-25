package com.eferraz.mygooglesheetsconnector.core.network

import com.eferraz.mygooglesheetsconnector.archtecture.DomainResponse

interface BaseReadableDataSource<Result> {
    suspend fun get(): DomainResponse<Result>
}