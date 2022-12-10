package com.eferraz.mygooglesheetsconnector.archtecture.datasource

import com.eferraz.googlesheets.providers.SheetsProvider
import com.eferraz.mygooglesheetsconnector.di.StringModules
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Named

class GenericReadableRemoteDataSourceImpl<Result> @Inject constructor(
    @Named(StringModules.Constants.SHEETS_KEY)  private val sheetsKey: String,
    private val sheetsProvider: SheetsProvider,
    private val parcelableModel: ParcelableModel<Result>
) : BaseReadableDataSource<Result> {

    override fun get() = flow {
        emit(sheetsProvider.get(sheetsKey, parcelableModel.getRange()).map {
            parcelableModel(it)
        }.toMutableList())
    }.flowOn(IO)
}