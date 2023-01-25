package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.repositories.room

import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.datasources.FixedIncomeDao
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models.FixedIncome
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory
import java.time.LocalDate

interface FixedIncomeRoomRepository {

    fun insertOrUpdate(models: MutableList<FixedIncome>)

    fun getGrouped(
        month: Int = LocalDate.now().month.value,
        year: Int = LocalDate.now().year
    ): Flow<Map<String, List<FixedIncome>>>

    fun getFiltered(
        startDueDate: LocalDate,
        endDueDate: LocalDate,
        month: Int = LocalDate.now().month.value,
        year: Int = LocalDate.now().year
    ): Flow<MutableList<FixedIncome>>

    fun getFiltered(
        liquidity: String,
        month: Int = LocalDate.now().month.value,
        year: Int = LocalDate.now().year
    ): Flow<MutableList<FixedIncome>>
}

@Factory
class FixedIncomeRoomRepositoryImpl(private val dao: FixedIncomeDao) : FixedIncomeRoomRepository {

    override fun insertOrUpdate(models: MutableList<FixedIncome>) =
        dao.insert(*models.toTypedArray())

    override fun getGrouped(month: Int, year: Int) =
        dao.getGrouped(month = month, year = year)

    override fun getFiltered(liquidity: String, month: Int, year: Int) =
        dao.getFiltered(liquidity = liquidity, month = month, year = year)

    override fun getFiltered(startDueDate: LocalDate, endDueDate: LocalDate, month: Int, year: Int) =
        dao.getFiltered(startDueDate = startDueDate, endDueDate = endDueDate, month = month, year = year)
}