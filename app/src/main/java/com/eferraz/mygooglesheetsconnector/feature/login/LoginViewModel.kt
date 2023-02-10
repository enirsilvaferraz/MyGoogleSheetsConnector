package com.eferraz.mygooglesheetsconnector.feature.login

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel : ViewModel(), DefaultLifecycleObserver {

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(State.Starting)
    val uiState = _uiState.asStateFlow()

    fun onStart(account: GoogleSignInAccount?) {
        if (account != null) _uiState.value = State.Success
        else _uiState.value = State.Loading
    }

    fun onTryAgain() {
        _uiState.value = State.Loading
    }

    fun onSuccess() {
        _uiState.value = State.Success
    }

    fun onFailure() {
        _uiState.value = State.Failure
    }

    sealed interface State {
        object Starting : State
        object Loading : State
        object Success : State
        object Failure : State
    }
}