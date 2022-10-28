package com.eferraz.mygooglesheetsconnector.archtecture.datasource

import com.eferraz.googlesheets.providers.SheetsProvider
import javax.inject.Inject

class GenericRemoteDataSourceImpl<Result> @Inject constructor(
    private val sheetsKey: String,
    private val range: String,
    private val sheetsProvider: SheetsProvider,
    private val parcelableModel: ParcelableModel<Result>
) : BaseReadableDataSource<Result> {

    override suspend fun get() = sheetsProvider.get(sheetsKey, range).map { parcelableModel(it) }.toMutableList()
}