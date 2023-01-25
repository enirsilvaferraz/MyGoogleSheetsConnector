package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models

import androidx.room.Embedded
import androidx.room.Relation

data class FixedIncomeWithHistory(

    @Embedded
    val fixedIncome: FixedIncome,

    @Relation(parentColumn = "uuid", entityColumn = "fixedIncome", entity = FixedIncomeHistory::class)
    val history: List<FixedIncomeHistory>
)