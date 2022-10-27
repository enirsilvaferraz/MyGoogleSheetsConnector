package com.eferraz.mygooglesheetsconnector.core.repository

import kotlinx.coroutines.flow.Flow

interface BaseRepository<Params, Result> {
    fun get(params :Params): Flow<Result>
}