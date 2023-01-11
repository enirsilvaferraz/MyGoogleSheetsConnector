package com.eferraz.googlesheets.providers

interface ParcelableModel<Result> {
    operator fun invoke(data: List<Any>): Result
}