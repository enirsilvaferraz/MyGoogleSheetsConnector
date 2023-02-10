package com.eferraz.mygooglesheetsconnector.feature.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eferraz.mygooglesheetsconnector.feature.home.domain.GetHomeDataUseCase
import com.eferraz.mygooglesheetsconnector.feature.sync.domain.SynchronizeDataBaseUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel constructor(getHomeDataUseCase: GetHomeDataUseCase, private val synchronizeUseCase: SynchronizeDataBaseUseCase) : ViewModel() {

    private val _uiState: Flow<List<GetHomeDataUseCase.HomeItem>> = getHomeDataUseCase()
    val uiState = _uiState

    private val _message: MutableStateFlow<String> = MutableStateFlow("")
    val message: StateFlow<String> = _message

    fun onSyncClicked() {
        viewModelScope.launch(IO) {
            synchronizeUseCase()
                .onCompletion {
                    _message.value = if (it != null) "Sincronização Falhou!" else "Sincronização concluída!"
                }.catch {
                    _message.value = "Sincronização Falhou!"
                }.collect()
        }
    }
}