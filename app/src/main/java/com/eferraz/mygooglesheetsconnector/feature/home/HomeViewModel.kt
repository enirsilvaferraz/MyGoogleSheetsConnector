package com.eferraz.mygooglesheetsconnector.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eferraz.mygooglesheetsconnector.core.domain.GetFixedIncomeInReleaseUseCase
import com.eferraz.mygooglesheetsconnector.core.domain.SynchronizeDataBaseUseCase
import com.eferraz.mygooglesheetsconnector.core.domain.definedFiltersIn3Months
import com.eferraz.mygooglesheetsconnector.core.domain.definedFiltersThisYear
import com.eferraz.mygooglesheetsconnector.core.model.BaseModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel constructor(
    private val getFixedIncomeListUseCase: GetFixedIncomeInReleaseUseCase,
    private val synchronizeDataBaseUseCase: SynchronizeDataBaseUseCase
) : ViewModel() {

    private val _uiState: Flow<List<HomeItem>> = combine(
        getFixedIncomeListUseCase(definedFiltersIn3Months()).map { HomeItem.FixedIncome3Months(it) },
        getFixedIncomeListUseCase(definedFiltersThisYear()).map { HomeItem.FixedIncomeThisYear(it) }
    ) { a, b ->
        listOf(a, b)
    }

    val uiState = _uiState

    private val _message: MutableStateFlow<String> = MutableStateFlow("")
    val message: StateFlow<String> = _message

    fun onSyncClicked() {
        viewModelScope.launch(IO) {
            synchronizeDataBaseUseCase().onCompletion {
                _message.value = if (it != null) "Sincronização Falhou!" else "Sincronização concluída!"
            }.collect()
        }
    }

    open class HomeItem(val title: String, val data: List<BaseModel>) {
        data class FixedIncome3Months(val list: List<BaseModel>) : HomeItem(title = "Renda fíxa a vencer em 2023", data = list)
        data class FixedIncomeThisYear(val list: List<BaseModel>) : HomeItem(title = "Renda fíxa a vencer em menos de 3 meses", data = list)
    }
}