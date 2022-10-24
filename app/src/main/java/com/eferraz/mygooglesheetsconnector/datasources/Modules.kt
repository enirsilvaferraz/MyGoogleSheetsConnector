package com.eferraz.mygooglesheetsconnector.datasources

import com.eferraz.mygooglesheetsconnector.core.domain.datasources.EnvironmentDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class Modules {

    @Binds
    abstract fun bindEnvironmentDataSource(dataSource: EnvironmentDataSourceImpl): EnvironmentDataSource
}
