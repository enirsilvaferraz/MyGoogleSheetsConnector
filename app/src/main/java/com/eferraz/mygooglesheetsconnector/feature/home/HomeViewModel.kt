package com.eferraz.mygooglesheetsconnector.feature.home

import androidx.lifecycle.ViewModel
import com.eferraz.mygooglesheetsconnector.core.domain.GetFixedIncomeInReleaseUseCase
import com.eferraz.mygooglesheetsconnector.core.model.BaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel constructor(getFixedIncomeListUseCase: GetFixedIncomeInReleaseUseCase) : ViewModel() {

    private val _uiState: Flow<Map<String, List<BaseModel>>> = merge(
        getFixedIncomeListUseCase().map { list -> list.groupBy { it::class.simpleName.orEmpty() } }
    )

    val uiState = _uiState
}