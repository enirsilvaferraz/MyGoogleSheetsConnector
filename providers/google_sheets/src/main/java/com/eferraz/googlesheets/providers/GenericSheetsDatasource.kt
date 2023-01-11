package com.eferraz.googlesheets.providers

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory

interface GenericSheetsDatasource {
    fun get(sheetsKey: String, range: String): Flow<List<List<Any>>>
}

@Factory
internal class GenericSheetsDatasourceImpl constructor(
    private val sheetsProvider: SheetsProvider
) : GenericSheetsDatasource {

    override fun get(sheetsKey: String, range: String) = flow {
        emit(sheetsProvider.get(sheetsKey, range))
    }.flowOn(IO)
}