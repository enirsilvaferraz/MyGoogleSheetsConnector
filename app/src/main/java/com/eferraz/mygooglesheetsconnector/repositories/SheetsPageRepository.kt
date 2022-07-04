package com.eferraz.mygooglesheetsconnector.repositories

import kotlinx.coroutines.flow.Flow

interface SheetsPageRepository<Entity> {
    fun get(): Flow<List<Entity>>
    fun append(vararg data: Entity)
}