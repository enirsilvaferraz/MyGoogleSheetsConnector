package com.eferraz.googlesheets

import com.eferraz.googlesheets.providers.SheetsProvider
import com.eferraz.googlesheets.providers.SheetsProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class Modules {

    @Binds
    abstract fun bindDataSource(dataSource: SheetsProviderImpl): SheetsProvider
}