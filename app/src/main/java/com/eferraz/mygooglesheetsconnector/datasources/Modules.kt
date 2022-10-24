package com.eferraz.mygooglesheetsconnector.datasources

import com.eferraz.finance.domain.archtecture.BaseReadableDataSource
import com.eferraz.finance.domain.datasources.EnvironmentDataSource
import com.eferraz.finance.domain.entities.FixedIncome
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class Modules {

    @Binds
    abstract fun bindEnvironmentDataSource(dataSource: EnvironmentDataSourceImpl): EnvironmentDataSource


    @Binds
    abstract fun bindFixedIncomeSheetsDataSource(dataSource: FixedIncomeSheetsDataSourceImpl): BaseReadableDataSource<List<FixedIncome>>
}
