package com.eferraz.mygooglesheetsconnector.repositories

import com.eferraz.googlesheets.datasources.DataSourceResponse
import kotlinx.coroutines.flow.Flow

interface SheetsPageRepository<Entity> {
    fun get(): Flow<DataSourceResponse>
    fun append(vararg data: Entity) : DataSourceResponse
}