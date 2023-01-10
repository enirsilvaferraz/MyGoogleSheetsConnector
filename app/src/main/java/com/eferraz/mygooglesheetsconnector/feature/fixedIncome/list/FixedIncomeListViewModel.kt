package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.list

import androidx.lifecycle.ViewModel
import com.eferraz.mygooglesheetsconnector.core.domain.GetFixedIncomeListUseCase
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FixedIncomeListViewModel @Inject constructor(
    getFixedIncomeListUseCase: GetFixedIncomeListUseCase
) : ViewModel() {

    private val _uiState = getFixedIncomeListUseCase(Unit)
    val uiState: Flow<List<FixedIncome>> = _uiState
}