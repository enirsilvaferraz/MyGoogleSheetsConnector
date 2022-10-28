package com.eferraz.mygooglesheetsconnector.archtecture.domain

import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<Params, Result> {
    abstract operator fun invoke(params: Params): Flow<Result>
}