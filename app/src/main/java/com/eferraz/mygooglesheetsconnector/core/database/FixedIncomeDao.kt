package com.eferraz.mygooglesheetsconnector.core.database

import androidx.room.Dao
import androidx.room.Query
import com.eferraz.mygooglesheetsconnector.archtectureImpl.database.GenericRoomDatasource
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters.lastDayOfYear

@Dao
interface FixedIncomeDao : GenericRoomDatasource<FixedIncome> {

    @Query("SELECT * FROM FixedIncome WHERE month = 01 AND year = 2023 ORDER BY year DESC, month DESC")
    override fun get(): Flow<MutableList<FixedIncome>>

    @Query("SELECT * FROM FixedIncome WHERE month = :monthParam AND year = :yearParam")
    fun get(
        monthParam: Int = LocalDate.now().month.value,
        yearParam: Int = LocalDate.now().year
    ): Flow<MutableList<FixedIncome>>

    @Query("SELECT * FROM FixedIncome WHERE dueDate < :dueDateParam AND month = :monthParam AND year = :yearParam ORDER BY dueDate")
    fun getF(
        dueDateParam: LocalDate = LocalDate.now().with(lastDayOfYear()),
        monthParam: Int = LocalDate.now().month.value,
        yearParam: Int = LocalDate.now().year
    ): Flow<MutableList<FixedIncome>>
}