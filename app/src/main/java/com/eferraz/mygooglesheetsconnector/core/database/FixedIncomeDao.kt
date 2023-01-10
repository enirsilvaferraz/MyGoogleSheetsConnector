package com.eferraz.mygooglesheetsconnector.core.database

import androidx.room.Dao
import androidx.room.Query
import com.eferraz.mygooglesheetsconnector.archtectureImpl.database.GenericRoomDatasource
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import kotlinx.coroutines.flow.Flow

@Dao
interface FixedIncomeDao : GenericRoomDatasource<FixedIncome> {

    @Query("SELECT * FROM FixedIncome WHERE month = 01 AND year = 2023 ORDER BY year DESC, month DESC")
    override fun get(): Flow<MutableList<FixedIncome>>

    @Query("SELECT * FROM FixedIncome WHERE month = :month AND year = :year")
    fun get(year: Int, month: Int): Flow<MutableList<FixedIncome>>
}