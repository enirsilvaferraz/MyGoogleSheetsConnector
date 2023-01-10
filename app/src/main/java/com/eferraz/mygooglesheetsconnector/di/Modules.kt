package com.eferraz.mygooglesheetsconnector.di

import android.content.Context
import androidx.room.Room
import com.eferraz.mygooglesheetsconnector.BuildConfig
import com.eferraz.mygooglesheetsconnector.archtecture.datasource.*
import com.eferraz.mygooglesheetsconnector.archtecture.repository.*
import com.eferraz.mygooglesheetsconnector.archtectureImpl.database.GenericRoomDatasource
import com.eferraz.mygooglesheetsconnector.archtectureImpl.sheets.GenericSheetsDatasource
import com.eferraz.mygooglesheetsconnector.archtectureImpl.sheets.ParcelableModel
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
    fun provideDao(appDatabase: AppDatabase): GenericRoomDatasource<FixedIncome> = appDatabase.getFixedIncomeDao()
}

/**
 *
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class FixedIncomeBindModule {

    @Binds
    abstract fun bindParcelable(parcelable: FixedIncomeParcelable): ParcelableModel<FixedIncome>

    @RemoteDataSource
    @Binds
    abstract fun bindReadableRemoteDataSource(ds: GenericSheetsDatasource<FixedIncome>): BaseReadableDataSource<FixedIncome>

    @LocalDataSource
    @Binds
    abstract fun bindReadableLocalDataSource(ds: GenericRoomDatasource<FixedIncome>): BaseReadableDataSource<FixedIncome>

    @LocalDataSource
    @Binds
    abstract fun bindWritableLocalDataSource(ds: GenericRoomDatasource<FixedIncome>): BaseWritableDataSource<FixedIncome>

    @LocalDataSource
    @Binds
    abstract fun bindReadableLocalRepository(repository: GenericReadableRepositoryImpl<FixedIncome>): BaseReadableRepository<Unit, MutableList<FixedIncome>>

    @LocalDataSource
    @Binds
    abstract fun bindWritableLocalRepository(repository: GenericWritableRepositoryImpl<FixedIncome>): BaseWritableRepository<FixedIncome>

    @RemoteDataSource
    @Binds
    abstract fun bindReadableRemoteRepository(repository: GenericReadableRemoteRepositoryImpl<FixedIncome>): BaseReadableRepository<Unit, MutableList<FixedIncome>>
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
    fun provideFixedIncomeRange() = "'Histórico Renda Fixa'!A2:Z1000"

    object Constants {
        const val SHEETS_KEY = "SHEETS_KEY"
        const val SHEETS_FIXED_INCOME_RANGE = "SHEETS_FIXED_INCOME_RANGE"
    }
}