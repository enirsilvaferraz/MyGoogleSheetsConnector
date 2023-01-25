package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.repositories.sheets

import com.eferraz.googlesheets.providers.GenericSheetsDatasource
import com.eferraz.mygooglesheetsconnector.BuildConfig
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models.FixedIncomeWithHistory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

interface FixedIncomeSheetsRepository {
    fun get(): Flow<List<FixedIncomeWithHistory>>
}

@Factory
class FixedIncomeSheetsRepositoryImpl(
    private val datasource: GenericSheetsDatasource
) : FixedIncomeSheetsRepository {

    private val sheetsKey: String = BuildConfig.SHEET_KEY
    private val range: String = "'Histórico Renda Fixa'!2:1000"

    override fun get(): Flow<List<FixedIncomeWithHistory>> = datasource.get(sheetsKey, range).map { it.toModel() }
}
