package com.eferraz.mygooglesheetsconnector.core.repositories

import com.eferraz.googlesheets.providers.GenericSheetsDatasource
import com.eferraz.mygooglesheetsconnector.BuildConfig
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import com.eferraz.mygooglesheetsconnector.core.model.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

interface FixedIncomeSheetsRepository {
    fun get(): Flow<List<FixedIncome>>
}

@Factory
class FixedIncomeSheetsRepositoryImpl(
    private val datasource: GenericSheetsDatasource
) : FixedIncomeSheetsRepository {

    private val sheetsKey: String = BuildConfig.SHEET_KEY
    private val range: String = "'Histórico Renda Fixa'!2:1000"

    override fun get(): Flow<List<FixedIncome>> = datasource.get(sheetsKey, range).map { it.toModel() }
}