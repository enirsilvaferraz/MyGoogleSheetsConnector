package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.datasources

import androidx.room.Dao
import androidx.room.MapInfo
import androidx.room.Query
import com.eferraz.mygooglesheetsconnector.core.database.GenericRoomDatasource
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models.FixedIncome
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface FixedIncomeDao : GenericRoomDatasource<FixedIncome> {

    @MapInfo(keyColumn = "bank")
    @Query("SELECT * FROM FixedIncome WHERE month = :month AND year = :year ORDER BY bank ASC, name ASC")
    fun getGrouped(
        month: Int,
        year: Int
    ): Flow<Map<String, List<FixedIncome>>>

    @Query("SELECT * FROM FixedIncome WHERE dueDate > :startDueDate AND dueDate < :endDueDate AND amount != 0.0 AND month = :month AND year = :year ORDER BY dueDate")
    fun getFiltered(
        startDueDate: LocalDate,
        endDueDate: LocalDate,
        month: Int,
        year: Int
    ): Flow<MutableList<FixedIncome>>

    @Query("SELECT * FROM FixedIncome WHERE liquidity = :liquidity AND amount != 0.0 AND month = :month AND year = :year ORDER BY dueDate")
    fun getFiltered(
        liquidity: String,
        month: Int,
        year: Int
    ): Flow<MutableList<FixedIncome>>
}
