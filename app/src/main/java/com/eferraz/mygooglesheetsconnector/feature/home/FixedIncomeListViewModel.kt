package com.eferraz.mygooglesheetsconnector.feature.home

import androidx.lifecycle.ViewModel
import com.eferraz.mygooglesheetsconnector.core.domain.GetFixedIncomeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class FixedIncomeListViewModel @Inject constructor(
    getFixedIncomeListUseCase: GetFixedIncomeListUseCase
) : ViewModel() {
    val uiState = flow { emit(getFixedIncomeListUseCase(Unit)) }.flowOn(Dispatchers.IO)
}