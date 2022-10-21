package com.eferraz.mygooglesheetsconnector.repositories

import com.eferraz.googlesheets.datasources.SheetsDataSource
import com.eferraz.mygooglesheetsconnector.datasources.EnvironmentDataSource
import com.eferraz.mygooglesheetsconnector.entities.FixedIncome
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FixedIncomeRepositoryImpl @Inject constructor(
    private val dataSource: SheetsDataSource,
    private val environmentDataSource: EnvironmentDataSource
) : SheetsPageRepository<@JvmSuppressWildcards List<FixedIncome>> {

    private val RANGE = "'Histórico Renda Fixa'!A2:Z1000"

    override fun get() = flow {

        val transformation = { it: List<Any> ->
            FixedIncome(
                col1 = runCatching { it[alphabets.indexOf('B')] as String }.getOrNull().orEmpty(),
                col2 = runCatching { it[alphabets.indexOf('C')] as String }.getOrNull().orEmpty(),
                col3 = runCatching { it[alphabets.indexOf('F')] as String }.getOrNull().orEmpty(),
                col4 = runCatching { it[alphabets.indexOf('O')] as String }.getOrNull().orEmpty(),
                col5 = runCatching { it[alphabets.indexOf('W')] as String }.getOrNull().orEmpty(),
            )
        }

        emit(dataSource.get(environmentDataSource.sheetKey, RANGE, transformation))
    }

    //override fun append(vararg data: FixedIncome) =
    //    dataSource.append(environmentDataSource.sheetKey, RANGE, data.toList().toRequestBody()).toResponse()

    /**
     * Mappers
     */

    private val alphabets = ('A'..'Z')
}