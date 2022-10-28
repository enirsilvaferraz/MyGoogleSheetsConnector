package com.eferraz.mygooglesheetsconnector.core.database

import androidx.room.Dao
import androidx.room.Query
import com.eferraz.mygooglesheetsconnector.archtecture.database.GenericDao
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome

@Dao
interface FixedIncomeDao : GenericDao<FixedIncome> {

    @Query("SELECT * FROM FixedIncome")
    override suspend fun get(): MutableList<FixedIncome>
}