package com.eferraz.mygooglesheetsconnector.di

import android.content.Context
import androidx.room.Room
import com.eferraz.mygooglesheetsconnector.BuildConfig
import com.eferraz.mygooglesheetsconnector.archtecture.database.GenericDao
import com.eferraz.mygooglesheetsconnector.archtecture.datasource.*
import com.eferraz.mygooglesheetsconnector.archtecture.repository.*
import com.eferraz.mygooglesheetsconnector.core.database.AppDatabase
import com.eferraz.mygooglesheetsconnector.core.datasource.FixedIncomeParcelable
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
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

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteDataSource

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
}

/**
 *
 */
@InstallIn(SingletonComponent::class)
@Module
object FixedIncomeProvideModule {

    @Provides
    fun provideDao(appDatabase: AppDatabase): GenericDao<FixedIncome> = appDatabase.getFixedIncomeDao()
}

/**
 *
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class FixedIncomeBindModule {

    @Binds
    abstract fun bindFixedIncomeParcelable(parcelable: FixedIncomeParcelable): ParcelableModel<FixedIncome>

    @Binds
    abstract fun bindReadableRepository(repository: GenericReadableRepositoryImpl<FixedIncome>): BaseReadableRepository<GenericReadableRepositoryImpl.Params, MutableList<FixedIncome>>

    @Binds
    abstract fun bindWritableRepository(repository: GenericWritableRepositoryImpl<FixedIncome>): BaseWritableRepository<FixedIncome>

    @RemoteDataSource
    @Binds
    abstract fun bindReadableRemoteDataSource(ds: GenericReadableRemoteDataSourceImpl<FixedIncome>): BaseReadableDataSource<FixedIncome>

    @LocalDataSource
    @Binds
    abstract fun bindReadableLocalDataSource(ds: GenericReadableLocalDataSourceImpl<FixedIncome>): BaseReadableDataSource<FixedIncome>

    @LocalDataSource
    @Binds
    abstract fun bindWritableLocalDataSource(ds: GenericWritableLocalDataSourceImpl<FixedIncome>): BaseWritableDataSource<FixedIncome>
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

    object Constants {
        const val SHEETS_KEY = "SHEETS_KEY"
    }
}