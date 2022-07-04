package com.eferraz.mygooglesheetsconnector.datasources

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SheetsDataSourceModule {

    @Binds
    abstract fun bindDataSource(dataSource: SheetsDataSourceImpl): SheetsDataSource
}