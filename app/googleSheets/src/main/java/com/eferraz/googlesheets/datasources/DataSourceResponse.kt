package com.eferraz.googlesheets.datasources

import android.content.Intent

sealed class DataSourceResponse {

    fun <T> success() = (this as Success<T>?)

    data class Success<T>(val data: T? = null) : DataSourceResponse()
    data class Failure(val e: Throwable, val intent: Intent? = null) : DataSourceResponse()
}