package com.eferraz.mygooglesheetsconnector.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eferraz.mygooglesheetsconnector.core.domain.GetFixedIncomeInReleaseUseCase
import com.eferraz.mygooglesheetsconnector.core.domain.SynchronizeDataBaseUseCase
import com.eferraz.mygooglesheetsconnector.core.model.BaseModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel constructor(
    getFixedIncomeListUseCase: GetFixedIncomeInReleaseUseCase,
    private val synchronizeDataBaseUseCase: SynchronizeDataBaseUseCase
) : ViewModel() {

    private val _uiState: Flow<Map<String, List<BaseModel>>> = merge(
        getFixedIncomeListUseCase().map { list -> list.groupBy { it::class.simpleName.orEmpty() } }
    )

    val uiState = _uiState

    private val _message: MutableStateFlow<String> = MutableStateFlow("")
    val message: StateFlow<String> = _message

    fun onSyncClicked() {
        viewModelScope.launch(IO) {
            synchronizeDataBaseUseCase().onCompletion {
                _message.value = if (it != null) "Sincronização Falhou!" else "Sincronização concluída!"
            }.collect()
        }
    }
}