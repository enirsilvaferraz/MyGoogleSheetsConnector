package com.eferraz.finance.domain.archtecture

import kotlinx.coroutines.flow.Flow

interface BaseRepository<T> {
    fun get(): Flow<DomainResponse<T>>
}