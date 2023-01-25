package com.eferraz.mygooglesheetsconnector.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.datasources.FixedIncomeDao
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models.FixedIncome

@Database(entities = [FixedIncome::class], version = 1)
@TypeConverters(LocalDateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getFixedIncomeDao(): FixedIncomeDao
}