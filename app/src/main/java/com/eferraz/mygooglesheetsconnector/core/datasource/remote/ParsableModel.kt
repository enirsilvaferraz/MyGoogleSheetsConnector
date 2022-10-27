package com.eferraz.mygooglesheetsconnector.core.datasource.remote

interface ParsableModel<Result> {
    operator fun invoke(data: List<Any>): Result
}