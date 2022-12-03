package com.eferraz.mygooglesheetsconnector.feature.home

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eferraz.mygooglesheetsconnector.core.domain.GetFixedIncomeListUseCase
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FixedIncomeListViewModel @Inject constructor(
    private val getFixedIncomeListUseCase: GetFixedIncomeListUseCase
) : ViewModel(), DefaultLifecycleObserver {

    private val _uiState = MutableStateFlow(mapOf("" to emptyList<FixedIncome>()))
    val uiState: StateFlow<Map<String, List<FixedIncome>>> = _uiState

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        viewModelScope.launch {

            getFixedIncomeListUseCase(Unit).mapLatest { list -> list.groupBy { "${it.year} - ${it.month}" } }.collect {
                _uiState.emit(it)
            }
        }
    }
}