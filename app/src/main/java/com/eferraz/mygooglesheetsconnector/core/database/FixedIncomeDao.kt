package com.eferraz.mygooglesheetsconnector.core.database

import androidx.room.Dao
import androidx.room.Query
import com.eferraz.mygooglesheetsconnector.archtectureImpl.database.GenericRoomDatasource
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface FixedIncomeDao : GenericRoomDatasource<FixedIncome> {

    @Query("SELECT * FROM FixedIncome WHERE month = 01 AND year = 2023 ORDER BY year DESC, month DESC")
    override fun get(): Flow<MutableList<FixedIncome>>

    @Query("SELECT * FROM FixedIncome WHERE month = :monthParam AND year = :yearParam")
    fun get(
        monthParam: Int = LocalDate.now().month.value,
        yearParam: Int = LocalDate.now().year
    ): Flow<MutableList<FixedIncome>>

    @Query("SELECT * FROM FixedIncome WHERE dueDate > :startDueDate AND dueDate < :endDueDate AND month = :monthParam AND year = :yearParam ORDER BY dueDate")
    fun getFiltered(
        startDueDate: LocalDate,
        endDueDate: LocalDate,
        monthParam: Int = LocalDate.now().month.value,
        yearParam: Int = LocalDate.now().year
    ): Flow<MutableList<FixedIncome>>

    @Query("SELECT * FROM FixedIncome WHERE liquidity = :liquidity AND month = :monthParam AND year = :yearParam ORDER BY dueDate")
    fun getFiltered(
        liquidity: String,
        monthParam: Int = LocalDate.now().month.value,
        yearParam: Int = LocalDate.now().year
    ): Flow<MutableList<FixedIncome>>
}

data class FixedIncomeRoomView(
    val name: String,
    val bank: String,
    val dueDate: LocalDate?,
    val investment: Double,
    val amount: Double
)