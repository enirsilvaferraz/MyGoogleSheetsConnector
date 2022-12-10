package com.eferraz.googlesheets.providers

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.*
import com.eferraz.googlesheets.data.SheetsException.Companion.resolve
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

interface GoogleSignInProvider : DefaultLifecycleObserver

@ActivityScoped
class GoogleSignInProviderImpl @Inject constructor(
    @ActivityContext private val context: Context, private val vm: GoogleSignInViewModel
) : GoogleSignInProvider {

    private val activity: ComponentActivity = context as ComponentActivity

    private lateinit var client: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    private val handleSignInResult = activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) prepareSignIn(result.data)
        else vm.onSignInCanceled()
    }

    private val handleIntentError = activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        signOut()
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        activity.lifecycle.addObserver(vm)

        activity.lifecycleScope.launch {
            activity.repeatOnLifecycle(Lifecycle.State.STARTED) {
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
        client = GoogleSignIn.getClient(activity, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build())
        auth = Firebase.auth

        if (GoogleSignIn.getLastSignedInAccount(activity) != null) vm.onSignInReady()
        else vm.onSignInNeeded()
    }

    private fun requestSignIn() {
        handleSignInResult.launch(client.signInIntent)
    }

    private fun prepareSignIn(data: Intent?) {
        val token = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException::class.java).idToken
        if (token != null) auth.signInWithCredential(GoogleAuthProvider.getCredential(token, null)).addOnCompleteListener(activity) { task ->
            if (task.isSuccessful) vm.onSignInReady()
            else vm.onSignInFailure()
        }
        else vm.onEmptyToken()
    }

    private fun signOut() {
        client.signOut()
        auth.signOut()
        activity.finish()
    }

    fun resolveFailure(throwable: Throwable) = throwable.resolve().intent?.let { handleIntentError.launch(it) }
}