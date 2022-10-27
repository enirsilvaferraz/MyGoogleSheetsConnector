package com.eferraz.mygooglesheetsconnector.core.domain

import com.eferraz.mygooglesheetsconnector.core.data.FixedIncomeRepositoryImpl
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import com.eferraz.mygooglesheetsconnector.core.repository.BaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SynchronizeDataBaseUseCase @Inject constructor(
    private val apiRepository: BaseRepository<FixedIncomeRepositoryImpl.Params, MutableList<FixedIncome>>
) : BaseUseCase<Unit, Unit>() {

    override fun invoke(params: Unit): Flow<Unit> {
        TODO()
    }
}