package com.eferraz.mygooglesheetsconnector

import androidx.lifecycle.ViewModel
import com.eferraz.finance.domain.archtecture.BaseRepository
import com.eferraz.finance.domain.entities.FixedIncome
import com.eferraz.mygooglesheetsconnector.repositories.FixedIncomePage
import com.eferraz.mygooglesheetsconnector.usecases.GetFixedIncomeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class FixedIncomeListViewModel @Inject constructor(getFixedIncomeListUseCase: GetFixedIncomeListUseCase) : ViewModel() {
    val uiState = getFixedIncomeListUseCase(Unit)
}