package com.eferraz.mygooglesheetsconnector.repositories

import com.eferraz.mygooglesheetsconnector.entities.FixedIncome
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Qualifier

@Module
@InstallIn(ViewModelComponent::class)
abstract class SheetsPageRepositoryModule {

    @FixedIncomePage
    @Binds
    abstract fun bindFixedIncomePage(repository: FixedIncomeRepositoryImpl): SheetsPageRepository<FixedIncome>
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FixedIncomePage