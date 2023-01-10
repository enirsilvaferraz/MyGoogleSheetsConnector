package com.eferraz.mygooglesheetsconnector.di

import androidx.room.Room
import com.eferraz.mygooglesheetsconnector.archtecture.datasource.BaseReadableDataSource
import com.eferraz.mygooglesheetsconnector.archtecture.repository.BaseReadableRepository
import com.eferraz.mygooglesheetsconnector.archtecture.repository.GenericReadableRepositoryImpl
import com.eferraz.mygooglesheetsconnector.core.database.AppDatabase
import com.eferraz.mygooglesheetsconnector.core.domain.GetFixedIncomeListUseCase
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.list.FixedIncomeListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

object KoinModules {

    val modules = module {

        single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, "GoogleSheetsAppDatabase").build() } bind AppDatabase::class

        single { get<AppDatabase>().getFixedIncomeDao() } bind BaseReadableDataSource::class
        factory { GenericReadableRepositoryImpl<FixedIncome>(datasource = get()) } bind BaseReadableRepository::class
        factoryOf(::GetFixedIncomeListUseCase)
        viewModelOf(::FixedIncomeListViewModel)
    }
}