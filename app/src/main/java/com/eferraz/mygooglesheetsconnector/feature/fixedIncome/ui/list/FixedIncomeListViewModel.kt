package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.ui.list

import androidx.lifecycle.ViewModel
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.usecases.GetFixedIncomeListUseCase
import kotlinx.coroutines.flow.Flow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class FixedIncomeListViewModel constructor(
    getFixedIncomeListUseCase: GetFixedIncomeListUseCase
) : ViewModel() {

    private val _uiState = getFixedIncomeListUseCase()
    val uiState: Flow<List<GetFixedIncomeListUseCase.Grouped>> = _uiState
}