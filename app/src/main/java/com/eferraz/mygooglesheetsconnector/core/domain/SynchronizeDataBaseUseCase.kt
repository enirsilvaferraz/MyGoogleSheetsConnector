package com.eferraz.mygooglesheetsconnector.core.domain

import com.eferraz.mygooglesheetsconnector.archtecture.domain.BaseUseCase
import com.eferraz.mygooglesheetsconnector.archtecture.repository.BaseReadableRepository
import com.eferraz.mygooglesheetsconnector.archtecture.repository.BaseWritableRepository
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import com.eferraz.mygooglesheetsconnector.di.LocalDataSource
import com.eferraz.mygooglesheetsconnector.di.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class SynchronizeDataBaseUseCase @Inject constructor(
    @RemoteDataSource private val apiRepository: BaseReadableRepository<Unit, MutableList<FixedIncome>>,
    @LocalDataSource private val writableRepository: BaseWritableRepository<FixedIncome>
) : BaseUseCase<Unit, Flow<MutableList<FixedIncome>>>() {

    override fun invoke(params: Unit) = apiRepository.get(params).onEach {
        writableRepository.insertOrUpdate(it)
    }
}