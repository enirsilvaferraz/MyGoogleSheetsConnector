package com.eferraz.mygooglesheetsconnector.archtecture.datasource

interface ParcelableModel<Result> {
    operator fun invoke(data: List<Any>): Result
    fun getRange(): String
}