package com.eferraz.mygooglesheetsconnector.core.domain

import com.eferraz.mygooglesheetsconnector.archtecture.DomainResponse
import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<Params, Result> {
    abstract operator fun invoke(params: Params): Flow<DomainResponse<Result>>
}