package com.eferraz.mygooglesheetsconnector.core.network

import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class Modules {

    @Binds
    abstract fun bindFixedIncomeSheetsDataSource(dataSource: FixedIncomeSheetsDataSourceImpl): BaseReadableDataSource<MutableList<FixedIncome>>

    @Binds
    abstract fun bindEnvironmentDataSource(dataSource: EnvironmentDataSourceImpl): EnvironmentDataSource
}
