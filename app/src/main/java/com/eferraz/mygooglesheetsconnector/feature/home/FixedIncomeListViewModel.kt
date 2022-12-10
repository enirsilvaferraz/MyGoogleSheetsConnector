package com.eferraz.mygooglesheetsconnector.feature.home

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eferraz.mygooglesheetsconnector.core.domain.GetFixedIncomeListUseCase
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FixedIncomeListViewModel @Inject constructor(
    getFixedIncomeListUseCase: GetFixedIncomeListUseCase
) : ViewModel(), DefaultLifecycleObserver {

    private val _uiState = getFixedIncomeListUseCase(Unit).transformation()
    val uiState: Flow<Map<String, List<FixedIncome>>> = _uiState
}

private fun Flow<MutableList<FixedIncome>>.transformation() = mapLatest { list ->
    list.groupBy { "${it.year} - ${it.month}" }
}
