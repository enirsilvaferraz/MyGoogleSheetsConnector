package com.eferraz.mygooglesheetsconnector.datasources

import com.eferraz.finance.domain.archtecture.GenericReadableDataSource
import com.eferraz.finance.domain.entities.FixedIncome
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class FixedIncomeSheetsDataSourceModule {

    @Binds
    abstract fun bind(dataSource: FixedIncomeSheetsDataSourceImpl): GenericReadableDataSource<List<FixedIncome>>
}
