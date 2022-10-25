package com.eferraz.mygooglesheetsconnector.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome

@Database(entities = [FixedIncome::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getFixedIncomeDao(): FixedIncomeDao
}