package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.datasources

import androidx.room.Dao
import androidx.room.Transaction
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models.FixedIncomeWithHistory

@Dao
interface FixedIncomeWithHistoryDao : FixedIncomeDao, FixedIncomeHistoryDao {

    @Transaction
    fun insert(data: List<FixedIncomeWithHistory>) {

        deleteAllHistory()
        deleteAllFixedIncome()

        data.forEach {
            insert(it.fixedIncome)
            it.history.forEach { history ->
                insert(history)
            }
        }
    }
}