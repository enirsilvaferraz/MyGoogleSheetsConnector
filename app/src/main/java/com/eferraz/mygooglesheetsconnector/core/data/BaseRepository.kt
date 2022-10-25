package com.eferraz.mygooglesheetsconnector.core.data

import com.eferraz.mygooglesheetsconnector.archtecture.DomainResponse
import kotlinx.coroutines.flow.Flow

interface BaseRepository<T> {
    fun get(): Flow<DomainResponse<T>>
}