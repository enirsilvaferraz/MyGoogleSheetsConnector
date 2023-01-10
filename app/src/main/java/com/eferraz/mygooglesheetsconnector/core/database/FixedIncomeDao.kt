package com.eferraz.mygooglesheetsconnector.core.database

import androidx.room.Dao
import androidx.room.Query
import com.eferraz.mygooglesheetsconnector.archtectureImpl.database.GenericRoomDatasource
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import kotlinx.coroutines.flow.Flow

@Dao
interface FixedIncomeDao : GenericRoomDatasource<FixedIncome> {

    @Query("SELECT * FROM FixedIncome")
    override fun get(): Flow<MutableList<FixedIncome>>
}