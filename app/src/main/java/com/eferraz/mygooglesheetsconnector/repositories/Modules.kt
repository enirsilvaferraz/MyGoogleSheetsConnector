package com.eferraz.mygooglesheetsconnector.repositories

import com.eferraz.finance.domain.archtecture.BaseRepository
import com.eferraz.finance.domain.entities.FixedIncome
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Qualifier

@Module
@InstallIn(ViewModelComponent::class)
abstract class SheetsPageRepositoryModule {

    //@FixedIncomePage
    @Binds
    abstract fun bind(repository: FixedIncomeRepositoryImpl): BaseRepository<MutableList<FixedIncome>>
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FixedIncomePage