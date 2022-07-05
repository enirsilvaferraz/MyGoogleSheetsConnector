package com.eferraz.mygooglesheetsconnector.repositories

import com.eferraz.googlesheets.datasources.DataSourceResponse
import com.eferraz.googlesheets.datasources.DataSourceResponse.Failure
import com.eferraz.googlesheets.datasources.DataSourceResponse.Success
import com.eferraz.googlesheets.datasources.SheetsDataSource
import com.eferraz.mygooglesheetsconnector.datasources.EnvironmentDataSource
import com.eferraz.mygooglesheetsconnector.entities.FixedIncome
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FixedIncomeRepositoryImpl @Inject constructor(
    private val dataSource: SheetsDataSource,
    private val environmentDataSource: EnvironmentDataSource
) : SheetsPageRepository<FixedIncome> {

    private val RANGE = "'Renda Fixa'!A2:Z1000"

    override fun get() = flow {
        emit(dataSource.get(environmentDataSource.sheetKey, RANGE).toResponse())
    }

    override fun append(vararg data: FixedIncome) =
        dataSource.append(environmentDataSource.sheetKey, RANGE, data.toList().toRequestBody()).toResponse()

    /**
     * Mappers
     */
    private fun MutableList<MutableList<*>>.toModel(): List<FixedIncome> = this.map {
        FixedIncome(
            col1 = runCatching { it[0] as String }.getOrNull().orEmpty(),
            col2 = runCatching { it[1] as String }.getOrNull().orEmpty(),
            col3 = runCatching { it[2] as String }.getOrNull().orEmpty(),
            col4 = runCatching { it[3] as String }.getOrNull().orEmpty(),
            col5 = runCatching { it[4] as String }.getOrNull().orEmpty(),
        )
    }

    private fun List<FixedIncome>.toRequestBody(): MutableList<MutableList<*>> {
        return this.map { mutableListOf(it.col1, it.col2, it.col3, it.col4, it.col5) }.toMutableList()
    }

    private fun DataSourceResponse.toResponse(): DataSourceResponse = when (this) {
        is Success<*> -> Success((this.data as MutableList<MutableList<*>>?)?.toModel())
        is Failure -> Failure(this.e, this.intent)
    }
}