package com.eferraz.mygooglesheetsconnector.core.data.di

import com.eferraz.mygooglesheetsconnector.core.data.repositories.BaseRepository
import com.eferraz.mygooglesheetsconnector.core.data.repositories.FixedIncomeRepositoryImpl
import com.eferraz.mygooglesheetsconnector.core.domain.entities.FixedIncome
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