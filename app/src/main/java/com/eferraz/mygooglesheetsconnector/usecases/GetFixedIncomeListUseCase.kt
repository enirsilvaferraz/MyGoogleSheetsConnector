package com.eferraz.mygooglesheetsconnector.usecases

import com.eferraz.finance.domain.archtecture.BaseRepository
import com.eferraz.finance.domain.archtecture.BaseUseCase
import com.eferraz.finance.domain.archtecture.DomainResponse
import com.eferraz.finance.domain.entities.FixedIncome
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFixedIncomeListUseCase @Inject constructor(
    private val repository: BaseRepository<MutableList<FixedIncome>>
) : BaseUseCase<Unit, MutableList<FixedIncome>>() {

    override fun invoke(params: Unit): Flow<DomainResponse<MutableList<FixedIncome>>> = repository.get()
}