package com.eferraz.mygooglesheetsconnector.core.database

import androidx.room.Dao
import androidx.room.Query
import com.eferraz.mygooglesheetsconnector.archtecture.database.GenericDao
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import kotlinx.coroutines.flow.Flow

@Dao
interface FixedIncomeDao : GenericDao<FixedIncome> {

    @Query("SELECT * FROM FixedIncome")
    override fun get(): Flow<MutableList<FixedIncome>>
}