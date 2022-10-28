package com.eferraz.mygooglesheetsconnector.archtecture.datasource

import com.eferraz.googlesheets.providers.SheetsProvider
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GenericReadableRemoteDataSourceImpl<Result> @Inject constructor(
    private val sheetsKey: String,
    private val range: String,
    private val sheetsProvider: SheetsProvider,
    private val parcelableModel: ParcelableModel<Result>
) : BaseReadableDataSource<Result> {

    override fun get() = flow { emit(sheetsProvider.get(sheetsKey, range).map { parcelableModel(it) }.toMutableList()) }.flowOn(IO)
}