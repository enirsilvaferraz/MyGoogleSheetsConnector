package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["year", "month", "fixedIncome"])
data class FixedIncomeHistory(
    @ColumnInfo(name = "year") val year: Int,
    @ColumnInfo(name = "month") val month: Int,
    @ColumnInfo(name = "investment") val investment: Double,
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "target") val target: String,
    @ColumnInfo(name = "fixedIncome") val fixedIncomeUuid: String
)