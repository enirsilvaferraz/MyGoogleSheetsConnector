package com.eferraz.mygooglesheetsconnector.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eferraz.mygooglesheetsconnector.core.domain.GetFixedIncomeListUseCase
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FixedIncomeListViewModel @Inject constructor(
    getFixedIncomeListUseCase: GetFixedIncomeListUseCase
) : ViewModel() {
    lateinit var uiState: Flow<MutableList<FixedIncome>>

    init {
        viewModelScope.launch {
            uiState = getFixedIncomeListUseCase(Unit)
        }
    }
}