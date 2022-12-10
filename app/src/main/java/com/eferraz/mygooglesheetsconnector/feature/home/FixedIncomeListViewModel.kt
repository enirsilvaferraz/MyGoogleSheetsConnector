package com.eferraz.mygooglesheetsconnector.feature.home

import androidx.lifecycle.ViewModel
import com.eferraz.mygooglesheetsconnector.core.domain.GetFixedIncomeListUseCase
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

@HiltViewModel
class FixedIncomeListViewModel @Inject constructor(
    getFixedIncomeListUseCase: GetFixedIncomeListUseCase
) : ViewModel() {

    private val _uiState = getFixedIncomeListUseCase(Unit).transformation()
    val uiState: Flow<Map<String, List<FixedIncome>>> = _uiState
}

private fun Flow<MutableList<FixedIncome>>.transformation() = mapLatest { list ->
    list.groupBy { "${it.year} - ${it.month}" }
}
