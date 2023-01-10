package com.eferraz.mygooglesheetsconnector.core.domain

import com.eferraz.mygooglesheetsconnector.archtecture.domain.BaseUseCase
import com.eferraz.mygooglesheetsconnector.archtecture.repository.BaseReadableRepository
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import com.eferraz.mygooglesheetsconnector.di.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFixedIncomeListUseCase @Inject constructor(
    @LocalDataSource private val repository: BaseReadableRepository<Unit, MutableList<FixedIncome>>
) : BaseUseCase<Unit, Flow<MutableList<FixedIncome>>>() {

    override fun invoke(params: Unit) = repository.get(params)
}