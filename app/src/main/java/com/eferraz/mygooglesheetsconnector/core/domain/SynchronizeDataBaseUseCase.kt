package com.eferraz.mygooglesheetsconnector.core.domain

import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import com.eferraz.mygooglesheetsconnector.core.repositories.FixedIncomeRoomRepository
import com.eferraz.mygooglesheetsconnector.core.repositories.FixedIncomeSheetsRepository
import kotlinx.coroutines.flow.onEach
import org.koin.core.annotation.Factory

@Factory
class SynchronizeDataBaseUseCase constructor(private val api: FixedIncomeSheetsRepository, private val database: FixedIncomeRoomRepository) {

    operator fun invoke() = api.get().onEach {
        database.insertOrUpdate(it as MutableList<FixedIncome>)
    }
}