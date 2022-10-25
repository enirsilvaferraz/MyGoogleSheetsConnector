package com.eferraz.mygooglesheetsconnector.core.network

import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
abstract class Modules {

    @RemoteDataSource
    @Binds
    abstract fun bindFixedIncomeRemoteDataSource(dataSource: FixedIncomeRemoteDataSourceImpl): BaseReadableDataSource<MutableList<FixedIncome>>

    @LocalDataSource
    @Binds
    abstract fun bindFixedIncomeLocalDataSource(dataSource: FixedIncomeLocalDataSourceImpl): BaseReadableDataSource<MutableList<FixedIncome>>

    @Binds
    abstract fun bindEnvironmentDataSource(dataSource: EnvironmentDataSourceImpl): EnvironmentDataSource
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteDataSource