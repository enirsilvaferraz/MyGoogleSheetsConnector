package com.eferraz.mygooglesheetsconnector.core.domain

import com.eferraz.mygooglesheetsconnector.archtecture.DomainResponse
import com.eferraz.mygooglesheetsconnector.core.data.BaseRepository
import com.eferraz.mygooglesheetsconnector.core.data.FixedIncomeRepositoryImpl
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFixedIncomeListUseCase @Inject constructor(
    private val repository: BaseRepository<FixedIncomeRepositoryImpl.Params, MutableList<FixedIncome>>
) : BaseUseCase<Unit, MutableList<FixedIncome>>() {

    override fun invoke(params: Unit): Flow<DomainResponse<MutableList<FixedIncome>>> =
        repository.get(FixedIncomeRepositoryImpl.Params(forceRemote = false))
}