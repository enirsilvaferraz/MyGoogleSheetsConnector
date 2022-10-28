package com.eferraz.mygooglesheetsconnector.archtecture.domain

abstract class BaseUseCase<Params, Result> {
    abstract suspend operator fun invoke(params: Params): Result
}