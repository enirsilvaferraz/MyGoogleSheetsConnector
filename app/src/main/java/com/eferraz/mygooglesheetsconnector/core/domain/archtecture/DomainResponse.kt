package com.eferraz.mygooglesheetsconnector.core.domain.archtecture

sealed class DomainResponse<out R> {

    class Success<T>(val result: T) : DomainResponse<T>()
    class Failure<T>(val result: Throwable) : DomainResponse<T>()

    fun getOrNull(): R? = when (this) {
        is Failure -> null
        is Success -> this.result
    }

    companion object {

        fun <T : Any> result(function: () -> T): DomainResponse<T> = try {
            Success(function())
        } catch (e: Exception) {
            Failure(e)
        }
    }
}