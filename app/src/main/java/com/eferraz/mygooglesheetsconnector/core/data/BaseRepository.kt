package com.eferraz.mygooglesheetsconnector.core.data

import com.eferraz.mygooglesheetsconnector.archtecture.DomainResponse
import kotlinx.coroutines.flow.Flow

interface BaseRepository<Params, Result> {
    fun get(params :Params): Flow<DomainResponse<Result>>
}