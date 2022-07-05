package com.eferraz.mygooglesheetsconnector

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eferraz.googlesheets.datasources.DataSourceResponse.Failure
import com.eferraz.googlesheets.datasources.DataSourceResponse.Success
import com.eferraz.mygooglesheetsconnector.entities.FixedIncome
import com.eferraz.mygooglesheetsconnector.repositories.FixedIncomePage
import com.eferraz.mygooglesheetsconnector.repositories.SheetsPageRepository
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

        repo.get().collect {
            uiState.value = when (it) {
                is Success<*> -> UiState.Success(it.data as List<FixedIncome>)
                is Failure -> UiState.Failure(it.e, it.intent)
            }
        }
    }

    fun append() = viewModelScope.launch(IO) {
        repo.append(FixedIncome("A", "B", "C", "D", "E"))
        getData()
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val data: List<FixedIncome>) : UiState()
        data class Failure(val exception: Throwable, val intent: Intent? = null) : UiState()
    }
}