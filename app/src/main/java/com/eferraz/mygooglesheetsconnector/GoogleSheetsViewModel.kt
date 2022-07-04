package com.eferraz.mygooglesheetsconnector

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eferraz.mygooglesheetsconnector.entities.FixedIncome
import com.eferraz.mygooglesheetsconnector.repositories.FixedIncomePage
import com.eferraz.mygooglesheetsconnector.repositories.SheetsPageRepository
import com.google.android.gms.auth.UserRecoverableAuthException
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoogleSheetsViewModel @Inject constructor(
    @FixedIncomePage private val repo: SheetsPageRepository<FixedIncome>
) : ViewModel() {

    val uiState = MutableStateFlow<UiState>(UiState.Loading)

    fun getData() = viewModelScope.launch(IO) {
        runCatching {
            repo.get().collect {
                uiState.value = UiState.Success(it)
            }
        }.getOrElse {
            uiState.value = verifyException(it)
        }
    }

    fun append() = viewModelScope.launch(IO) {
        repo.append(FixedIncome("A", "B", "C", "D", "E"))
        getData()
    }

    private fun verifyException(exception: Throwable) = when (exception) {
        is UserRecoverableAuthIOException -> UiState.Failure(exception, exception.intent)
        is UserRecoverableAuthException -> UiState.Failure(exception, exception.intent)
        else -> UiState.Failure(exception)
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val data: List<FixedIncome>) : UiState()
        data class Failure(val exception: Throwable, val intent: Intent? = null) : UiState()
    }
}