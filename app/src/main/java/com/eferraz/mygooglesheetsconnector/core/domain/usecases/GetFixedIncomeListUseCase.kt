package com.eferraz.mygooglesheetsconnector.core.domain.usecases

import com.eferraz.mygooglesheetsconnector.core.data.repositories.BaseRepository
import com.eferraz.mygooglesheetsconnector.core.domain.archtecture.DomainResponse
import com.eferraz.mygooglesheetsconnector.core.domain.entities.FixedIncome
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFixedIncomeListUseCase @Inject constructor(
    private val repository: com.eferraz.mygooglesheetsconnector.core.data.repositories.BaseRepository<MutableList<FixedIncome>>
) : BaseUseCase<Unit, MutableList<FixedIncome>>() {

    override fun invoke(params: Unit): Flow<DomainResponse<MutableList<FixedIncome>>> = repository.get()
}