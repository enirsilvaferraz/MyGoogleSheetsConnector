package com.eferraz.mygooglesheetsconnector.feature.sync.domain

import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.repositories.room.FixedIncomeWithHistoryRoomRepository
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.repositories.sheets.FixedIncomeSheetsRepository
import kotlinx.coroutines.flow.onEach
import org.koin.core.annotation.Factory

@Factory
class SynchronizeDataBaseUseCase constructor(
    private val api: FixedIncomeSheetsRepository,
    private val database: FixedIncomeWithHistoryRoomRepository
) {

    operator fun invoke() = api.get().onEach {
        database.insertOrUpdate(it)
    }
}