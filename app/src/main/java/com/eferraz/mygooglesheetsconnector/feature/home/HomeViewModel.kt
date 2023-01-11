package com.eferraz.mygooglesheetsconnector.feature.home

import androidx.lifecycle.ViewModel
import com.eferraz.mygooglesheetsconnector.core.domain.GetFixedIncomeInReleaseUseCase
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import kotlinx.coroutines.flow.Flow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel constructor(getFixedIncomeListUseCase: GetFixedIncomeInReleaseUseCase) : ViewModel() {

    private val _uiState = getFixedIncomeListUseCase(Unit)
    val uiState: Flow<List<FixedIncome>> = _uiState
}