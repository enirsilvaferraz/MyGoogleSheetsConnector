package com.eferraz.mygooglesheetsconnector

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eferraz.finance.domain.entities.FixedIncome
import com.eferraz.mygooglesheetsconnector.repositories.FixedIncomePage
import com.eferraz.mygooglesheetsconnector.repositories.BaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoogleSheetsViewModel @Inject constructor(
    @FixedIncomePage private val repo: BaseRepository<@JvmSuppressWildcards List<FixedIncome>>
) : ViewModel() {

    val uiState = repo.get().flowOn(IO)

    fun append() = viewModelScope.launch(IO) {
        //repo.append(FixedIncome("A", "B", "C", "D", "E"))
        //getData()
    }
}