package com.eferraz.mygooglesheetsconnector.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.datasources.FixedIncomeAndHistoryDao
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.datasources.FixedIncomeDao
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.datasources.FixedIncomeHistoryDao
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.datasources.FixedIncomeWithHistoryDao
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models.FixedIncome
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models.FixedIncomeHistory

@Database(entities = [FixedIncome::class, FixedIncomeHistory::class], version = 1)
@TypeConverters(LocalDateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getFixedIncomeDao(): FixedIncomeDao
    abstract fun getFixedIncomeHistoryDao(): FixedIncomeHistoryDao
    abstract fun getFixedIncomeWithHistoryDao(): FixedIncomeWithHistoryDao
    abstract fun getFixedIncomeAndHistoryDao(): FixedIncomeAndHistoryDao
}