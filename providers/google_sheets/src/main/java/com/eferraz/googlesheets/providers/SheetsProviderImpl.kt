package com.eferraz.googlesheets.providers

import android.content.Context
import com.eferraz.googlesheets.data.SheetsException.Companion.resolve
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.gson.GsonFactory
import com.google.api.client.util.ExponentialBackOff
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.SheetsScopes
import com.google.api.services.sheets.v4.model.ValueRange
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SheetsProviderImpl @Inject constructor(@ApplicationContext private val context: Context) : SheetsProvider {

    private fun getSheets(): Sheets {

        val jsonFactory: JsonFactory = GsonFactory.getDefaultInstance()
        val httpTransport: HttpTransport = GoogleNetHttpTransport.newTrustedTransport()

        val credential = GoogleAccountCredential.usingOAuth2(context, SheetsScopes.all()).setBackOff(ExponentialBackOff()).also {
            it.selectedAccount = GoogleSignIn.getLastSignedInAccount(context)?.account
        }

        return Sheets.Builder(httpTransport, jsonFactory, credential).setApplicationName("Google-SheetsSample/0.1").build()
    }

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

    override fun get(sheetID: String, range: String): MutableList<MutableList<Any>> = runCatching {

        val request = getSheets().spreadsheets().values().get(sheetID, range)
        val response: ValueRange = request.execute()

        response.getValues().filter { it.isNotEmpty() }.toMutableList()

    }.getOrElse {
        throw it.resolve()
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