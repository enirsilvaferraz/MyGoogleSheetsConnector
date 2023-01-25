package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.usecases

import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models.FixedIncome
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.repositories.room.FixedIncomeRoomRepository
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class GetFixedIncomeListUseCase constructor(private val repository: FixedIncomeRoomRepository) {

    operator fun invoke() = repository.getGrouped().map {
        it.map {
            Grouped(it.key, it.value)
        }
    }

    data class Grouped(val title: String, val list: List<FixedIncome>)
}