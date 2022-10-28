package com.eferraz.mygooglesheetsconnector.archtecture.repository

interface BaseReadableRepository<Params, Result> {
    suspend fun get(params :Params): Result
}