package com.eferraz.finance.domain.archtecture

import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<Params, Result> {
    abstract operator fun invoke(params: Params): Flow<DomainResponse<Result>>
}