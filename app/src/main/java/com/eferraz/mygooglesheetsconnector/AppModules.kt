package com.eferraz.mygooglesheetsconnector

import android.content.Context
import androidx.room.Room
import com.eferraz.googlesheets.SheetsModules
import com.eferraz.mygooglesheetsconnector.core.database.AppDatabase
import com.eferraz.mygooglesheetsconnector.core.database.FixedIncomeDao
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Singleton

@Module(includes = [SheetsModules::class])
@ComponentScan
class AppModules {

    @Singleton
    fun getDatabase(context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, "GoogleSheetsAppDatabase").build()

    @Singleton
    fun getFixedIncomeDao(database: AppDatabase): FixedIncomeDao = database.getFixedIncomeDao()

    //@Singleton
    //@Named("SHEETS_KEY")
    //fun getSheetsKey() = BuildConfig.SHEET_KEY

   // @Singleton
   // @Named("SHEETS_FIXED_INCOME_RANGE")
   // fun getSheetsFixedIncomeRange() = "'Histórico Renda Fixa'!A2:Z1000"
}