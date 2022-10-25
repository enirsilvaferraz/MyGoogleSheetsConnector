package com.eferraz.mygooglesheetsconnector.core.data

import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
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
    abstract fun bind(repository: FixedIncomeRepositoryImpl): BaseRepository<FixedIncomeRepositoryImpl.Params, MutableList<FixedIncome>>
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FixedIncomePage