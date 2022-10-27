package com.eferraz.mygooglesheetsconnector.core.network

import com.eferraz.mygooglesheetsconnector.BuildConfig
import com.eferraz.mygooglesheetsconnector.core.datasource.BaseReadableDataSource
import com.eferraz.mygooglesheetsconnector.core.datasource.local.FixedIncomeLocalDataSourceImpl
import com.eferraz.mygooglesheetsconnector.core.datasource.remote.FixedIncomeRemoteDataSourceImpl
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import com.eferraz.mygooglesheetsconnector.core.network.Constants.SHEETS_KEY
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
abstract class Modules {

    @RemoteDataSource
    @Binds
    abstract fun bindFixedIncomeRemoteDataSource(dataSource: FixedIncomeRemoteDataSourceImpl): BaseReadableDataSource<FixedIncome>

    @LocalDataSource
    @Binds
    abstract fun bindFixedIncomeLocalDataSource(dataSource: FixedIncomeLocalDataSourceImpl): BaseReadableDataSource<FixedIncome>

    @Binds
    abstract fun bindEnvironmentDataSource(dataSource: EnvironmentDataSourceImpl): EnvironmentDataSource
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteDataSource

@Module
@InstallIn(SingletonComponent::class)
class StringModules {

    @Provides
    @Named(SHEETS_KEY)
    fun provideSheetsUrl() = BuildConfig.SHEET_KEY
}

object Constants {
    const val SHEETS_KEY = "SHEETS_KEY"
}