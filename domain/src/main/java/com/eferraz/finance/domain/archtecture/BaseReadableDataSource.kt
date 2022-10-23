package com.eferraz.finance.domain.archtecture

interface BaseReadableDataSource<Result> {
    suspend fun get(): DomainResponse<Result>
}