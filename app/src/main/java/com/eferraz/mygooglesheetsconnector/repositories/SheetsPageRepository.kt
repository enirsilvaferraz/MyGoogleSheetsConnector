package com.eferraz.mygooglesheetsconnector.repositories

import com.eferraz.googlesheets.datasources.SheetsResponse
import kotlinx.coroutines.flow.Flow

interface SheetsPageRepository<T> {
    fun get(): Flow<SheetsResponse<T>>
   // fun append(vararg data: Entity) : DataSourceResponse
}