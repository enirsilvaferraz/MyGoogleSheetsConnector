package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.list

import androidx.lifecycle.ViewModel
import com.eferraz.mygooglesheetsconnector.core.domain.GetFixedIncomeListUseCase
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import kotlinx.coroutines.flow.Flow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class FixedIncomeListViewModel constructor(
    getFixedIncomeListUseCase: GetFixedIncomeListUseCase
) : ViewModel() {

    private val _uiState = getFixedIncomeListUseCase()
    val uiState: Flow<List<FixedIncome>> = _uiState
}