package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.repositories.room

import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.datasources.FixedIncomeWithHistoryDao
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models.FixedIncomeWithHistory
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory
import java.time.LocalDate

interface FixedIncomeWithHistoryRoomRepository {

    fun insertOrUpdate(models: List<FixedIncomeWithHistory>)

    fun getGrouped(
        month: Int = LocalDate.now().month.value,
        year: Int = LocalDate.now().year
    ): Flow<Map<String, List<FixedIncomeWithHistory>>>

    fun getFiltered(
        startDueDate: LocalDate,
        endDueDate: LocalDate,
        month: Int = LocalDate.now().month.value,
        year: Int = LocalDate.now().year
    ): Flow<MutableList<FixedIncomeWithHistory>>

    fun getFiltered(
        liquidity: String,
        month: Int = LocalDate.now().month.value,
        year: Int = LocalDate.now().year
    ): Flow<MutableList<FixedIncomeWithHistory>>
}

@Factory
class FixedIncomeWithHistoryRoomRepositoryImpl(private val dao: FixedIncomeWithHistoryDao) : FixedIncomeWithHistoryRoomRepository {
    override fun insertOrUpdate(models: List<FixedIncomeWithHistory>) =
        dao.insert(models)

    override fun getGrouped(month: Int, year: Int) =
        dao.getGrouped(month = month, year = year)

    override fun getFiltered(liquidity: String, month: Int, year: Int) =
        dao.getFiltered(liquidity = liquidity, month = month, year = year)

    override fun getFiltered(startDueDate: LocalDate, endDueDate: LocalDate, month: Int, year: Int) =
        dao.getFiltered(startDueDate = startDueDate, endDueDate = endDueDate, month = month, year = year)
}