package com.eferraz.googlesheets.providers

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.eferraz.googlesheets.data.SheetsException.Companion.resolve
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

abstract class GoogleSignInActivityAbs : ComponentActivity() {

    private lateinit var client: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    protected val vm: GoogleSignInViewModel by viewModels()

    private val handleSignInResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) prepareSignIn(result.data)
        else vm.onSignInCanceled()
    }

    private val handleIntentError = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        signOut()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(vm)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.signInState.collect {
                    when (it) {
                        GoogleSignInState.CheckStatus -> checkLoginStatus()
                        GoogleSignInState.RequestSignIn -> requestSignIn()
                        GoogleSignInState.Idle -> {}
                    }
                }
            }
        }
    }

    private fun checkLoginStatus() {
        client = GoogleSignIn.getClient(this, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build())
        auth = Firebase.auth

        if (GoogleSignIn.getLastSignedInAccount(this) != null) vm.onSignInReady()
        else vm.onSignInNeeded()
    }

    private fun requestSignIn() {
        handleSignInResult.launch(client.signInIntent)
    }

    private fun prepareSignIn(data: Intent?) {
        val token = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException::class.java).idToken
        if (token != null) auth.signInWithCredential(GoogleAuthProvider.getCredential(token, null)).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) vm.onSignInReady()
            else vm.onSignInFailure()
        }
        else vm.onEmptyToken()
    }

    private fun signOut() {
        client.signOut()
        auth.signOut()
        finish()
    }

    protected fun resolveFailure(throwable: Throwable) = throwable.resolve().intent?.let { handleIntentError.launch(it) }
}