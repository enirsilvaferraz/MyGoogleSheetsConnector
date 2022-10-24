package com.eferraz.mygooglesheetsconnector.repositories

import com.eferraz.finance.domain.archtecture.BaseRepository
import com.eferraz.finance.domain.entities.FixedIncome
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
abstract class Modules {

    //@FixedIncomePage
    @Binds
    abstract fun bind(repository: FixedIncomeRepositoryImpl): BaseRepository<MutableList<FixedIncome>>
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FixedIncomePage