package com.eferraz.mygooglesheetsconnector.core.domain

import com.eferraz.mygooglesheetsconnector.archtecture.domain.BaseUseCase
import com.eferraz.mygooglesheetsconnector.archtecture.repository.BaseReadableRepository
import com.eferraz.mygooglesheetsconnector.archtecture.repository.GenericReadableRepositoryImpl
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFixedIncomeListUseCase @Inject constructor(
    private val repository: BaseReadableRepository<GenericReadableRepositoryImpl.Params, MutableList<FixedIncome>>
) : BaseUseCase<Unit, Flow<MutableList<FixedIncome>>>() {

    override fun invoke(params: Unit) = repository.get(GenericReadableRepositoryImpl.Params(forceRemote = false))
}