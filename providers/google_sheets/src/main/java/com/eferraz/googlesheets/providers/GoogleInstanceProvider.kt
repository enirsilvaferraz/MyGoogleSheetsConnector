package com.eferraz.googlesheets.providers

import android.app.Activity
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GoogleInstanceProvider @Inject constructor() {

    private lateinit var client: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    fun login(activity: ComponentActivity, onSignInReady: () -> Unit) {

        if (!this::client.isInitialized) {
            client = GoogleSignIn.getClient(activity, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build())
            auth = Firebase.auth
        }

        if (GoogleSignIn.getLastSignedInAccount(activity) == null) startSignIn(activity, onSignInReady) else onSignInReady()
    }

    private fun startSignIn(activity: ComponentActivity, onSignInReady: () -> Unit) =
        activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) signIn(result, activity, onSignInReady)
        }.launch(client.signInIntent)

    private fun signIn(result: ActivityResult, activity: ComponentActivity, onSignInReady: () -> Unit) {
        val token = GoogleSignIn.getSignedInAccountFromIntent(result.data).getResult(ApiException::class.java).idToken!!
        auth.signInWithCredential(GoogleAuthProvider.getCredential(token, null)).addOnCompleteListener(activity) { task ->
            if (task.isSuccessful) onSignInReady()
        }
    }

    fun handleError(activity: ComponentActivity, intent: Intent?) {
        intent?.let {
            activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                signOut(activity)
            }.launch(intent)
        }
    }

    private fun signOut(activity: ComponentActivity) {
        client.signOut()
        auth.signOut()
        activity.finish()
    }
}