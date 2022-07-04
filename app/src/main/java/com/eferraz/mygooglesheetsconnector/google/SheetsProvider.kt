package com.eferraz.mygooglesheetsconnector.google

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.sheets.v4.Sheets
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SheetsProvider @Inject constructor() {

    lateinit var credential: GoogleAccountCredential

    fun getSheets(): Sheets {
        val jsonFactory: JsonFactory = GsonFactory.getDefaultInstance()
        val httpTransport: HttpTransport = GoogleNetHttpTransport.newTrustedTransport()
        return Sheets.Builder(httpTransport, jsonFactory, credential).setApplicationName("Google-SheetsSample/0.1").build()
    }
}