package com.eferraz.mygooglesheetsconnector.core.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["year", "month", "name"])
data class FixedIncome(
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "month") val month: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "investment") val investment: String,
    @ColumnInfo(name = "amount") val amount: String
)