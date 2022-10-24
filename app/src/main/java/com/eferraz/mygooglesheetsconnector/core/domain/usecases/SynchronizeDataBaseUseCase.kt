package com.eferraz.mygooglesheetsconnector.core.domain.usecases

import com.eferraz.mygooglesheetsconnector.core.data.repositories.BaseRepository
import com.eferraz.mygooglesheetsconnector.core.domain.archtecture.DomainResponse
import com.eferraz.mygooglesheetsconnector.core.domain.entities.FixedIncome
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SynchronizeDataBaseUseCase @Inject constructor(
    private val apiRepository: com.eferraz.mygooglesheetsconnector.core.data.repositories.BaseRepository<MutableList<FixedIncome>>
) : BaseUseCase<Unit, Unit>() {

    override fun invoke(params: Unit): Flow<DomainResponse<Unit>> {
        TODO()
    }
}