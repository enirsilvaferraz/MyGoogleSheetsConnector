package com.eferraz.googlesheets.datasources

import android.content.Intent
import com.eferraz.googlesheets.datasources.SheetsException.Companion.resolve
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
/*
    override fun append(sheetID: String, range: String?, values: MutableList<MutableList<*>>) = DataSourceResponse.result {

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
    }

 */

    override fun <T> get(sheetID: String, range: String?, transformation: (List<Any>) -> T): SheetsResponse<List<T>> = SheetsResponse.result {

        val request = sheetsProvider.getSheets().spreadsheets().values().get(sheetID, range)
        val response: ValueRange = request.execute()

        response.getValues().map(transformation)
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