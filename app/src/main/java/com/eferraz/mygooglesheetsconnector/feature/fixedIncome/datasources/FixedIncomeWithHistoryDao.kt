package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.datasources

import androidx.room.Dao
import androidx.room.MapInfo
import androidx.room.Query
import androidx.room.RoomWarnings
import androidx.room.Transaction
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models.FixedIncomeWithHistory
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface FixedIncomeWithHistoryDao : FixedIncomeDao, FixedIncomeHistoryDao {

    @Transaction
    fun insert(data: List<FixedIncomeWithHistory>) {

        deleteAllHistory()
        deleteAllFixedIncome()

        data.forEach {
            insert(it.fixedIncome)
            it.history.forEach { history ->
                insert(history)
            }
        }
    }

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Transaction
    @Query("SELECT * FROM FixedIncome INNER JOIN FixedIncomeHistory ON uuid = fixedIncome WHERE year = :year AND month = :month")
    fun get(year: Int, month: Int): Flow<List<FixedIncomeWithHistory>>

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Transaction
    @MapInfo(keyColumn = "bank")
    @Query("SELECT * FROM FixedIncome INNER JOIN FixedIncomeHistory ON uuid = fixedIncome WHERE month = :month AND year = :year ORDER BY bank ASC, name ASC")
    fun getGrouped(month: Int, year: Int): Flow<Map<String, List<FixedIncomeWithHistory>>>

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Transaction
    @Query("SELECT * FROM FixedIncome INNER JOIN FixedIncomeHistory ON uuid = fixedIncome WHERE dueDate > :startDueDate AND dueDate < :endDueDate AND amount != 0.0 AND month = :month AND year = :year ORDER BY dueDate")
    fun getFiltered(startDueDate: LocalDate, endDueDate: LocalDate, month: Int, year: Int): Flow<MutableList<FixedIncomeWithHistory>>

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Transaction
    @Query("SELECT * FROM FixedIncome INNER JOIN FixedIncomeHistory ON uuid = fixedIncome WHERE liquidity = :liquidity AND amount != 0.0 AND month = :month AND year = :year ORDER BY dueDate")
    fun getFiltered(liquidity: String, month: Int, year: Int): Flow<MutableList<FixedIncomeWithHistory>>
}