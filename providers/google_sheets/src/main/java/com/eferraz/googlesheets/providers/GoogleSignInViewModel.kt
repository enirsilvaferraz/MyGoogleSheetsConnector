package com.eferraz.googlesheets.providers

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class GoogleSignInViewModel @Inject constructor() : ViewModel(), DefaultLifecycleObserver {

    private val _signInState: MutableStateFlow<GoogleSignInState> = MutableStateFlow(GoogleSignInState.Idle)
    internal val signInState: StateFlow<GoogleSignInState> = _signInState

    private val _clientState: MutableStateFlow<LoginClientState> = MutableStateFlow(LoginClientState.Idle)
    val clientState: StateFlow<LoginClientState> = _clientState

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        onStartProcess()
    }

    fun onSignInNeeded() {
        _signInState.value = GoogleSignInState.RequestSignIn
        _clientState.value = LoginClientState.Loading
    }

    fun onEmptyToken() {
        _signInState.value = GoogleSignInState.CheckStatus
    }

    fun onSignInReady() {
        _clientState.value = LoginClientState.Success
    }

    fun onSignInFailure() {
        _clientState.value = LoginClientState.Failure
    }

    fun onSignInCanceled() {
        _signInState.value = GoogleSignInState.Idle
        _clientState.value = LoginClientState.Idle
    }

    fun onTryAgainClicked() {
        _signInState.value = GoogleSignInState.CheckStatus
        _clientState.value = LoginClientState.Loading
    }

    fun onStartProcess() {
        _signInState.value = GoogleSignInState.CheckStatus
        _clientState.value = LoginClientState.Loading
    }
}

sealed interface GoogleSignInState {
    object Idle : GoogleSignInState
    object CheckStatus : GoogleSignInState
    object RequestSignIn : GoogleSignInState
}

sealed interface LoginClientState {
    object Idle : LoginClientState
    object Loading : LoginClientState
    object Success : LoginClientState
    object Failure : LoginClientState
}