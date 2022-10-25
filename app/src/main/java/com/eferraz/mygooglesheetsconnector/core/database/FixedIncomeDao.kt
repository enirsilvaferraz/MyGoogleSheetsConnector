package com.eferraz.mygooglesheetsconnector.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome

@Dao
interface FixedIncomeDao {

    @Query("SELECT * FROM FixedIncome")
    suspend fun get(): MutableList<FixedIncome>

    @Insert
    suspend fun insert(vararg model: FixedIncome)

    @Update
    suspend fun update(vararg model: FixedIncome)
}