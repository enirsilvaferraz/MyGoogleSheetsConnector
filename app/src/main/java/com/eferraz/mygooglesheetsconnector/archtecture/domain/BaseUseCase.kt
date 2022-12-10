package com.eferraz.mygooglesheetsconnector.archtecture.domain

abstract class BaseUseCase<Params, Result> {
    abstract operator fun invoke(params: Params): Result
}