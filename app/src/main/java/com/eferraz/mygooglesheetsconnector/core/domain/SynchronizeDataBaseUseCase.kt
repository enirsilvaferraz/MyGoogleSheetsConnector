package com.eferraz.mygooglesheetsconnector.core.domain

import com.eferraz.mygooglesheetsconnector.archtecture.domain.BaseUseCase
import com.eferraz.mygooglesheetsconnector.archtecture.repository.BaseReadableRepository
import com.eferraz.mygooglesheetsconnector.archtecture.repository.BaseWritableRepository
import com.eferraz.mygooglesheetsconnector.archtecture.repository.GenericReadableRepositoryImpl.Params
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class SynchronizeDataBaseUseCase @Inject constructor(
    private val apiRepository: BaseReadableRepository<Params, MutableList<FixedIncome>>,
    private val writableRepository: BaseWritableRepository<FixedIncome>
) : BaseUseCase<Unit, Flow<MutableList<FixedIncome>>>() {

    override fun invoke(params: Unit) = apiRepository.get(Params(forceRemote = true)).onEach {
        writableRepository.insertOrUpdate(it)
    }
}