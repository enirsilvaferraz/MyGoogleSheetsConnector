package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.datasources

import androidx.room.Dao
import androidx.room.MapInfo
import androidx.room.Query
import androidx.room.RoomWarnings
import androidx.room.Transaction
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models.FixedIncomeAndHistory
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface FixedIncomeAndHistoryDao {

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Transaction
    @MapInfo(keyColumn = "bank")
    @Query("SELECT * FROM FixedIncome, FixedIncomeHistory WHERE uuid = fixedIncome AND month = :month AND year = :year ORDER BY bank ASC, name ASC")
    fun getGrouped(month: Int, year: Int): Flow<Map<String, List<FixedIncomeAndHistory>>>

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Transaction
    @Query("SELECT * FROM FixedIncome, FixedIncomeHistory WHERE uuid = fixedIncome AND dueDate > :startDueDate AND dueDate < :endDueDate AND amount != 0.0 AND month = :month AND year = :year ORDER BY dueDate")
    fun getFiltered(startDueDate: LocalDate, endDueDate: LocalDate, month: Int, year: Int): Flow<MutableList<FixedIncomeAndHistory>>

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Transaction
    @Query("SELECT * FROM FixedIncomeHistory, FixedIncome WHERE uuid = fixedIncome AND liquidity = :liquidity AND amount != 0.0 AND month = :month AND year = :year ORDER BY dueDate")
    fun getFiltered(liquidity: String, month: Int, year: Int): Flow<MutableList<FixedIncomeAndHistory>>
}