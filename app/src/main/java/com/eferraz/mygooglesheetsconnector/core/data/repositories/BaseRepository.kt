package com.eferraz.mygooglesheetsconnector.core.data.repositories

import com.eferraz.mygooglesheetsconnector.core.domain.archtecture.DomainResponse
import kotlinx.coroutines.flow.Flow

interface BaseRepository<T> {
    fun get(): Flow<DomainResponse<T>>
}