package com.eferraz.mygooglesheetsconnector

import androidx.lifecycle.ViewModel
import com.eferraz.mygooglesheetsconnector.core.domain.usecases.GetFixedIncomeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FixedIncomeListViewModel @Inject constructor(
    private val getFixedIncomeListUseCase: GetFixedIncomeListUseCase
) : ViewModel() {
    val uiState = getFixedIncomeListUseCase(Unit)
}