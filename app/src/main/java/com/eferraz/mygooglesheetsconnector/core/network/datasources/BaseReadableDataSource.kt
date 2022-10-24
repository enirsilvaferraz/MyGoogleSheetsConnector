package com.eferraz.mygooglesheetsconnector.core.network.datasources

import com.eferraz.mygooglesheetsconnector.core.domain.archtecture.DomainResponse

interface BaseReadableDataSource<Result> {
    suspend fun get(): com.eferraz.mygooglesheetsconnector.core.domain.archtecture.DomainResponse<Result>
}