package com.eferraz.mygooglesheetsconnector.core.network.di

import com.eferraz.mygooglesheetsconnector.core.domain.entities.FixedIncome
import com.eferraz.mygooglesheetsconnector.core.network.datasources.BaseReadableDataSource
import com.eferraz.mygooglesheetsconnector.core.network.datasources.FixedIncomeSheetsDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class Modules {

    @Binds
    abstract fun bindFixedIncomeSheetsDataSource(dataSource: FixedIncomeSheetsDataSourceImpl): BaseReadableDataSource<List<FixedIncome>>
}
