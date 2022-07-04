package com.eferraz.mygooglesheetsconnector.google

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.gson.GsonFactory
import com.google.api.client.util.ExponentialBackOff
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.SheetsScopes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SheetsInstanceProvider @Inject constructor(@ApplicationContext private val context: Context) {

    fun getSheets(): Sheets {

        val jsonFactory: JsonFactory = GsonFactory.getDefaultInstance()
        val httpTransport: HttpTransport = GoogleNetHttpTransport.newTrustedTransport()

        val credential = GoogleAccountCredential.usingOAuth2(context, SheetsScopes.all()).setBackOff(ExponentialBackOff()).also {
            it.selectedAccount = GoogleSignIn.getLastSignedInAccount(context)?.account
        }

        return Sheets.Builder(httpTransport, jsonFactory, credential).setApplicationName("Google-SheetsSample/0.1").build()
    }
}