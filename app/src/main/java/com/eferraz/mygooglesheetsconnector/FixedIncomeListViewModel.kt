package com.eferraz.mygooglesheetsconnector

import androidx.lifecycle.ViewModel
import com.eferraz.finance.domain.archtecture.BaseUseCase
import com.eferraz.finance.domain.entities.FixedIncome
import com.eferraz.mygooglesheetsconnector.usecases.GetFixedIncomeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FixedIncomeListViewModel @Inject constructor(
    private val getFixedIncomeListUseCase: GetFixedIncomeListUseCase
) : ViewModel() {
    val uiState = getFixedIncomeListUseCase(Unit)
}