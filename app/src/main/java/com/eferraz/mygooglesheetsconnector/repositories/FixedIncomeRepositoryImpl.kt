package com.eferraz.mygooglesheetsconnector.repositories

import com.eferraz.mygooglesheetsconnector.datasources.EnvironmentDataSource
import com.eferraz.mygooglesheetsconnector.datasources.SheetsDataSource
import com.eferraz.mygooglesheetsconnector.entities.FixedIncome
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FixedIncomeRepositoryImpl @Inject constructor(
    private val dataSource: SheetsDataSource,
    private val environmentDataSource: EnvironmentDataSource
) : SheetsPageRepository<FixedIncome> {

    private val RANGE = "'Renda Fixa'!A2:Z1000"

    override fun get() = flow { emit(dataSource.get(environmentDataSource.sheetKey, RANGE).toModel()) }

    override fun append(vararg data: FixedIncome) =
        dataSource.append(environmentDataSource.sheetKey, RANGE, data.toList().toRequestBody())

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
}