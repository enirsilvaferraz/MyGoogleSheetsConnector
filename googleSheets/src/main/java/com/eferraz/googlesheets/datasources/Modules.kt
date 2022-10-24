package com.eferraz.googlesheets.datasources

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class Modules {

    @Binds
    abstract fun bindDataSource(dataSource: SheetsProviderImpl): SheetsProvider
}