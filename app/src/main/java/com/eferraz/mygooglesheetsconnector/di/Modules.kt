package com.eferraz.mygooglesheetsconnector.di

import android.content.Context
import androidx.room.Room
import com.eferraz.googlesheets.providers.SheetsProvider
import com.eferraz.mygooglesheetsconnector.BuildConfig
import com.eferraz.mygooglesheetsconnector.archtecture.database.GenericDao
import com.eferraz.mygooglesheetsconnector.archtecture.datasource.*
import com.eferraz.mygooglesheetsconnector.archtecture.repository.BaseReadableRepository
import com.eferraz.mygooglesheetsconnector.archtecture.repository.BaseWritableRepository
import com.eferraz.mygooglesheetsconnector.archtecture.repository.GenericReadableRepositoryImpl
import com.eferraz.mygooglesheetsconnector.archtecture.repository.GenericWritableRepositoryImpl
import com.eferraz.mygooglesheetsconnector.core.database.AppDatabase
import com.eferraz.mygooglesheetsconnector.core.datasource.FixedIncomeParcelable
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import com.eferraz.mygooglesheetsconnector.di.StringModules.Constants.SHEETS_FIXED_INCOME_RANGE
import com.eferraz.mygooglesheetsconnector.di.StringModules.Constants.SHEETS_KEY
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 *
 */
@InstallIn(SingletonComponent::class)
@Module
object DatabaseModules {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(appContext, AppDatabase::class.java, "GoogleSheetsAppDatabase").build()

    @Provides
    fun provideFixedIncomeDao(appDatabase: AppDatabase): GenericDao<FixedIncome> = appDatabase.getFixedIncomeDao()
}

/**
 *
 */
@Module
@InstallIn(SingletonComponent::class)
object DataSourceModules {

    @RemoteDataSource
    @Provides
    fun provideReadableRemoteDataSource(
        @Named(SHEETS_KEY) sheetsKey: String,
        @Named(SHEETS_FIXED_INCOME_RANGE) range: String,
        sheetsProvider: SheetsProvider,
        parcelableModel: ParcelableModel<FixedIncome>
    ): BaseReadableDataSource<FixedIncome> = GenericRemoteDataSourceImpl(sheetsKey, range, sheetsProvider, parcelableModel)

    @LocalDataSource
    @Provides
    fun provideReadableLocalDataSource(dao: GenericDao<FixedIncome>): BaseReadableDataSource<FixedIncome> = GenericReadableLocalDataSourceImpl(dao)

    @LocalDataSource
    @Provides
    fun provideWritableLocalDataSource(dao: GenericDao<FixedIncome>): BaseWritableDataSource<FixedIncome> = GenericWritableLocalDataSourceImpl(dao)
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteDataSource

/**
 *
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class ParcelableModule {

    @Binds
    abstract fun bindFixedIncomeParcelable(parcelable: FixedIncomeParcelable): ParcelableModel<FixedIncome>
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModules {

    @Binds
    abstract fun bindReadableRepository(repository: GenericReadableRepositoryImpl<FixedIncome>): BaseReadableRepository<GenericReadableRepositoryImpl.Params, MutableList<FixedIncome>>

    @Binds
    abstract fun bindWritableRepository(repository: GenericWritableRepositoryImpl<FixedIncome>): BaseWritableRepository<FixedIncome>
}

/**
 *
 */
@Module
@InstallIn(SingletonComponent::class)
class StringModules {

    @Provides
    @Named(SHEETS_KEY)
    fun provideSheetsUrl() = BuildConfig.SHEET_KEY

    @Provides
    @Named(SHEETS_FIXED_INCOME_RANGE)
    fun provideFixedIncomeSheetsRange() = "'Histórico Renda Fixa'!A2:Z1000"

    object Constants {
        const val SHEETS_KEY = "SHEETS_KEY"
        const val SHEETS_FIXED_INCOME_RANGE = "SHEETS_FIXED_INCOME_RANGE"
    }
}