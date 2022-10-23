package com.eferraz.mygooglesheetsconnector.datasources

import com.eferraz.finance.domain.datasources.EnvironmentDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class EnvironmentDataSourceModule {

    @Binds
    abstract fun bindDataSource(dataSource: EnvironmentDataSourceImpl): EnvironmentDataSource
}