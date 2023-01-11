package com.eferraz.mygooglesheetsconnector.core.domain

import com.eferraz.mygooglesheetsconnector.archtecture.domain.BaseUseCase
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import com.eferraz.mygooglesheetsconnector.core.repositories.FixedIncomeRoomRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class GetFixedIncomeInReleaseUseCase constructor(
    private val repository: FixedIncomeRoomRepository
) : BaseUseCase<Unit, Flow<MutableList<FixedIncome>>>() {

    override fun invoke(params: Unit) = repository.getInRelease()
}