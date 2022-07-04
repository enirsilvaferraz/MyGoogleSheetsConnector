package com.eferraz.mygooglesheetsconnector.datasources

import com.eferraz.mygooglesheetsconnector.google.SheetsInstanceProvider
import com.google.api.services.sheets.v4.model.ValueRange
import javax.inject.Inject

class SheetsDataSourceImpl @Inject constructor(
    private val sheetsProvider: SheetsInstanceProvider
) : SheetsDataSource {

    /**
     * https://developers.google.com/sheets/api/reference/rest/v4/spreadsheets.values/append
     */
    override fun append(sheetID: String, range: String?, values: MutableList<MutableList<*>>) {

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

    override fun get(sheetID: String, range: String?): MutableList<MutableList<*>> {

        val request = sheetsProvider.getSheets().spreadsheets().values().get(sheetID, range)
        val response: ValueRange = request.execute()

        return response.getValues()
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