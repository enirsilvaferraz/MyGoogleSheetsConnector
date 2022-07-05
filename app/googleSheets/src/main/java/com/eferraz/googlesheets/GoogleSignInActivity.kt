package com.eferraz.googlesheets


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


abstract class GoogleSignInActivity : ComponentActivity() {

    private lateinit var client: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    val registerThrowableResult = registerForActivityResult(StartActivityForResult()) { signOut() }

    private val registerSuccessfulResult = registerForActivityResult(StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) firebaseSignIn(getToken(result.data))
    }

    open fun configureGoogleSignIn(webClientId: String) {

        client = GoogleSignIn.getClient(
            this, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(webClientId)
                .requestEmail()
                .build()
        )

        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser == null) signIn() else onSignInReady()
    }

    private fun signIn() {
        registerSuccessfulResult.launch(client.signInIntent)
    }

    private fun signOut() {
        client.signOut()
        auth.signOut()
        finish()
    }

    private fun getToken(result: Intent?) = GoogleSignIn.getSignedInAccountFromIntent(result).getResult(ApiException::class.java).idToken!!

    private fun firebaseSignIn(idToken: String) {
        auth.signInWithCredential(GoogleAuthProvider.getCredential(idToken, null)).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) onSignInReady()
        }
    }

    abstract fun onSignInReady()
}