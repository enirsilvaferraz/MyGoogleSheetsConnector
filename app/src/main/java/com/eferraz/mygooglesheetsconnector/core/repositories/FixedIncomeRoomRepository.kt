package com.eferraz.mygooglesheetsconnector.core.repositories

import com.eferraz.mygooglesheetsconnector.core.database.FixedIncomeDao
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory
import java.time.LocalDate

interface FixedIncomeRoomRepository {
    fun insertOrUpdate(models: MutableList<FixedIncome>)
    fun get(): Flow<MutableList<FixedIncome>>
    fun getInRelease(dueDateParam: LocalDate): Flow<MutableList<FixedIncome>>
}

@Factory
class FixedIncomeRoomRepositoryImpl(private val dao: FixedIncomeDao) : FixedIncomeRoomRepository {

    override fun insertOrUpdate(models: MutableList<FixedIncome>) = dao.insert(*models.toTypedArray())

    override fun get() = dao.get()

    override fun getInRelease(dueDateParam: LocalDate) = dao.getFiltered(dueDateParam = dueDateParam)
}