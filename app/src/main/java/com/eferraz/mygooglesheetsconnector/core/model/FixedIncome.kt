package com.eferraz.mygooglesheetsconnector.core.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.time.LocalDate

@Entity(primaryKeys = ["year", "month", "name"])
data class FixedIncome(
    @ColumnInfo(name = "year") val year: Int,
    @ColumnInfo(name = "month") val month: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "dueDate") val dueDate: LocalDate?,
    @ColumnInfo(name = "liquidity") val liquidity: String,
    @ColumnInfo(name = "investment") val investment: Double,
    @ColumnInfo(name = "amount") val amount: Double
)