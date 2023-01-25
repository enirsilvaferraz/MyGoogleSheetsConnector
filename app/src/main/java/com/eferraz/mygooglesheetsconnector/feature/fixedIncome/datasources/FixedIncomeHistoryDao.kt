package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.datasources

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models.FixedIncomeHistory

@Dao
interface FixedIncomeHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: FixedIncomeHistory)

    @Query("DELETE FROM FixedIncomeHistory")
    fun deleteAllHistory()
}