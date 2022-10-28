package com.eferraz.mygooglesheetsconnector.core.domain

import com.eferraz.mygooglesheetsconnector.archtecture.domain.BaseUseCase
import com.eferraz.mygooglesheetsconnector.archtecture.repository.BaseReadableRepository
import com.eferraz.mygooglesheetsconnector.archtecture.repository.BaseWritableRepository
import com.eferraz.mygooglesheetsconnector.archtecture.repository.GenericReadableRepositoryImpl
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class SynchronizeDataBaseUseCase @Inject constructor(
    private val apiRepository: BaseReadableRepository<GenericReadableRepositoryImpl.Params, MutableList<FixedIncome>>,
    private val writableRepository: BaseWritableRepository<FixedIncome>
) : BaseUseCase<Unit, Unit>() {

    override suspend fun invoke(params: Unit) {
        apiRepository.get(GenericReadableRepositoryImpl.Params(forceRemote = true)).first().let {
            writableRepository.insertOrUpdate(it)
        }
    }
}