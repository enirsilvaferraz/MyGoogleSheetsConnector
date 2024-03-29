package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.datasources

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models.FixedIncome

@Dao
interface FixedIncomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: FixedIncome)

    @Query("DELETE FROM FixedIncome")
    fun deleteAllFixedIncome()
}