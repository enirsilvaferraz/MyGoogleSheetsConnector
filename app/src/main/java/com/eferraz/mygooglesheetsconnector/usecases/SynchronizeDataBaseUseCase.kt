package com.eferraz.mygooglesheetsconnector.usecases

import com.eferraz.finance.domain.archtecture.BaseRepository
import com.eferraz.finance.domain.archtecture.BaseUseCase
import com.eferraz.finance.domain.archtecture.DomainResponse
import com.eferraz.finance.domain.entities.FixedIncome
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SynchronizeDataBaseUseCase @Inject constructor(
    private val apiRepository: BaseRepository<MutableList<FixedIncome>>
) : BaseUseCase<Unit, Unit>() {

    override fun invoke(params: Unit): Flow<DomainResponse<Unit>> {
        TODO()
    }
}