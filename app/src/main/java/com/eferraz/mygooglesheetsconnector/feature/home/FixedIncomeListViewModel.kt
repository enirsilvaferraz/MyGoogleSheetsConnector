package com.eferraz.mygooglesheetsconnector.feature.home

import androidx.lifecycle.ViewModel
import com.eferraz.mygooglesheetsconnector.core.domain.GetFixedIncomeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FixedIncomeListViewModel @Inject constructor(
    getFixedIncomeListUseCase: GetFixedIncomeListUseCase
) : ViewModel() {
    val uiState = getFixedIncomeListUseCase(Unit)
}