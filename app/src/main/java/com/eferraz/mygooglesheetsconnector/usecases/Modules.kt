package com.eferraz.mygooglesheetsconnector.usecases

import com.eferraz.finance.domain.archtecture.BaseUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class GetFixedIncomeListUseCaseModule {

    @Binds
    abstract fun bind(bindable: GetFixedIncomeListUseCase): BaseUseCase<*, *>
}