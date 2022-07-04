package com.eferraz.mygooglesheetsconnector.google

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException
import com.google.api.client.util.ExponentialBackOff
import com.google.api.services.sheets.v4.SheetsScopes
import javax.inject.Inject

open class GoogleSignInActivity : ComponentActivity() {

    @Inject
    lateinit var provider: SheetsProvider

    override fun onStart() {
        super.onStart()
        checkIfSignedIn()
    }

    private fun checkIfSignedIn() {
        GoogleSignIn.getLastSignedInAccount(this).let { account ->
            if (account == null) signIn() else storeCredentials(account)
        }
    }

    private fun storeCredentials(account: GoogleSignInAccount) {
        provider.credential = GoogleAccountCredential.usingOAuth2(this, SheetsScopes.all()).setBackOff(ExponentialBackOff()).also {
            it.selectedAccount = account.account
        }
    }

    fun safeCall(function: () -> Unit) {
        try {
            function()
        } catch (e: Throwable) {
            if (e is UserRecoverableAuthIOException) registerForActivityResult(StartActivityForResult()) { }.launch(e.intent)
        }
    }

    private fun signIn() = registerForActivityResult(StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            storeCredentials(GoogleSignIn.getSignedInAccountFromIntent(result.data).getResult(ApiException::class.java))
        }
    }.launch(
        GoogleSignIn.getClient(this, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()).signInIntent
    )
}