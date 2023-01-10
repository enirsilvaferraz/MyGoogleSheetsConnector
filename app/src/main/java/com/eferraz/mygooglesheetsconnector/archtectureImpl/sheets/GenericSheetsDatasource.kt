package com.eferraz.mygooglesheetsconnector.archtectureImpl.sheets

import com.eferraz.googlesheets.providers.SheetsProvider
import com.eferraz.mygooglesheetsconnector.archtecture.datasource.BaseReadableDataSource
import com.eferraz.mygooglesheetsconnector.di.StringModules
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Named

class GenericSheetsDatasource<Result> @Inject constructor(
    @Named(StringModules.Constants.SHEETS_KEY) private val sheetsKey: String,
    @Named(StringModules.Constants.SHEETS_FIXED_INCOME_RANGE) private val range: String,
    private val sheetsProvider: SheetsProvider,
    private val parcelableModel: ParcelableModel<Result>
) : BaseReadableDataSource<Result> {

    override fun get() = flow {
        emit(sheetsProvider.get(sheetsKey, range).map {
            parcelableModel(it)
        }.toMutableList())
    }.flowOn(IO)
}