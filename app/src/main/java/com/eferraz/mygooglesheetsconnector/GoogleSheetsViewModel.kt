package com.eferraz.mygooglesheetsconnector

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    val uiState = MutableStateFlow<UiState>(UiState.Success(mutableListOf()))

    init {
        getData()
    }

    private fun getData() = viewModelScope.launch(IO) {
        repo.get().collect {
            uiState.value = UiState.Success(it)
        }
    }

    fun append() = viewModelScope.launch(IO) {
        repo.append(FixedIncome("A", "B", "C", "D", "E"))
        getData()
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val data: List<FixedIncome>) : UiState()
    }
}