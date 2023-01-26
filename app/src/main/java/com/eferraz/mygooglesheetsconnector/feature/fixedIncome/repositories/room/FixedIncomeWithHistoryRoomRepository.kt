package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.repositories.room

import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.datasources.FixedIncomeWithHistoryDao
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models.FixedIncomeWithHistory
import org.koin.core.annotation.Factory

interface FixedIncomeWithHistoryRoomRepository {

    fun insertOrUpdate(models: List<FixedIncomeWithHistory>)
}

@Factory
class FixedIncomeWithHistoryRoomRepositoryImpl(private val dao: FixedIncomeWithHistoryDao) : FixedIncomeWithHistoryRoomRepository {

    override fun insertOrUpdate(models: List<FixedIncomeWithHistory>) =
        dao.insert(models)
}