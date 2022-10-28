package com.eferraz.mygooglesheetsconnector.archtecture.repository

import kotlinx.coroutines.flow.Flow

interface BaseReadableRepository<Params, Result> {
    fun get(params :Params): Flow<Result>
}