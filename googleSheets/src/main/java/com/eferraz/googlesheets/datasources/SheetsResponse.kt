package com.eferraz.googlesheets.datasources

sealed class SheetsResponse<out R> {

    class Success<T>(val result: T) : SheetsResponse<T>()
    class Failure<T>(val result: Throwable) : SheetsResponse<T>()

    companion object {

        fun <T : Any> result(function: () -> T): SheetsResponse<T> = try {
            Success(function())
        } catch (e: Exception) {
            Failure(e)
        }
    }
}