package com.eferraz.mygooglesheetsconnector.datasources

import com.eferraz.finance.domain.archtecture.BaseReadableDataSource
import com.eferraz.finance.domain.datasources.EnvironmentDataSource
import com.eferraz.finance.domain.entities.FixedIncome
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class EnvironmentDataSourceModule {

    @Binds
    abstract fun bind(dataSource: EnvironmentDataSourceImpl): EnvironmentDataSource
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class FixedIncomeSheetsDataSourceModule {

    @Binds
    abstract fun bind(dataSource: FixedIncomeSheetsDataSourceImpl): BaseReadableDataSource<List<FixedIncome>>
}
