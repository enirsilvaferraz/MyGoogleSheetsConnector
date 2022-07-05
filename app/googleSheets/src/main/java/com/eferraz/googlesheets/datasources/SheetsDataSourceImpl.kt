package com.eferraz.googlesheets.datasources

import com.eferraz.googlesheets.providers.SheetsInstanceProvider
import com.google.android.gms.auth.UserRecoverableAuthException
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException
import com.google.api.services.sheets.v4.model.ValueRange
import javax.inject.Inject

class SheetsDataSourceImpl @Inject constructor(
    private val sheetsProvider: SheetsInstanceProvider
) : SheetsDataSource {

    /**
     * https://developers.google.com/sheets/api/reference/rest/v4/spreadsheets.values/append
     */
    override fun append(sheetID: String, range: String?, values: MutableList<MutableList<*>>) = runCatching {

        val body = ValueRange().apply {
            majorDimension = Dimension.ROWS.name
            this.range = range
            setValues(values)
        }

        val request = sheetsProvider.getSheets().spreadsheets().values().append(sheetID, body.range, body).apply {
            valueInputOption = ValueInputOption.RAW.name
            insertDataOption = InsertDataOption.INSERT_ROWS.name
        }

        request.execute()

        DataSourceResponse.Success<Unit>()

    }.getOrElse {
        createFailure(it)
    }

    override fun get(sheetID: String, range: String?): DataSourceResponse = runCatching {

        val request = sheetsProvider.getSheets().spreadsheets().values().get(sheetID, range)
        val response: ValueRange = request.execute()

        DataSourceResponse.Success(response.getValues())

    }.getOrElse {
        createFailure(it)
    }

    private fun createFailure(it: Throwable) = when (it) {
        is UserRecoverableAuthIOException -> DataSourceResponse.Failure(it, it.intent)
        is UserRecoverableAuthException -> DataSourceResponse.Failure(it, it.intent)
        else -> DataSourceResponse.Failure(it)
    }

    private enum class ValueInputOption {
        USER_ENTERED, INPUT_VALUE_OPTION_UNSPECIFIED, RAW
    }

    private enum class InsertDataOption {
        INSERT_ROWS, OVERWRITE
    }

    private enum class Dimension {
        COLUMNS, ROWS, DIMENSION_UNSPECIFIED
    }
}