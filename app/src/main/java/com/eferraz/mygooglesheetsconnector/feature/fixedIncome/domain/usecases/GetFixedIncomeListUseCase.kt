package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.usecases

import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models.FixedIncomeWithHistory
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.repositories.room.FixedIncomeWithHistoryRoomRepository
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class GetFixedIncomeListUseCase constructor(private val repository: FixedIncomeWithHistoryRoomRepository) {

    operator fun invoke() = repository.getGrouped().map {
        it.map {
            Grouped(it.key, it.value)
        }
    }

    data class Grouped(val title: String, val list: List<FixedIncomeWithHistory>)
}