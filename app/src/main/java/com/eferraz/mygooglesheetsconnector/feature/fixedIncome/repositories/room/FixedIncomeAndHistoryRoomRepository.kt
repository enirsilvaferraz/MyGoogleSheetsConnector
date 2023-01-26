package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.repositories.room

import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.datasources.FixedIncomeAndHistoryDao
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models.FixedIncomeAndHistory
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory
import java.time.LocalDate

interface FixedIncomeAndHistoryRoomRepository {
    fun getGrouped(
        month: Int = LocalDate.now().month.value,
        year: Int = LocalDate.now().year
    ): Flow<Map<String, List<FixedIncomeAndHistory>>>

    fun getFiltered(
        startDueDate: LocalDate,
        endDueDate: LocalDate,
        month: Int = LocalDate.now().month.value,
        year: Int = LocalDate.now().year
    ): Flow<MutableList<FixedIncomeAndHistory>>

    fun getFiltered(
        liquidity: String,
        month: Int = LocalDate.now().month.value,
        year: Int = LocalDate.now().year
    ): Flow<MutableList<FixedIncomeAndHistory>>
}

@Factory
class FixedIncomeAndHistoryRoomRepositoryImpl(private val dao: FixedIncomeAndHistoryDao) : FixedIncomeAndHistoryRoomRepository {

    override fun getGrouped(month: Int, year: Int) =
        dao.getGrouped(month = month, year = year)

    override fun getFiltered(liquidity: String, month: Int, year: Int) =
        dao.getFiltered(liquidity = liquidity, month = month, year = year)

    override fun getFiltered(startDueDate: LocalDate, endDueDate: LocalDate, month: Int, year: Int) =
        dao.getFiltered(startDueDate = startDueDate, endDueDate = endDueDate, month = month, year = year)
}