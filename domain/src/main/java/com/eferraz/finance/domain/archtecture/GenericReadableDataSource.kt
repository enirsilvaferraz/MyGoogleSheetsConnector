package com.eferraz.finance.domain.archtecture

interface GenericReadableDataSource<Result> {
    suspend fun get(): DomainResponse<Result>
}