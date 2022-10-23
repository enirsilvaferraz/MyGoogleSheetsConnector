package com.eferraz.mygooglesheetsconnector.repositories

import com.eferraz.finance.domain.archtecture.DomainResponse
import kotlinx.coroutines.flow.Flow

interface BaseRepository<T> {
    fun get(): Flow<DomainResponse<T>>
   // fun append(vararg data: Entity) : DataSourceResponse
}