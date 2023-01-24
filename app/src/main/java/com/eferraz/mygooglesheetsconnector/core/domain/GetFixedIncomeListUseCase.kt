package com.eferraz.mygooglesheetsconnector.core.domain

import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import com.eferraz.mygooglesheetsconnector.core.repositories.FixedIncomeRoomRepository
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class GetFixedIncomeListUseCase constructor(private val repository: FixedIncomeRoomRepository) {

    operator fun invoke() = repository.getGrouped().map { it.map { Grouped(it.key, it.value) } }

    data class Grouped(val title: String, val list: List<FixedIncome>)
}