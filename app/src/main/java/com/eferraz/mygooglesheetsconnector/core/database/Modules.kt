package com.eferraz.mygooglesheetsconnector.core.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class Modules {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(appContext, AppDatabase::class.java, "GoogleSheetsAppDatabase").build()

    @Provides
    fun provideFixedIncomeDao(appDatabase: AppDatabase): FixedIncomeDao = appDatabase.getFixedIncomeDao()
}